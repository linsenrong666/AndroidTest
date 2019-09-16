package linsr.com.androidtest.livedata;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import linsr.com.androidtest.R;
import linsr.com.androidtest.base.BaseActivity;
import linsr.com.androidtest.loader.Source;
import linsr.com.androidtest.receiver.ReceiverTestActivity;

/**
 * Description
 *
 * @author Linsr 2019/8/14 上午10:34
 */
public class LiveDataDemoActivity extends BaseActivity {
    private String TAG = ReceiverTestActivity.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_livedata;
    }

    @Override
    protected void init() {

    }

    public void onLivedataADD(View view) {
    }
}
