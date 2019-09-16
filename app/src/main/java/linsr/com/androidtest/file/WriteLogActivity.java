package linsr.com.androidtest.file;

import android.content.Context;
import android.os.Environment;
import android.text.format.DateUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import linsr.com.androidtest.R;
import linsr.com.androidtest.Utils;
import linsr.com.androidtest.base.BaseActivity;
import linsr.com.androidtest.old.ToastUtils;

import static android.text.format.DateUtils.FORMAT_24HOUR;

public class WriteLogActivity extends BaseActivity {
    private String TAG = "file_log";

    private String[] log = {"1", "2", "3", "4", "5"};
    private final static Lock lock = new ReentrantLock();

    private final static String LOG_FILE_A = "dd_waiter_log_a.log";
    private final static String LOG_FILE_B = "dd_waiter_log_b.log";

    private String dirPath;
    private TextView mTextView;
    private ScrollView mScrollView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_write_log;
    }


    @Override
    protected void init() {
        mTextView = findViewById(R.id.log_tv);
        mScrollView = find(R.id.log_sv);
        dirPath = getFilesDir().getAbsolutePath();

        UploadLogManager.getInstance().init(getApplication());
    }

    public void onWriteLog(View view) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS", Locale.getDefault());
        String message = simpleDateFormat.format(new Date());
        UploadLogManager.getInstance().writeLog(message + "\n");
        mTextView.setText(readA());
        mScrollView.fullScroll(View.FOCUS_DOWN);
    }

    private void getFileSize(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.length();
        }
    }

    private void write(String fileDir, String fileName, String message) {
        lock.lock();
        FileWriter writer = null;
        try {
            File dir = new File(fileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File log = new File(dir, fileName);
            if (!log.exists()) {
                log.createNewFile();
            }
            writer = new FileWriter(log, true);
            writer.append(message);
            writer.flush();
        } catch (Exception e) {
            Log.v("LOG", "写入失败，一般是因为没有权限");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();
        }
    }

    public void onReadLogA(View view) {
        File file = new File(dirPath + "/" + LOG_FILE_A);
        Utils.i(TAG, "1 文件大小：" + file.length());
        try {
            long s = getFileSizes(file);
            Utils.i(TAG, "2 文件大小：" + s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mTextView.setText(readA());
    }

    private String readA() {
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream inputStream = openFileInput(LOG_FILE_A);
            byte[] buffer = new byte[1024];
            int len = inputStream.read(buffer);
            //读取文件内容
            while (len > 0) {
                sb.append(new String(buffer, 0, len));
                //继续将数据放到buffer中
                len = inputStream.read(buffer);
            }
            //关闭输入流
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void onClearA(View view) {
        Utils.i(TAG, "擦除日志A");

        lock.lock();
        FileWriter writer = null;
        try {
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File log = new File(dir, LOG_FILE_A);
            if (!log.exists()) {
                log.createNewFile();
            }
            writer = new FileWriter(log, false);
            writer.append("");
            writer.flush();
        } catch (Exception e) {
            //
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();
        }
    }


    /**
     * 取得文件大小
     *
     * @param f
     * @return
     * @throws Exception
     */
    public long getFileSizes(File f) throws Exception {
        long s = 0;
        if (f.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(f);
            s = fis.available();
        } else {
            f.createNewFile();
        }
        return s;
    }

}
