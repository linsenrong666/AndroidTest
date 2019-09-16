package linsr.com.androidtest.loader;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import linsr.com.androidtest.R;

public class UserAdapter extends CursorAdapter {

    private Context mContext;

    public UserAdapter(Context context, Cursor c) {
        super(context, c, false);
        mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        Holder holder = new Holder();
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_loader_item, parent, false);
        holder.name = v.findViewById(R.id.item_loader_user_name);
        holder.gender = v.findViewById(R.id.item_loader_user_gender);
        holder.email = v.findViewById(R.id.item_loader_user_email);
        v.setTag(holder);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Holder holder = (Holder) view.getTag();
        holder.name.setText(cursor.getString(cursor.getColumnIndex(Source.User.NAME)));
        holder.gender.setText(cursor.getString(cursor.getColumnIndex(Source.User.GENDER)));
        holder.email.setText(cursor.getString(cursor.getColumnIndex(Source.User.EMAIL)));
    }

    class Holder {
        TextView name;
        TextView gender;
        TextView email;
    }

}
