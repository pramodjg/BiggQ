package com.wemob.app.biggq;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDelegate;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import com.wemob.app.biggq.fragments.ProfilePage;
import com.wemob.app.biggq.fragments.WalletFragment;

/**
 * Created by admin on 8/23/2017.
 */
@Layout(R.layout.drawer_item)
public class DrawerMenuItem {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    public static final int DRAWER_MENU_ITEM_PROFILE = 1;
    public static final int DRAWER_MENU_ITEM_WALLET = 2;
    public static final int DRAWER_MENU_ITEM_PRIVACY = 3;
    public static final int DRAWER_MENU_ITEM_TERMS = 4;
    public static final int DRAWER_MENU_ITEM_INVITE = 5;
    public static final int DRAWER_MENU_ITEM_LOGOUT = 6;
    public static final int DRAWER_MENU_ITEM_HOME = 8;
    public static final int DRAWER_MENU_ITEM_ABOUT=7;

    ProfilePage profilefragment;
    WalletFragment walletfragment;
    private int mMenuPosition;
    private HomePageWrapper mContext;
    private DrawerCallBack mCallBack;

    @View(R.id.itemNameTxt)
    private TextView itemNameTxt;

    @View(R.id.itemIcon)
    private ImageView itemIcon;

    public DrawerMenuItem(Context context, int menuPosition) {
        mContext = (HomePageWrapper)context;
        mMenuPosition = menuPosition;
        mCallBack= (HomePageWrapper)context;
    }

    @Resolve
    private void onResolved() {
        switch (mMenuPosition){

            case DRAWER_MENU_ITEM_HOME:
                itemNameTxt.setText("Home");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_home));
                break;
            case DRAWER_MENU_ITEM_PROFILE:
                itemNameTxt.setText("Profile");
                
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_profile));
                break;
            case DRAWER_MENU_ITEM_WALLET:
                itemNameTxt.setText("Wallet");
               itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_wallet));
                break;
            case DRAWER_MENU_ITEM_PRIVACY:
                itemNameTxt.setText("Privacy");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_padlock));
                break;
            case DRAWER_MENU_ITEM_TERMS:
                itemNameTxt.setText("Terms and Conditions");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_script));
                break;
            case DRAWER_MENU_ITEM_INVITE:
                itemNameTxt.setText("Invite and Earn");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_add_user));
                break;
            case DRAWER_MENU_ITEM_ABOUT:
                itemNameTxt.setText("About");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_about));
                break;
            case DRAWER_MENU_ITEM_LOGOUT:
                itemNameTxt.setText("Logout");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_logout));
                break;

        }
    }

    @Click(R.id.mainView)
    private void onMenuItemClick(){
        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_HOME:
                //  Toast.makeText(mContext, "Profile", Toast.LENGTH_SHORT).show();
                // showProfilePage();
                if(mCallBack != null)mCallBack.onHomeMenuSelected();
                break;
            case DRAWER_MENU_ITEM_PROFILE:
              //  Toast.makeText(mContext, "Profile", Toast.LENGTH_SHORT).show();
               // showProfilePage();
                if(mCallBack != null)mCallBack.onProfileMenuSelected();
                break;
            case DRAWER_MENU_ITEM_WALLET:

                if(mCallBack != null)mCallBack.onWalletMenuSelected();
                break;
            case DRAWER_MENU_ITEM_PRIVACY:
                //Toast.makeText(mContext, "Privacy", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onPrivacyMenuSelected();
                break;
            case DRAWER_MENU_ITEM_TERMS:
               // Toast.makeText(mContext, "Terms and Conditions", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onTermsMenuSelected();
                break;
            case DRAWER_MENU_ITEM_INVITE:
               // Toast.makeText(mContext, "Invite and Earn", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onInviteMenuSelected();
                break;
            case DRAWER_MENU_ITEM_ABOUT:
                //Toast.makeText(mContext, "About", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onAboutMenuSelected();
                break;
            case DRAWER_MENU_ITEM_LOGOUT:
                //Toast.makeText(mContext, "Log Out", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onLogoutMenuSelected();
                break;

        }
    }



    public void setDrawerCallBack(DrawerCallBack callBack) {
        mCallBack = callBack;
    }

    public interface DrawerCallBack{
        void onProfileMenuSelected();
        void onWalletMenuSelected();
        void onPrivacyMenuSelected();
        void onTermsMenuSelected();
        void onInviteMenuSelected();
        void onLogoutMenuSelected();
        void onAboutMenuSelected();
        void onHomeMenuSelected();

    }
}