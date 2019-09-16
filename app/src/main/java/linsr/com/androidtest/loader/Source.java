package linsr.com.androidtest.loader;

import android.net.Uri;

/**
 * Description
 *
 * @author Linsr 2019/5/24 下午8:27
 */
public class Source {

    public static final String AUTHORITY = "linsr.com.aaaa";

    public static class User implements UserColumn {
        public static final String PATH = "User";
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    }
}
