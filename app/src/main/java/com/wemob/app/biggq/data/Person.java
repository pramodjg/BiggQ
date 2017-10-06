package com.wemob.app.biggq.data;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by admin on 8/17/2017.
 */

public class Person implements Serializable {

    private String name;
    private String location;
    private Drawable profilethumb;
    private String id;
    private String askedDate;
    private String profileImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Drawable getProfilethumb() {
        return profilethumb;
    }

    public void setProfilethumb(Drawable profilethumb) {
        this.profilethumb = profilethumb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAskedDate() {
        return askedDate;
    }

    public void setAskedDate(String askedDate) {
        this.askedDate = askedDate;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
