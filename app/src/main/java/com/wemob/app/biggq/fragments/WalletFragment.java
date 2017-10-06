package com.wemob.app.biggq.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wemob.app.biggq.R;

/**
 * Created by admin on 8/25/2017.
 */

public class WalletFragment extends Fragment   {

    Button btnaddmoney;
    WalletInterface mcallback;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mcallback = (WalletInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View contentview=inflater.inflate(R.layout.wallet_layout,null);
        return contentview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnaddmoney=(Button)view.findViewById(R.id.btnAddMoney);
        btnaddmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddMoneyFragment();
            }
        });
    }

    private void showAddMoneyFragment() {

        mcallback.addMoneySelected();
    }


    public interface WalletInterface
    {
        public void addMoneySelected();

    }
}
