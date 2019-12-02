package linsr.com.androidtest.wcdb;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import linsr.com.androidtest.R;
import linsr.com.androidtest.base.BaseActivity;

public class WcdbActivity extends BaseActivity {
    private static final String TAG = WcdbActivity.class.getSimpleName();

    private EditText mInsertEdittext;
    private EditText mqueryEdittext;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wcdb;
    }

    private SqliteHelper mSqliteHelper;
    private WcdbHelper mWcdbHelper;

    @Override
    protected void init() {
        mSqliteHelper = new SqliteHelper(WcdbActivity.this);
        mWcdbHelper = new WcdbHelper(WcdbActivity.this,"123456");
        mInsertEdittext = find(R.id.insert_edittext);
        mqueryEdittext = find(R.id.query_edittext);
        findViewById(R.id.sqlite_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        insert(mSqliteHelper);
                        mqueryEdittext.setText("123456".getBytes().toString());
                    }
                }).start();
            }
        });
        findViewById(R.id.wcdb_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        insert(mWcdbHelper);
                    }
                }).start();
            }
        });
        findViewById(R.id.sqlite_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        insert(mSqliteHelper);
                    }
                }).start();
            }
        });
        findViewById(R.id.wcdb_db_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int count = Integer.parseInt(mInsertEdittext.getText().toString().trim());
                        long start = System.currentTimeMillis();
                        com.tencent.wcdb.database.SQLiteDatabase writableDatabase = mWcdbHelper.getWritableDatabase();
                        writableDatabase.beginTransaction();
                        mWcdbHelper.insert(writableDatabase, count);
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                        long interval = System.currentTimeMillis() - start;
                        Log.d(TAG, mWcdbHelper.getClicent() + "开启事务插入 " + count + "条数据，耗时：" + interval + "ms" + ",当前线程:" + Thread.currentThread().getName());
                    }
                }).start();
            }
        });
        findViewById(R.id.sqlite_db_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int count = Integer.parseInt(mInsertEdittext.getText().toString().trim());
                        long start = System.currentTimeMillis();
                        SQLiteDatabase writableDatabase = mSqliteHelper.getWritableDatabase();
                        writableDatabase.beginTransaction();
                        mSqliteHelper.insert(writableDatabase, count);
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                        long interval = System.currentTimeMillis() - start;
                        Log.d(TAG, mSqliteHelper.getClicent() + ",开启事务插入 " + count + "条数据，耗时：" + interval + "ms" + ",当前线程:" + Thread.currentThread().getName());
                    }
                }).start();
            }
        });
        findViewById(R.id.sqlite_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        query(mSqliteHelper);
                    }
                }).start();
            }
        });
        findViewById(R.id.wcdb_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        query(mWcdbHelper);
                    }
                }).start();
            }
        });
    }


    private void insert(DB.Biz helper) {
        int count = Integer.parseInt(mInsertEdittext.getText().toString().trim());
        long start = System.currentTimeMillis();
        helper.insert(count);
        long interval = System.currentTimeMillis() - start;
        Log.v(TAG, helper.getClicent() + "插入 " + count + "条数据，耗时：" + interval + "ms" + ",当前线程:" + Thread.currentThread().getName());
    }

    private void query(DB.Biz helper) {
        long start = System.currentTimeMillis();
        int count = helper.query();
        long interval = System.currentTimeMillis() - start;
        Log.v(TAG, helper.getClicent() + "查询 " + count + "条数据，耗时：" + interval + "ms" + ",当前线程:" + Thread.currentThread().getName());
    }
}
