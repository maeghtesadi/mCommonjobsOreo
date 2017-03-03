package com.oreo.mcommonjobs.Models;

import android.content.Context;

import com.oreo.mcommonjobs.DatabaseTasks.UserExists;

/**
 * Created by jason on 2017-02-26.
 */

public class Users {


    Context context;
    UserExists userexsits;


public void checkifExsists(String email , Context c){



        userexsits = new UserExists(c);

        userexsits.execute("login", email);


}



}
