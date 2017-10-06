package com.wemob.app.biggq;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.wemob.app.biggq.apiHandler.ApiLinks;

/**
 * Created by admin on 8/23/2017.
 */

@NonReusable
@Layout(R.layout.drawer_header)
public class DrawerHeader {

    @View(R.id.profileImageView)
    private ImageView profileImage;

    @View(R.id.txtusername)
    private TextView txtusername;

    @View(R.id.txtplace)
    private TextView txtplace;

    private String username;
    private String placeofuser;
    private String imgurl;

    private Context mContext;

    DrawerHeader(Context context,String user,String place,String imglink)
    {
        username=user;
        placeofuser=place;
        imgurl=imglink;
        mContext=context;
    }

    @Resolve
    private void onResolved() {
        txtusername.setText(username);
        txtplace.setText(placeofuser);
        Glide.with(mContext).load(imgurl).asBitmap().centerCrop().into(new BitmapImageViewTarget(profileImage) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                profileImage.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    public TextView getTxtplace() {
        return txtplace;
    }

    public void setTxtplace(TextView txtplace) {
        this.txtplace = txtplace;
    }

    public TextView getTxtusername() {
        return txtusername;
    }

    public void setTxtusername(TextView txtusername) {
        this.txtusername = txtusername;
    }

    public ImageView getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ImageView profileImage) {
        this.profileImage = profileImage;
    }
}
