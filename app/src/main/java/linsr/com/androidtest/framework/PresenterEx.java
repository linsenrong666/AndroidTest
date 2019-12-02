package linsr.com.androidtest.framework;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;



/**
 * Description
 *
 * @author Linsr 2018/12/9 下午5:06
 */
public abstract class PresenterEx<V extends IView> implements IPresenter {

    protected V mView;
    protected Application mApplication;
    private LifecycleOwner mLifecycleOwner;

    public PresenterEx(V IView) {
        mView = IView;
    }

    protected LifecycleOwner getLifecycleOwner() {
        return mLifecycleOwner;
    }

    @Override
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        mLifecycleOwner = lifecycleOwner;
    }

    @Override
    public void onLifecycleChanged( LifecycleOwner owner,
                                    Lifecycle.Event event) {

    }

    @Override
    public void onCreate( LifecycleOwner owner) {

    }

    @Override
    public void onStart( LifecycleOwner owner) {

    }

    @Override
    public void onResume( LifecycleOwner owner) {

    }

    @Override
    public void onPause( LifecycleOwner owner) {

    }

    @Override
    public void onStop( LifecycleOwner owner) {

    }

    @Override
    public void onDestroy( LifecycleOwner owner) {
        mView = null;
    }

}
