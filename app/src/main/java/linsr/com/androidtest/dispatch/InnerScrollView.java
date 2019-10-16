package linsr.com.androidtest.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import linsr.com.androidtest.Utils;

public class InnerScrollView extends ScrollView {
    private static final String TAG = "InnerScrollView";
    public InnerScrollView(Context context) {
        this(context, null, 0);
    }

    public InnerScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InnerScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Utils.i(TAG,"dispatchTouchEvent ACTION_DOWN");
//                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                Utils.i(TAG,"dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Utils.i(TAG,"dispatchTouchEvent ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Utils.e(TAG, "onInterceptTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Utils.e(TAG, "onInterceptTouchEvent ACTION_MOVE ");
                break;
            case MotionEvent.ACTION_UP:
                Utils.e(TAG, "onInterceptTouchEvent ACTION_UP");
                break;
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Utils.d(TAG, "onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Utils.d(TAG, "onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Utils.d(TAG, "onTouchEvent ACTION_UP");
                break;
        }
        return super.onTouchEvent(ev);
    }

    private String getActionName(int action) {
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                return "ACTION_DOWN";
            case MotionEvent.ACTION_MOVE:
                return "ACTION_MOVE";
            case MotionEvent.ACTION_UP:
                return "ACTION_UP";
            case MotionEvent.ACTION_CANCEL:
                return "ACTION_CANCEL";
            default:
                return "UNKNOWN";
        }
    }

}
