package linsr.com.androidtest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import linsr.com.androidtest.Utils;

/**
 * Description
 *
 * @author Linsr 2019/8/15 下午4:55
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init();
    }

    protected abstract int getLayoutId();

    protected abstract void init();

    protected void initTAG(Class c) {
        TAG = c.getSimpleName();
    }

    protected void v(String text, String... content) {
        Utils.v(TAG, text, content);
    }

    protected void d(String text, String... content) {
        Utils.d(TAG, text, content);
    }

    protected void i(String text, String... content) {
        Utils.i(TAG, text, content);
    }

    protected void w(String text, String... content) {
        Utils.w(TAG, text, content);
    }

    protected void e(String text, String... content) {
        Utils.e(TAG, text, content);
    }

    protected <T extends View> T  find(int id) {
        return findViewById(id);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
