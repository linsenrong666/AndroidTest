package linsr.com.androidtest.dispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Description
 *
 * @author Linsr 2019/8/14 下午8:03
 */
public class ChildView extends View implements View.OnTouchListener {

    private String TAG = ChildView.class.getSimpleName() + "事件分发";

    public ChildView(Context context) {
        this(context, null, 0);
    }

    public ChildView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChildView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }

    /**
     * 父布局的 onInterceptTouchEvent() -> 子View的dispatchTouchEvent()##
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent(),event:" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 子View的 onTouch() -> 子View的 onTouchEvent()
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent(),event:" + event.getAction());
        return super.onTouchEvent(event);
    }

    /**
     * 子View的 dispatchTouchEvent() -> 子View的 onTouch()
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.e(TAG, "onTouch(),event:" + event.getAction());
        return false;
    }
}
