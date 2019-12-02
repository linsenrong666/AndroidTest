package linsr.com.androidtest.framework;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;


/**
 * Description
 *
 * @author Linsr 2018/7/9 下午4:41
 */
public interface IPresenter extends LifecycleObserver {

    void setLifecycleOwner(LifecycleOwner lifecycleOwner);

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate( LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart( LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume( LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause( LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop( LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy( LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onLifecycleChanged( LifecycleOwner owner,
                             Lifecycle.Event event);
}
