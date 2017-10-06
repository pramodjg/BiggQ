package com.wemob.app.biggq.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import com.wemob.app.biggq.R;
import com.wemob.app.biggq.adapter.NotificationAdapter;
import com.wemob.app.biggq.data.NotificationData;

/**
 * Created by admin on 8/31/2017.
 */

public class NotificationView extends Fragment {
    ListView lstearlier,lstprevious;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View containerview=inflater.inflate(R.layout.notification_layout,null);

        return containerview;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lstearlier=(ListView)view.findViewById(R.id.lstearlier);
        lstprevious=(ListView)view.findViewById(R.id.lstprevious);
        ArrayList<NotificationData> contents=new ArrayList<NotificationData>();
        contents.add(new NotificationData());
        contents.add(new NotificationData());
        contents.add(new NotificationData());
        contents.add(new NotificationData());
        contents.add(new NotificationData());

        NotificationAdapter notificationadapter=new NotificationAdapter(getContext(),contents);
        lstearlier.setAdapter(notificationadapter);
        lstprevious.setAdapter(notificationadapter);
    }
}
