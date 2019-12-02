package linsr.com.androidtest.wcdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class SqliteHelper extends SQLiteOpenHelper implements DB.Biz {

    static final String DATABASE_NAME = "sqlite.db";
    static final int DATABASE_VERSION = 1;

    public SqliteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS " + Table.TABLE_NAME +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT , " + Table.COLUMN_NAME + " VARCHAR(20) , " + Table.COLUMN_ADDRESS + " TEXT)";
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public String getClicent() {
        return "sqlite";
    }

    @Override
    public void insert(int count) {
        for (int i = 0; i < count; i++) {
            ContentValues values = new ContentValues();
            values.put(Table.COLUMN_NAME, "sqlite " + i);
            try {
                getWritableDatabase().insert(Table.TABLE_NAME, null, values);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void insert(SQLiteDatabase db, int count) {
        for (int i = 0; i < count; i++) {
            ContentValues values = new ContentValues();
            values.put(Table.COLUMN_NAME, "sqlite " + i);
            try {
                db.insert(Table.TABLE_NAME, null, values);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int query() {
        Cursor query = getReadableDatabase().query(Table.TABLE_NAME, null, null, null, null, null, null);
        return query.getCount();
    }
}
