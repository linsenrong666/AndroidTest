package linsr.com.androidtest.dispatch.conflict;

import android.view.View;

import linsr.com.androidtest.R;
import linsr.com.androidtest.base.BaseActivity;
import linsr.com.androidtest.common.TabViewGroup;

/**
 * Description
 *
 * @author Linsr 2019/8/15 下午4:55
 */
public class InterceptActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_intercept;
    }

    @Override
    protected void init() {
        initTAG(this.getClass());
        TabViewGroup mTabViewGroup = find(R.id.conflict_tab_vg);
        mTabViewGroup.addItem("外部拦截", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.conflict_content, OutsideFragment.newInstance())
                        .commit();
            }
        });
        mTabViewGroup.addItem("内部拦截", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.conflict_content, OutsideFragment.newInstance())
                        .commit();
            }
        });
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.conflict_content, OutsideFragment.newInstance())
                .commit();
    }

}
