package linsr.com.androidtest.common;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import linsr.com.androidtest.R;

/**
 * Description
 *
 * @author Linsr 2019/8/15 下午7:56
 */
public class TabViewGroup extends FrameLayout {

    private LinearLayout container;

    public TabViewGroup(Context context) {
        this(context, null, 0);
    }

    public TabViewGroup(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_tab_parent,
                (ViewGroup) getParent(), false);
        container = view.findViewById(R.id.tab_layout);
        addView(view);
    }

    public void clear() {
        container.removeAllViews();
    }

    public void addItem(String title, View.OnClickListener listener) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_tab_title,
                (ViewGroup) getParent(), false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1;
        view.setLayoutParams(layoutParams);

        TextView tv = view.findViewById(R.id.item_tab_tv);
        tv.setText(title);
        tv.setOnClickListener(listener);
        container.addView(view);
    }
}
