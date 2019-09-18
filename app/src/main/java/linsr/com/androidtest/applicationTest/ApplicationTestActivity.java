package linsr.com.androidtest.applicationTest;

import android.app.Application;
import android.view.View;
import android.widget.Button;

import linsr.com.androidtest.R;
import linsr.com.androidtest.base.BaseActivity;

/**
 * Description
 *
 * @author Linsr 2019/8/19 下午4:54
 */
public class ApplicationTestActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_application;
    }

    @Override
    protected void init() {

    }

    public void applicationClick(View view) {
        try {
            Application application = new Application();
            ((Button) view).setText(application.getText(R.string.app_name));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
