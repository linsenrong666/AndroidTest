package linsr.com.androidtest.dispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Description
 *
 * @author Linsr 2019/8/14 下午7:57
 */
public class ParentView extends LinearLayout implements View.OnTouchListener {
    private String TAG = ParentView.class.getSimpleName() + "事件分发";

    public ParentView(Context context) {
        this(context, null, 0);
    }

    public ParentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }

    /**
     * ##Activity的dispatchTouchEvent()调用完了会调用父布局的dispatchTouchEvent()方法##
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent(),event:" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent(),event:" + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.w(TAG, "onInterceptTouchEvent(),event:" + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.e(TAG, "onTouch()");
        return false;
    }
}
