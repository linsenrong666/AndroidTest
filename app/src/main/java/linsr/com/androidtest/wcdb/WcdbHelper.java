package linsr.com.androidtest.wcdb;

import android.content.ContentValues;
import android.content.Context;

import com.tencent.wcdb.Cursor;
import com.tencent.wcdb.DatabaseErrorHandler;
import com.tencent.wcdb.database.SQLiteCipherSpec;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteOpenHelper;
import com.tencent.wcdb.repair.RepairKit;

import java.io.File;

public class WcdbHelper extends SQLiteOpenHelper implements DB.Biz {

    static final String DATABASE_NAME = "wcdb.db";
    static final int DATABASE_VERSION = 1;

    static final byte[] PASSPHRASE = "testkey".getBytes();
    private Context mContext;

    // The test database is taken from SQLCipher test-suit.
    //
    // To be compatible with databases created by the official SQLCipher
    // library, a SQLiteCipherSpec must be specified with page size of
    // 1024 bytes.
    static final SQLiteCipherSpec CIPHER_SPEC = new SQLiteCipherSpec()
            .setPageSize(1024);

    private static final String password = "123456";
    // We don't want corrupted databases get deleted or renamed on this sample,
    // so use an empty DatabaseErrorHandler.
    private static final DatabaseErrorHandler ERROR_HANDLER = new DatabaseErrorHandler() {
        @Override
        public void onCorruption(SQLiteDatabase dbObj) {
            // Do nothing
        }
    };

    public WcdbHelper(Context context,String password) {
        super(context, DATABASE_NAME, password.getBytes(), null, DATABASE_VERSION, ERROR_HANDLER);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS " + Table.TABLE_NAME +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT , " + Table.COLUMN_NAME + " VARCHAR(20) , " + Table.COLUMN_ADDRESS + " TEXT)";
        db.execSQL(SQL_CREATE);

        RepairKit.MasterInfo.save(db, db.getPath() + "-mbak", PASSPHRASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Do nothing.
    }

    @Override
    public String getClicent() {
        return "wcdb";
    }

    @Override
    public void insert(int count) {
        for (int i = 0; i < count; i++) {
            ContentValues values = new ContentValues();
            values.put(Table.COLUMN_NAME, "wcdb " + i);
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
            values.put(Table.COLUMN_NAME, "wcdb " + i);
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
