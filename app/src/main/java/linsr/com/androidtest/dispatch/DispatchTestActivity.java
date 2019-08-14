package linsr.com.androidtest.dispatch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import linsr.com.androidtest.R;

/**
 * 事件分发
 * dispatchTouchEvent()
 * onTouchEvent()
 * onInterceptTouchEvent()
 *
 * 1、Android 事件分发总是遵循 Activity => ViewGroup => View 的传递顺序；
 * 2、onTouch() 执行总优先于 onClick()
 *
 * https://www.jianshu.com/p/38015afcdb58
 *
 * @author Linsr 2019/8/14 下午7:12
 */
public class DispatchTestActivity extends AppCompatActivity {
    private String TAG = DispatchTestActivity.class.getSimpleName() + "事件分发";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
    }

    /**
     * ##点击事件首先会分发到Activity的dispatchTouchEvent方法里面##
     *
     * getWindow().superDispatchTouchEvent()
     * PhoneWindows.superDispatchTouchEvent()
     * 本质是调用了DecorView根布局的dispatchTouchEvent()方法
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.v(TAG, "dispatchTouchEvent,event:" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 该方法主要的作用是实现屏保功能，并且当此 Activity 在栈顶的时候，
     * 触屏点击 Home、Back、Recent 键等都会触发这个方法。
     */
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        Log.v(TAG, "onUserInteraction");
    }

    /**
     * ##dispatchTouchEvent()如果没有被拦截，最后会调用onTouchEvent()方法##
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v(TAG, "onTouchEvent. event:" + event.getAction());
        return super.onTouchEvent(event);
    }

}
