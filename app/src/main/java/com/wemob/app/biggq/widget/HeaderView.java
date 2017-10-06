package com.wemob.app.biggq.widget;

/**
 * Created by admin on 9/7/2017.
 */
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.wemob.app.biggq.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by anton on 11/12/15.
 */

public class HeaderView extends LinearLayout {

    @Bind(R.id.txtname)
    TextView name;

    @Bind(R.id.txtplace)
    TextView place;

    @Bind(R.id.profileImageView)
    ImageView imgProfile;

    public HeaderView(Context context) {
        super(context);
    }

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void bindTo(String name, String lastSeen,String imglink) {
        this.name.setText(name);
        this.place.setText(lastSeen);
        if(!imglink.equals("NA")) {
            Glide.with(getContext()).load(imglink).asBitmap().centerCrop().into(new BitmapImageViewTarget(this.imgProfile) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    imgProfile.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
    }

    public void setTextSize(float size) {
        name.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }
}