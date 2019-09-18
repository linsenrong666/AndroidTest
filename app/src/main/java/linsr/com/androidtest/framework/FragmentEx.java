package linsr.com.androidtest.framework;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.jetbrains.annotations.NotNull;

import linsr.com.androidtest.R;

/**
 * Description
 *
 * @author Linsr 2018/6/16 下午2:42
 */
public abstract class FragmentEx<P extends IPresenter> extends BaseFragment implements IView {

    protected P mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter = bindPresenter();
        initLifecycleObserver(getLifecycle());
    }

    protected P bindPresenter() {
        return null;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @CallSuper
    @MainThread
    protected void initLifecycleObserver(@NotNull Lifecycle lifecycle) {
        if (mPresenter != null) {
            mPresenter.setLifecycleOwner(this);
            lifecycle.addObserver(mPresenter);
        } else {
            Log.w(TAG, "ERROR: Presenter is null !!!");
        }
    }

    @Override
    protected void setNoDataLayout() {
        View mNoDataView = LayoutInflater.from(mActivity).inflate(R.layout.common_layout_no_data,
                (ViewGroup) mNoDataLayout.getParent(), false);
        mNoDataLayout.addView(mNoDataView);
    }

    @Override
    public void showNoData() {
        mContentLayout.setVisibility(View.GONE);
        mNoDataLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoData() {
        mContentLayout.setVisibility(View.VISIBLE);
        mNoDataLayout.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String text) {
        mContentLayout.setVisibility(View.VISIBLE);
        mNoDataLayout.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy(this);
            mPresenter = null;
        }
    }
}
