package linsr.com.androidtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import linsr.com.androidtest.dispatch.DispatchTestActivity;
import linsr.com.androidtest.receiver.ReceiverTestActivity;

/**
 * Description
 *
 * @author Linsr 2019/8/14 上午10:42
 */
public class EntryActivity extends AppCompatActivity {

    private List<Menu> mMenus = new ArrayList<>();
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        mContext = this;
        RecyclerView mRecyclerView = findViewById(R.id.entry_list_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        EAdapter mEAdapter = new EAdapter();
        mRecyclerView.setAdapter(mEAdapter);
        initData();
        mEAdapter.notifyDataSetChanged();
    }

    private void initData() {
        mMenus.add(new Menu("广播测试", ReceiverTestActivity.class));
        mMenus.add(new Menu("事件分发", DispatchTestActivity.class));
    }


    class Menu {
        public String title;
        public Class mClass;

        Menu(String title, Class aClass) {
            this.title = title;
            mClass = aClass;
        }
    }

    class EAdapter extends RecyclerView.Adapter<EAdapter.Holder> {

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new Holder(new Button(mContext));
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int i) {
            final Menu menu = mMenus.get(i);
            holder.mButton.setText(menu.title);
            holder.mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, menu.mClass);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mMenus.size();
        }

        class Holder extends RecyclerView.ViewHolder {
            private Button mButton;

            Holder(@NonNull View itemView) {
                super(itemView);
                mButton = (Button) itemView;
            }
        }
    }
}
