package com.oreo.mcommonjobs.Controllers;

import android.content.Context;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.oreo.mcommonjobs.Activtity.NavigationActivityForJobProvider;
import com.oreo.mcommonjobs.Activtity.NavigationActivityForJobSeeker;
import com.oreo.mcommonjobs.Activtity.SelectUserTypeActivity;
import com.oreo.mcommonjobs.Models.URLPath;
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
    /*
    public void checkifExsists(String email, final Context c) {
        //String loginLink = "http://192.168.2.11/mcommonjobs/login.php";
        final String email2 = email;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLPath.login, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                PersonSession instance = PersonSession.getInstance();
                try {
                    if(response.has("error")){
                        Intent z = new Intent(c, SelectUserTypeActivity.class);
                        c.startActivity(z);
                    }else {
                        JSONObject values = response.getJSONObject("result");

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
    */


    public void checkIfExists(final String email, final Context context){
        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", email);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLPath.login, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            PersonSession personSession = PersonSession.getInstance();
                            if(!response.has("error")){
                                JSONObject result = response.getJSONObject("result");
                                personSession.setTypeOfUser(result.getString("type"));

                                if (personSession.getTypeOfUser().equals("jobprovider")) {
                                    Intent i = new Intent(context, NavigationActivityForJobProvider.class);
                                    context.startActivity(i);
                                }

                                if (personSession.getTypeOfUser().equals("jobseeker")) {
                                    Intent i = new Intent(context, NavigationActivityForJobSeeker.class);
                                    context.startActivity(i);
                                }

                            }
                            else{
                                Intent z = new Intent(context, SelectUserTypeActivity.class);
                                context.startActivity(z);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });
        request.setShouldCache(false);
        RequestSingleton.getInstance(context).addToRequestQueue(request);

    }

    public void registerAccount(String firstName, String lastName, String email, String typeOfUser, final Context c) {
        //String loginLink = "http://192.168.2.11/mcommonjobs/insert.php";
        final String email2 = email;
        final String firstname2=firstName;
        final String lastname2=lastName;
        final String typeofuser2=typeOfUser;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLPath.insert, new Response.Listener<String>() {
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
                params.put("firstname", firstname2);
                params.put("lastname", lastname2);
                params.put("typeofuser", typeofuser2);

                return params;
            }
        };

        RequestSingleton.getInstance(c).addToRequestQueue(stringRequest);
    }
}
