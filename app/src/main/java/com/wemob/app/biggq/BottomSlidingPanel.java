package com.wemob.app.biggq;

import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;

/**
 * Created by admin on 9/3/2017.
 */
public class BottomSlidingPanel extends BottomSheetDialogFragment {

    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragmentbottom, null);
        dialog.setContentView(contentView);
    }
}