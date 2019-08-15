package linsr.com.androidtest.dispatch.chain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import linsr.com.androidtest.R;

/**
 * Description
 *
 * @author Linsr 2019/8/15 上午10:52
 */
public class ChainTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chain_test);
    }

    public void start(View view) {
        ParentNode parentNode = new ParentNode();
        List<ChildNode> childNodes = new ArrayList<>();
        ChildNode childNode = new ChildNode();
        childNodes.add(childNode);
        parentNode.setChildNodes(childNodes);
        parentNode.onDispatch();
    }
}
