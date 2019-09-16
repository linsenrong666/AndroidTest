package linsr.com.androidtest.file;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import linsr.com.androidtest.Utils;

/**
 * 日志上传工具
 */
public class UploadLogManager {

    private UploadLogManager() {
    }

    private static volatile UploadLogManager mInstance;
    private final static Lock sLock = new ReentrantLock();

    public static UploadLogManager getInstance() {
        if (mInstance == null) {
            synchronized (UploadLogManager.class) {
                if (mInstance == null) {
                    mInstance = new UploadLogManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 限制每个日志文件的大小，最大2M
     */
    private final static long LIMIT_SIZE = 2 * 1024 * 1024;//2M
    /**
     * 私有目录文件路径
     */
    private String mFilePath;
    private final static String LOG_FILE_A = "dd_waiter_log_a.log";
    private final static String LOG_FILE_B = "dd_waiter_log_b.log";
    /**
     * 写日志开关
     */
    private boolean mIsEnable;

    /**
     * 持久化记录当前正在写的文件是哪个，下次进入续写
     */
    private final static String CURRENT_FILE_KEY = "current_file_key";
    private final static String PREFERENCE = "DDWaiterLog";
    private SharedPreferences mPreferences;

    /**
     * 初始化
     *
     * @param application 上下文
     */
    public void init(Application application) {
        mFilePath = application.getFilesDir().getAbsolutePath();
        mPreferences = application.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        mIsEnable = true;
    }

    /**
     * 写日志
     *
     * @param message 日志内容
     */
    public void writeLog(String message) {
        if (!mIsEnable) {
            return;
        }
        try {
            sLock.lock();
            String currentFile = getCurrentFile();
            boolean isFull = isFull(currentFile);
            if (isFull) {
                //如果文件写满了，写到另外一个文件里
                currentFile = getAnotherFile(currentFile);
                setCurrentFile(currentFile);
                System.out.println("文件写满了，切换为：" + currentFile);
            }
            write(currentFile, message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sLock.unlock();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ////以下是私有方法                                                                           ////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private void write(String fileName, String message) throws IOException {
        File file = getFile(fileName);
        //检查文件是否被写满了，如果写满了，清空再往里写
        clearLogIfNeeded(file);
        //写日志
        write(file, message, true);
    }

    private void write(File file, String message, boolean append) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file, append));
            if (append) {
                bw.append(message);
            } else {
                bw.write(message);
            }
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Utils.i("linsr", "写日志：" + message + ",文件：" + file.getName() + "是否是清理操作：" + !append + ",当前文件大小：" + file.length());
        }
    }

    private void clearLogIfNeeded(File file) {
        if (file.exists() && file.length() >= LIMIT_SIZE) {
            write(file, "", false);
            System.out.println("清理文件：" + file.getName());
        }
    }

    private boolean isFull(String fileName) throws IOException {
        File file = getFile(fileName);
        if (file.exists()) {
            return (file.length() >= LIMIT_SIZE);
        } else {
            System.out.println("创建新文件：" + fileName);
            file.createNewFile();
            return false;
        }
    }

    private void setCurrentFile(String currentFile) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(CURRENT_FILE_KEY, currentFile);
        editor.apply();
    }

    private String getCurrentFile() {
        return mPreferences.getString(CURRENT_FILE_KEY, LOG_FILE_A);
    }

    private String getAnotherFile(String currentFile) {
        if (TextUtils.equals(currentFile, LOG_FILE_A)) {
            return LOG_FILE_B;
        } else {
            return LOG_FILE_A;
        }
    }

    private File getFile(String fileName) throws IOException {
        File dir = new File(mFilePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File log = new File(mFilePath, fileName);
        if (!log.exists()) {
            log.createNewFile();
        }
        return log;
    }
}
