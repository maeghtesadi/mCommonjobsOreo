package com.oreo.mcommonjobs.Controllers;

import android.content.Context;

import com.oreo.mcommonjobs.Models.DatabaseTasks.Addjob;

/**
 * Created by jason on 2017-03-02.
 */

public class JobProviderController {


    public void createPosting(String type, String description, Context c) {

        Addjob addjob = new Addjob(c);
        addjob.execute("addjob", type, description);


    }


}
