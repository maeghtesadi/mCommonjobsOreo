package com.oreo.mcommonjobs.Controllers;

import android.content.Context;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.oreo.mcommonjobs.Activity.NavigationActivityForJobProvider;
import com.oreo.mcommonjobs.Activity.SelectUserTypeActivity;

import com.oreo.mcommonjobs.Models.URLPath;
import com.oreo.mcommonjobs.Activity.ViewProfilesActivity;

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
     * @param context
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
                            if(response.has("result")){
                                JSONObject result = response.getJSONObject("result");
                                personSession.setTypeOfUser(result.getString("type"));

                                if (personSession.getTypeOfUser().equals("jobprovider")) {
                                    Intent i = new Intent(context, NavigationActivityForJobProvider.class);
                                    context.startActivity(i);
                                }

                        if (personSession.getTypeOfUser().equals("jobprovider")) {
                            Intent i = new Intent(context, NavigationActivityForJobProvider.class);
                            context.startActivity(i);


                        }

                        if (personSession.getTypeOfUser().equals("jobseeker")) {

                            Intent i = new Intent (context, ViewProfilesActivity.class);
                            context.startActivity(i);
                        }
                    } else{
                                Intent z = new Intent(context, SelectUserTypeActivity.class);
                                context.startActivity(z);

                            }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }} , new Response.ErrorListener( ){
                public void onErrorResponse(VolleyError error){
                }
            }
        );
        request.setShouldCache(false);
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }



    /**
     * Makes a volley request which adds user into the database after selecting their profile type (JobSeeker or JobProvider)
     *
     * @param email
     * @param firstname
     * @param lastname
     * @param typeofuser
     * @param context
     */
    public void registerAccount(final String firstname, final String lastname, final String email, final String typeofuser, final Context context){


        // Post params to be sent to the server
        Map<String, String> params = new HashMap<String, String>();
        params.put("firstname", firstname);
        params.put("lastname", lastname);
        params.put("email", email);
        params.put("typeofuser", typeofuser);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLPath.insert, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


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






}
