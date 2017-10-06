package com.wemob.app.biggq.data;

import java.io.Serializable;

/**
 * Created by admin on 9/15/2017.
 */

public class Celebrity implements Serializable {
    //"name":"salmaan khan ","0":"salmaan khan ","email":"salmaankhan@gmail.com",
    // "1":"salmaankhan@gmail.com","profile_pic":"rsz_salmaankhan.jpg","2":"rsz_salmaankhan.jpg"
    private String name;
    private String id;
    private String email;
    private String profile_pic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
}
