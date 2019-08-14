package linsr.com.androidtest.old;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import linsr.com.androidtest.R;

/**
 * Description
 *
 * @author Linsr 2019/2/25 下午2:23
 */
public class ToastUtils {

    static Toast mMyToast;

    public static void showMyMsg(Context mActivity, String msg, Integer width, Integer height) {
        try {
            if (mActivity == null) {
                return;
            }
            View v = View.inflate(mActivity, R.layout.toast_view, null);

            TextView mtoastContent =  v.findViewById(R.id.toast_prompt);
            if (width != null ) {
                ViewGroup.LayoutParams params = mtoastContent.getLayoutParams();
                params.width = width;
                mtoastContent.setLayoutParams(params);
            }
            mMyToast = new Toast(mActivity);
            mMyToast.setDuration(Toast.LENGTH_SHORT);
            mMyToast.setGravity(Gravity.CENTER, 0, 0);
            mMyToast.setView(v);
            mtoastContent.setText(msg);
            mMyToast.show();
        } catch (Exception e) {
            mMyToast = null;
        }
    }
}
