package linsr.com.androidtest.dispatch.conflict;

import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import linsr.com.androidtest.R;
import linsr.com.androidtest.base.BaseFragment;

/**
 * Description
 *
 * @author Linsr 2019/8/15 下午8:15
 */
public class OutsideFragment extends BaseFragment {

    private ScrollView parent;
    private ScrollView child;
    private HorizontalScrollView childHor;

    public static OutsideFragment newInstance() {

        Bundle args = new Bundle();

        OutsideFragment fragment = new OutsideFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_conflict_outside;
    }

    @Override
    protected void init() {
        initTAG(this.getClass());
        parent = find(R.id.conflict_parent_sv);
        child = find(R.id.conflict_child_sv);
        childHor = find(R.id.conflict_child_hor_sv);
    }
}
