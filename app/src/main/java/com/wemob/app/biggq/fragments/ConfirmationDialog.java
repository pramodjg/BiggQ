package com.wemob.app.biggq.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wemob.app.biggq.R;

/**
 * Created by admin on 9/6/2017.
 */

public class ConfirmationDialog extends AppCompatDialogFragment {
    String payment;
    public static ConfirmationDialog newInstance(String title,String amount) {
        ConfirmationDialog frag = new ConfirmationDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("amount", amount);
        frag.setArguments(args);
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        payment=getArguments().getString("amount");
        return inflater.inflate(R.layout.confirmation_popup, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        TextView txtpayment=(TextView)view.findViewById(R.id.txtamountvalue);
        txtpayment.setText(">The Sum of Rs."+payment+"/- will be deducted from your wallet.  Click Confirm to continue.");

    }
}

