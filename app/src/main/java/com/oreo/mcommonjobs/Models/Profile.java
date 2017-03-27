package com.oreo.mcommonjobs.Models;

/**
 * Created by jason on 2017-03-26.
 */

public class Profile {

    String typeofprofile;

    public Profile(String type) {
        this.typeofprofile = type;
    }

    public String getType() {
        return typeofprofile;
    }


    public void setType(String type) {
        this.typeofprofile = type;
    }
}



