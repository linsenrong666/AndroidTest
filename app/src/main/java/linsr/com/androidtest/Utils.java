package linsr.com.androidtest;

import android.app.Activity;
import android.content.Intent;

/**
 * Description
 *
 * @author Linsr 2019/8/15 上午10:53
 */
public class Utils {

    public static void toActivity(Activity a, Class c) {
        Intent i = new Intent(a, c);
        a.startActivity(i);
    }
}
