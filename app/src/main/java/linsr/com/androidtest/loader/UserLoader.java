package linsr.com.androidtest.loader;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

public class UserLoader extends CursorLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    public interface OnLoad {
        void onLoadFinish(Cursor cursor);
    }


    public UserLoader(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new UserLoader(getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
