package linsr.com.androidtest;

import android.app.Application;

/**
 * Description
 *
 * @author Linsr 2019/8/15 上午10:54
 */
public class App extends Application {

    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static App getApp() {
        return mApp;
    }

}
