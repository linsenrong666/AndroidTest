package linsr.com.androidtest.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import linsr.com.androidtest.R;
import linsr.com.androidtest.base.BaseActivity;

public class SqlActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_sqlite;
    }

    private Button create;
    private Button writeA;
    private Button writeB;
    private Button readC;
    DBHelper mDBHelper;
    Thread mThreadA;
    Thread mThreadB;
    Thread mThreadC;
    Thread mThreadD;
    Thread mThreadF;

    @Override
    protected void init() {
        mThreadA = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    i++;
                    insert("A write ,i:" + i);
                    logD("插入 i ：" + i);
                }
            }
        });
        mThreadA.setName("线程A");
        mThreadB = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    i++;
                    insert("B write ,i:" + i);
                    logI("插入 i ：" + i);
                }
            }
        });
        mThreadB.setName("线程B");
        mThreadC = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    query(mDBHelper.getReadableDatabase());
                }
            }
        });
        mThreadC.setName("线程C");
        mThreadD = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                DBHelper dbHelper = new DBHelper(SqlActivity.this);
                SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
                while (true) {
                    i++;
                    insert(writableDatabase, "D write ,i:" + i);
                    logV("插入 i ：" + i);
                }
            }
        });
        mThreadD.setName("线程D");
        mThreadF = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                DBHelper dbHelper = new DBHelper(SqlActivity.this);
                SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
                while (true) {
                    i++;
                    insert(writableDatabase, "F write ,i:" + i);
                    logW("插入 i ：" + i);
                }
            }
        });
        mThreadF.setName("线程F");

        create = findViewById(R.id.sql_create_db);
        writeA = findViewById(R.id.sql_write_a);
        writeB = findViewById(R.id.sql_write_b);
        readC = findViewById(R.id.sql_read_c);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDBHelper = new DBHelper(SqlActivity.this);
            }
        });
        writeA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThreadA.start();
            }
        });
        writeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThreadB.start();
            }
        });
        readC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThreadC.start();
            }
        });
        findViewById(R.id.sql_write_d).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThreadD.start();
            }
        });
        findViewById(R.id.sql_write_f).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThreadF.start();
            }
        });

    }

    public  void insert(String value) {
        SQLiteDatabase writableDatabase = mDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME", value);
        writableDatabase.insert(tableName, null, values);
    }

    public void insert(SQLiteDatabase writableDatabase, String value) {
        ContentValues values = new ContentValues();
        values.put("NAME", value);
        writableDatabase.insert(tableName, null, values);
    }

    public void query(SQLiteDatabase readableDatabase) {
        Cursor query = readableDatabase.query(tableName, new String[]{"NAME", "AGE"}, null, null, null, null, null);
        if (query == null) {
            logE("查询失败，cursor = null");
            return;
        }
        if (query.getCount() == 0) {
            logE("cursor.getCount =" + 0);
            return;
        }
        while (query.moveToNext()) {
            logE(query.getString(query.getColumnIndex("NAME")));
        }

        query.close();
    }

    private void logE(String log) {
        Log.e("qwerty", log + "，当前线程：" + Thread.currentThread().getName());
    }

    private void logI(String log) {
        Log.i("qwerty", log + "，当前线程：" + Thread.currentThread().getName());
    }

    private void logD(String log) {
        Log.d("qwerty", log + "，当前线程：" + Thread.currentThread().getName());
    }

    private void logW(String log) {
        Log.w("qwerty", log + "，当前线程：" + Thread.currentThread().getName());
    }

    private void logV(String log) {
        Log.v("qwerty", log + "，当前线程：" + Thread.currentThread().getName());
    }

    private String tableName = "user";

    class DBHelper extends SQLiteOpenHelper {
        static final String DB_NAME = "test.db";
        static final int VERSION = 1;


        public DBHelper(@Nullable Context context) {
            super(context, DB_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + tableName + " ( " +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME  TEXT , AGE INT" +
                    ")");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
