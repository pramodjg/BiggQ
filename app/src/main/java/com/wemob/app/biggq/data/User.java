package com.wemob.app.biggq.data;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by admin on 9/14/2017.
 */

public class User implements Serializable {
    private String name;
    private String email;
    private String about;
    private String walletAmount;
    private String profilePic;
    private String id;
    private int login_method;
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setWalletAmount(String walletAmount) {
        this.walletAmount = walletAmount;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAbout() {
        return about;
    }

    public String getWalletAmount() {
        return walletAmount;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public String getId() {
        return id;
    }

    public int getLogin_method() {
        return login_method;
    }

    public void setLogin_method(int login_method) {
        this.login_method = login_method;
    }
}
