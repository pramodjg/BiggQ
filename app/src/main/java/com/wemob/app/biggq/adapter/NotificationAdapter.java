package com.wemob.app.biggq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import com.wemob.app.biggq.R;
import com.wemob.app.biggq.data.NotificationData;

/**
 * Created by admin on 8/31/2017.
 */

public class NotificationAdapter extends BaseAdapter
{
    ArrayList<NotificationData> data;
    Context mcontext;
    public NotificationAdapter(Context context, ArrayList<NotificationData> value)
    {
        mcontext=context;
        data=value;

    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mcontext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.notification_list_row, null, false);
        return rowView;

    }
}
