package linsr.com.androidtest.old;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Description
 *
 * @author Linsr 2019/1/12 上午8:07
 */
public class SmsService extends Service {



    @Override
    public void onCreate() {
        super.onCreate();
        SmsUtils.registerSmsDatabaseChangeObserver(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SmsUtils.unregisterSmsDatabaseChangeObserver(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
