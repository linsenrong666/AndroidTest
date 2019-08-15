package linsr.com.androidtest.dispatch.chain;

import android.util.Log;

/**
 * Description
 *
 * @author Linsr 2019/8/15 上午11:50
 */
public class ChildNode implements Chain {

    @Override
    public boolean onDispatch() {
        Log.d("chain","child onDispatch");
        return onTouch();
    }

    @Override
    public boolean onTouch() {
        Log.d("chain","child onTouch");
        return true;
    }
}
