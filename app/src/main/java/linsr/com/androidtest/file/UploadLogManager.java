package linsr.com.androidtest.file;

import android.app.Application;
import android.content.SharedPreferences;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 日志上传工具
 */
public class UploadLogManager {

    private UploadLogManager() {
    }

    private static volatile UploadLogManager mInstance;
    private final static Lock sLock = new ReentrantLock();
    private final static long LIMIT_SIZE = 2 * 1024 * 1024;//2M

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
     * 私有目录文件路径
     */
    private String mFilePath;
    private String mLogAPath;
    private String mLogBPath;
    private final static String LOG_FILE_A = "dd_waiter_log_a";
    private final static String LOG_FILE_B = "dd_waiter_log_b";

    /**
     * 初始化方法
     *
     * @param application
     */
    public void init(Application application) {
        mFilePath = application.getFilesDir().getAbsolutePath();
        mLogAPath = mFilePath + File.separator + LOG_FILE_A;
        mLogBPath = mFilePath + File.separator + LOG_FILE_B;
    }

    /**
     * 写日志
     *
     * @param message 日志内容
     */
    public void writeLog(String message) {
        try {
            sLock.lock();
            String currentFile = getCurrentFile();
            boolean isFull = isFull(currentFile);
            //如果文件写满了，写到另外一个文件里，同时清理掉写满的文件
            if (isFull) {
                //清理写满的文件
                clearLog(currentFile);
                //切换到另一个文件
                currentFile = getAnotherFile();
                setCurrentFile(currentFile);
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
    private void write(String fileName, String message) {
        write(fileName, message, true);
    }

    private void write(String fileName, String message, boolean append) {
        BufferedWriter bw = null;
        try {
            File dir = new File(mFilePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File log = new File(dir, fileName);
            if (!log.exists()) {
                log.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(log, append));
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
        }
    }

    private void clearLog(String fileName) {
        write(fileName, "", false);
    }

    private boolean isFull(String fileName) throws IOException {
        File file = new File(mFilePath + File.separator + fileName);
        if (file.exists()) {
            return (file.length() >= LIMIT_SIZE);
        } else {
            file.createNewFile();
            return false;
        }
    }

    private void setCurrentFile(String currentFile) {

    }

    private String getCurrentFile() {
        return LOG_FILE_A;
    }

    private String getAnotherFile() {
        return LOG_FILE_B;
    }
}
