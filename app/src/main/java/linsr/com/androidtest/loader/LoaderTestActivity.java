package linsr.com.androidtest.loader;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.View;
import android.widget.ListView;

import linsr.com.androidtest.R;
import linsr.com.androidtest.Utils;
import linsr.com.androidtest.base.BaseActivity;

public class LoaderTestActivity extends BaseActivity {

    private ListView mListView;
    private UserAdapter mAdapter;
    private UserLoader mUserLoader;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_loader;
    }

    @Override
    protected void init() {
        mListView = find(R.id.loader_lv);
        Cursor cursor = getContentResolver().query(Source.User.CONTENT_URI, null, null, null, null);
        mAdapter = new UserAdapter(this, cursor);
        if (cursor != null) {
            i("=====", "count:%s", String.valueOf(cursor.getCount()));
            cursor.close();
        }
        mListView.setAdapter(mAdapter);
        mUserLoader = new UserLoader(this);

        getLoaderManager().initLoader(0,null, (LoaderManager.LoaderCallbacks<Cursor>) mUserLoader);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLoaderManager().destroyLoader(0);
    }

    public void onloaderAddData(View view) {
        ContentValues values = new ContentValues();
        values.put(Source.User.USER_ID, Utils.uuid());
        values.put(Source.User.NAME, "name:" + Utils.randomInt(100));
        getContentResolver().insert(Source.User.CONTENT_URI, values);
    }

}
