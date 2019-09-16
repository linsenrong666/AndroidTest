package linsr.com.androidtest.loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.CursorLoader;

public class UserLoader extends CursorLoader {
    public UserLoader(@NonNull Context context) {
        super(context);
    }
}
