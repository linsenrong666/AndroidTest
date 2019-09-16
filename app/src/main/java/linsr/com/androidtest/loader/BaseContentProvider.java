package linsr.com.androidtest.loader;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by Linsr
 */
public abstract class BaseContentProvider extends ContentProviderWithDecorator implements ContentProviderDecorator {


    @Override
    public boolean clearContents() {
        return false;
    }

    protected Cursor queryDb(SQLiteQueryBuilder sqb,
                             SQLiteOpenHelper helper,
                             Uri uri,
                             String[] projection,
                             String selection,
                             String[] selectionArgs,
                             String sortOrder) {

        try {
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = sqb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
            return cursor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 创建默认的where语句，用于delete和update
     */
    protected String buildDefaultWhere(String _id, String id, String selection) {
        return _id + "=" + id + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : "");
    }

    //根据id删除数据库中的数据
    protected int deleteDB(SQLiteDatabase db, String tableName,
                           String _id, String id, String selection,
                           String[] selectionArgs) {
        return db.delete(tableName, buildDefaultWhere(_id, id, selection), selectionArgs);
    }

    //根据id更新数据库中的数据
    protected int updateDB(SQLiteDatabase db, ContentValues values, String tableName,
                           String _id, String id, String selection,
                           String[] selectionArgs) {
        return db.update(tableName, values, buildDefaultWhere(_id, id, selection), selectionArgs);
    }

    /**
     * 更新修改时间
     *
     * @param values
     * @param columnName
     */
    protected void putUpdateTime(ContentValues values, String columnName) {
        if (values != null) {
            values.put(columnName, System.currentTimeMillis());
        }
    }

    //关联查询的时候给不同表的相同名称的字段加上别名
    protected String[] updateTableProjections(String alias, String[] projections) {
        if (projections != null && projections.length > 0) {
            int idx = 0;
            for (String item : projections) {
                if (BaseColumns._ROW_LAST_UPDATED_TIME.equalsIgnoreCase(item)
                        || BaseColumns._ID.equalsIgnoreCase(item)
                        || BaseColumns._ENABLE.equalsIgnoreCase(item)
                        || BaseColumns._COUNT.equalsIgnoreCase(item)) {
                    projections[idx] = alias + item;
                } else {
                    projections[idx] = item;
                }
                idx++;
            }
            return projections;
        }
        return projections;
    }

}
