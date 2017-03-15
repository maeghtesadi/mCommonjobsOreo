package com.oreo.mcommonjobs.Controllers;

import android.content.Context;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.oreo.mcommonjobs.Activtity.NavigationActivityForJobProvider;
import com.oreo.mcommonjobs.Activtity.NavigationActivityForJobSeeker;
import com.oreo.mcommonjobs.Activtity.SelectUserTypeActivity;
import com.oreo.mcommonjobs.Models.DatabaseTasks.RegisterAccount;
import com.oreo.mcommonjobs.Session.PersonSession;
import com.oreo.mcommonjobs.Session.RequestSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jason on 2017-02-26.
 */

public class UserController {


    // Context context;
    // UserExists userexsits;


/*
    public void checkifExsists(String email, Context c) {


        UserExists userexsits = new UserExists(c);
        userexsits.execute("login", email);
    }
*/

    public void checkifExsists(String email, final Context c) {
        String loginLink = "http://192.168.0.104/login.php";
        final String email2 = email;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginLink, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                PersonSession instance = PersonSession.getInstance();
                try {
                    JSONObject values = new JSONObject(response);

                    instance.setTypeofuser(values.getString("typeofuser"));


                    if(instance.getTypeofuser().equals("jobprovider")){
                        Intent i = new Intent(c, NavigationActivityForJobProvider.class);
                        c.startActivity(i);

                    }

                    if(instance.getTypeofuser().equals("jobseeker")){
                        Intent i = new Intent(c, NavigationActivityForJobSeeker.class);
                        c.startActivity(i);

                    }

                    if(instance.getTypeofuser()==null){
                        Intent z = new Intent(c, SelectUserTypeActivity.class);
                        c.startActivity(z);

                    }









                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }} , new Response.ErrorListener( ){
                public void onErrorResponse(VolleyError error){


                }

            }
        ){

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
               params.put("email",  email2);

                return params;
            }
        };


        RequestSingleton.getInstance(c).addToRequestQueue(stringRequest);




    }


    public void registerAccount(String firstname, String lastname, String email, String typeofuser, Context c) {

        RegisterAccount reg = new RegisterAccount(c);
        reg.execute("insert", firstname, lastname, email, typeofuser);

    }


}
