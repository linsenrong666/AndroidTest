package linsr.com.androidtest;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import java.util.Random;
import java.util.UUID;

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

    public static void v(String TAG, String text, Object... content) {
        Log.v(TAG, format(text,content));
    }

    public static void d(String TAG, String text, Object... content) {
        Log.d(TAG, format(text,content));
    }

    public static void i(String TAG, String text, Object... content) {
        Log.i(TAG, format(text,content));
    }

    public static void w(String TAG, String text, Object... content) {
        Log.w(TAG, format(text,content));
    }

    public static void e(String TAG, String text, Object... content) {
        Log.e(TAG, format(text,content));
    }

    private static String format(String text, Object... content) {
        String format = String.format(text, content);
        format = "[" + Thread.currentThread().getName() + "]" + format;
        return format;
    }

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

    private static final Random random = new Random();

    public static int randomInt(int i){
        return random.nextInt(i);
    }
}
