package com.oreo.mcommonjobs.Models;

import android.content.Context;

import com.oreo.mcommonjobs.DatabaseTasks.Addjob;

/**
 * Created by jason on 2017-03-02.
 */

public class JobProvider {









public void createPosting(String type, String description, Context c){

    Addjob addjob = new Addjob(c);
    addjob.execute("addjob", type, description);



}








}
