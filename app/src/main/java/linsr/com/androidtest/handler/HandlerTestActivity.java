package linsr.com.androidtest.handler;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import linsr.com.androidtest.R;
import linsr.com.androidtest.Utils;

/**
 * Description
 *
 * @author Linsr 2019/8/15 下午3:12
 */
public class HandlerTestActivity extends AppCompatActivity {
    private String TAG = HandlerTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        Utils.v(TAG, "onCreate()");
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(final Message msg) {
            //这里接受并处理消息
            Utils.d(TAG, "handleMessage");
        }
    };

    public void handlerStart(View view) {
        Message message = Message.obtain();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Utils.w(TAG, "post");
            }
        },1);
        Utils.i(TAG,"main send");
        handler.sendMessage(message);
    }

    public void handlerThreadStart(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Utils.i(TAG,"thread send");
                Message message = Message.obtain();
                handler.sendMessage(message);
            }
        }).start();
    }
}
