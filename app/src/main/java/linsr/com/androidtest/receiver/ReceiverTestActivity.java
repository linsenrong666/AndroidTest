package linsr.com.androidtest.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import linsr.com.androidtest.R;

/**
 * Description
 *
 * @author Linsr 2019/8/14 上午10:34
 */
public class ReceiverTestActivity extends AppCompatActivity {
    private String TAG = ReceiverTestActivity.class.getSimpleName();
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "收到广播啦！");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
    }

    public void onRegister(View view) {
        Log.v(TAG, "注册广播！mBroadcastReceiver =" + mBroadcastReceiver);
        IntentFilter filter = new IntentFilter("test");
        registerReceiver(mBroadcastReceiver, filter);
    }

    public void onUnregister(View view) {
        Log.v(TAG, "反注册广播！mBroadcastReceiver =" + mBroadcastReceiver);
        try {
            unregisterReceiver(mBroadcastReceiver);
            mBroadcastReceiver = null;
//            getApplicationContext().unregisterReceiver(mBroadcastReceiver);
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

    public void send(View view) {
        Log.d(TAG, "发送广播！");
        Intent intent = new Intent();
        intent.setAction("test");
        sendBroadcast(intent);
    }
}
