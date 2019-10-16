package linsr.com.androidtest.dispatch;

import android.view.View;

import linsr.com.androidtest.R;
import linsr.com.androidtest.Utils;
import linsr.com.androidtest.base.BaseActivity;

public class ScrollConflictOutActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scroll_conflict_out;
    }

    @Override
    protected void init() {
//        find(R.id.out_conflict_view).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Utils.v("TAG","onClick");
//            }
//        });
    }
}
