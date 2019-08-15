package linsr.com.androidtest.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import linsr.com.androidtest.Utils;

/**
 * Description
 *
 * @author Linsr 2019/8/15 下午8:15
 */
public abstract class BaseFragment extends Fragment {
    private String TAG = "BaseFragment";

    private View mRoot;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(getLayoutId(), container, false);
        return mRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    protected abstract int getLayoutId();

    protected abstract void init();

    protected void initTAG(Class c) {
        TAG = c.getSimpleName();
    }

    protected void v(String text, String... content) {
        Utils.v(TAG, text, content);
    }

    protected void d(String text, String... content) {
        Utils.d(TAG, text, content);
    }

    protected void i(String text, String... content) {
        Utils.i(TAG, text, content);
    }

    protected void w(String text, String... content) {
        Utils.w(TAG, text, content);
    }

    protected void e(String text, String... content) {
        Utils.e(TAG, text, content);
    }

    protected <T extends View> T find(int id) {
        return mRoot.findViewById(id);
    }
}
