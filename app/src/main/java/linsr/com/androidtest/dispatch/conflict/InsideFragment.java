package linsr.com.androidtest.dispatch.conflict;

import linsr.com.androidtest.R;
import linsr.com.androidtest.base.BaseFragment;

/**
 * Description
 *
 * @author Linsr 2019/8/15 下午8:33
 */
public class InsideFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_conflict_outside;
    }

    @Override
    protected void init() {
        initTAG(this.getClass());
    }
}
