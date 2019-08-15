package linsr.com.androidtest.dispatch.conflict;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import linsr.com.androidtest.R;
import linsr.com.androidtest.Utils;

/**
 * Description
 *
 * @author Linsr 2019/8/15 下午7:25
 */
public class ParentScrollView extends ScrollView {
    String TAG = "ParentScrollView";

    private ScrollView vertical;
    private HorizontalScrollView hotizontal;

    public ParentScrollView(Context context) {
        super(context);
    }

    public ParentScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParentScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        vertical = findViewById(R.id.conflict_child_sv);
        hotizontal = findViewById(R.id.conflict_child_hor_sv);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = true;
        if (inRangeOfView(vertical, ev)) {
            intercept = false;
        }
        if (inRangeOfView(hotizontal, ev)) {
            intercept = false;
        }
        return intercept;
    }

    /**
     * 判断点击的是否是指定的view
     * @param view   指定的view
     * @param ev
     * @return
     */
    private boolean inRangeOfView(View view, MotionEvent ev) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        if (ev.getX() < x || ev.getX() > (x + view.getWidth()) || ev.getY() < y || ev.getY() > (y + view.getHeight())) {
            return false;
        }
        return true;
    }
}
