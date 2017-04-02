package com.oreo.mcommonjobs.Models;

import java.util.List;

/**
 * Created by jason on 2017-03-02.
 */
public class JobSeeker extends User {

    private  Profile currentPofile;
    private List<Profile> profiles;

    public Profile getCurrentPofile() {
        return currentPofile;
    }

    public void setCurrentPofile(Profile currentPofile) {
        this.currentPofile = currentPofile;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

   public void addProfile(Profile p){

       this.profiles.add(p);
   }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }
}
