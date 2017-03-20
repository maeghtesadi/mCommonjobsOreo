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
import com.oreo.mcommonjobs.Session.PersonSession;
import com.oreo.mcommonjobs.Session.RequestSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * User Controller
 *
 * @author Jason
 */
public class UserController {

    /**
     * Makes a volley request, expects String as response, checks if user exsists, if so launches appropiate navigationactivity, else sends user to sign up page.
     *
     * @param email
     * @param c
     */
    public void checkifExsists(String email, final Context c) {
        String loginLink = "http://192.168.0.104/login.php";
        final String email2 = email;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginLink, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                PersonSession instance = PersonSession.getInstance();
                try {
                    if(response.equals("noUser")){
                        Intent z = new Intent(c, SelectUserTypeActivity.class);
                        c.startActivity(z);
                    }else {
                        JSONObject values = new JSONObject(response);

                        instance.setTypeOfUser(values.getString("typeofuser"));

                        if (instance.getTypeOfUser().equals("jobprovider")) {
                            Intent i = new Intent(c, NavigationActivityForJobProvider.class);
                            c.startActivity(i);
                        }

                        if (instance.getTypeOfUser().equals("jobseeker")) {
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


    public void registerAccount(String firstName, String lastName, String email, String typeOfUser, final Context c) {
        String loginLink = "http://192.168.0.104/insert.php";
        final String email2 = email;
        final String firstname2=firstName;
        final String lastname2=lastName;
        final String typeofuser2=typeOfUser;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginLink, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                PersonSession instance = PersonSession.getInstance();
                instance.setTypeOfUser(typeofuser2);

                if (instance.getTypeOfUser().equals("jobprovider")) {
                    Intent i = new Intent(c, NavigationActivityForJobProvider.class);
                    c.startActivity(i);
                }

                if (instance.getTypeOfUser().equals("jobseeker")) {
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
