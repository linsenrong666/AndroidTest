package linsr.com.androidtest.loader;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CP extends BaseContentProvider {

    private static final String DATABASE_NAME = "LOADER_TEST.db";
    private static final int DATABASE_VERSION = 2;

    private static final String USER_TABLE = "user";

    private static class DbHelper extends AbstractSQLiteHelper {
        private static volatile DbHelper mInstance;

        private DbHelper(Context context) {
            super(context, DATABASE_NAME, DATABASE_VERSION);
            mInstance = this;
        }

        public static DbHelper getInstance(Context context) {
            if (mInstance == null) {
                synchronized (DbHelper.class) {
                    if (mInstance == null) {
                        mInstance = new DbHelper(context);
                    }
                }
            }
            return mInstance;
        }

        @Override
        protected void onCreateEx(SQLiteDatabase db) {
            createTable(db, USER_TABLE, createTable());
        }

        @Override
        protected void onUpgradeEx(SQLiteDatabase db, List<String> tables) {

        }

        public Map<String, String> createTable() {
            Map<String, String> map = new HashMap<>();
            map.put(BaseColumns._ID, primary());
            map.put(BaseColumns._ENABLE, integer(1));
            map.put(BaseColumns._ROW_LAST_UPDATED_TIME, integer(0));
            map.put(Source.User.NAME, text());
            map.put(Source.User.GENDER, text());
            map.put(Source.User.EMAIL, text());
            map.put(Source.User.USER_ID, text());
            return map;
        }

    }

    private DbHelper mDbHelper;
    private static final UriMatcher uriMatcher;
    private static final int USER = 1;
    private static Map<String, String> userMaps;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(Source.AUTHORITY, Source.User.PATH, USER);

        userMaps = new HashMap<>();
        userMaps.put(BaseColumns._ID, BaseColumns._ID);
        userMaps.put(BaseColumns._ENABLE, BaseColumns._ENABLE);
        userMaps.put(UserColumn.NAME, UserColumn.NAME);
        userMaps.put(UserColumn.USER_ID, UserColumn.USER_ID);
        userMaps.put(UserColumn.GENDER, UserColumn.GENDER);
        userMaps.put(UserColumn.EMAIL, UserColumn.EMAIL);
    }


    @Override
    public boolean onCreate() {
        mDbHelper = DbHelper.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        try {
            SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();
            switch (uriMatcher.match(uri)) {
                case USER:
                    sqb.setTables(USER_TABLE);
                    sqb.setProjectionMap(userMaps);
                    return queryDb(sqb, mDbHelper, uri, projection, selection, selectionArgs, sortOrder);
                default:
                    throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        try {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            long _id;
            Uri contentUri;
            switch (uriMatcher.match(uri)) {
                case USER:
                    _id = db.insert(USER_TABLE, Source.User.USER_ID, values);
                    contentUri = Source.User.CONTENT_URI;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            if (_id > 0 && contentUri != null) {
                Uri uri1 = ContentUris.withAppendedId(contentUri, _id);
                notifyUriIfNeeded(uri1);
                return uri1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        try {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            switch (uriMatcher.match(uri)) {
                case USER:
                    count = db.delete(USER_TABLE, selection, selectionArgs);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            notifyUriIfNeeded(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        try {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            switch (uriMatcher.match(uri)) {
                case USER:
                    count = db.update(USER_TABLE, values, selection, selectionArgs);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            if (count > 0) {
                notifyUriIfNeeded(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
