package linsr.com.androidtest.dispatch.chain;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Linsr 2019/8/15 上午11:50
 */
public class ParentNode implements Chain {

    private List<ChildNode> mChildNodes;
    private List<ParentNode> mParentNodes;

    public void setParentNodes(List<ParentNode> parentNodes) {
        mParentNodes = parentNodes;
    }

    public void setChildNodes(List<ChildNode> childNodes) {
        mChildNodes = childNodes;
    }

    public List<ChildNode> getChildNodes() {
        if (mChildNodes == null) {
            return new ArrayList<>(0);
        }
        return mChildNodes;
    }

    public List<ParentNode> getParentNodes() {
        return mParentNodes;
    }

    @Override
    public boolean onDispatch() {
        Log.i("chain","parent onDispatch");
        if (!onIntercept()) {
            boolean result = true;
            int childCount = getChildNodes().size();
            for (int i = 0; i < childCount; i++) {
                ChildNode childNode = getChildNodes().get(i);
                //View 是对点进行判断的，这里简化了
                result = childNode.onDispatch();
            }
            if (!result) {
                return onTouch();
            }
        } else {
            return onTouch();
        }
        return false;
    }

    @Override
    public boolean onTouch() {
        Log.i("chain","parent onTouch");
        return false;
    }

    public boolean onIntercept() {
        Log.i("chain","parent onIntercept");
        return false;
    }
}
