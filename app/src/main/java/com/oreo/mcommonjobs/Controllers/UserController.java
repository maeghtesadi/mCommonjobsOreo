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
import com.oreo.mcommonjobs.Activtity.TwilioAuthenticationActivity;
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
        String loginLink = "http://[IP address]/mcommonjobs/login.php";
        final String email2 = email;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginLink, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                PersonSession instance = PersonSession.getInstance();
               // instance.setTypeofuser("");
                try {


                    if(response.equals("noUser")){
                        Intent z = new Intent(c, TwilioAuthenticationActivity.class);
                        c.startActivity(z);

                    }else {


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


    public void registerAccount(String firstname, String lastname, String email, String typeofuser, final Context c) {


        String loginLink = "http://[IP address]/mcommonjobs/insert.php";
        final String email2 = email;
        final String firstname2=firstname;
        final String lastname2=lastname;
        final String typeofuser2=typeofuser;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginLink, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                PersonSession instance = PersonSession.getInstance();
                instance.setTypeofuser(typeofuser2);

                if (instance.getTypeofuser().equals("jobprovider")) {
                    Intent i = new Intent(c, NavigationActivityForJobProvider.class);
                    c.startActivity(i);

                }


                if (instance.getTypeofuser().equals("jobseeker")) {
                    Intent i = new Intent(c, NavigationActivityForJobSeeker.class);
                    c.startActivity(i);

                }






            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {


            }

        }
        ) {

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email2);
                params.put("firstName", firstname2);
                params.put("lastName", lastname2);
                params.put("typeofuser", typeofuser2);

                return params;
            }
        };


        RequestSingleton.getInstance(c).addToRequestQueue(stringRequest);


    }





}
