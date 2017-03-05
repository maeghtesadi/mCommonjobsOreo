package com.oreo.mcommonjobs.Controllers;

import android.content.Context;

import com.oreo.mcommonjobs.Models.DatabaseTasks.RegisterAccount;
import com.oreo.mcommonjobs.Models.DatabaseTasks.UserExists;

/**
 * Created by jason on 2017-02-26.
 */

public class UserController {



   // Context context;
  // UserExists userexsits;



    public void checkifExsists(String email , Context c){

        UserExists userexsits = new UserExists(c);
        userexsits.execute("login", email);
}

    public void registerAccount(String firstname, String lastname,String email,String typeofuser, Context c){

        RegisterAccount reg = new RegisterAccount(c);
        reg.execute("insert",firstname, lastname, email,typeofuser);

    }



}
