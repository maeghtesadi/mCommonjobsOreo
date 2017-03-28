package com.oreo.mcommonjobs.Controllers;

import android.content.Context;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.oreo.mcommonjobs.Activtity.AddProfileActivity;
import com.oreo.mcommonjobs.Activtity.NavigationActivityForJobProvider;
import com.oreo.mcommonjobs.Activtity.SelectUserTypeActivity;
import com.oreo.mcommonjobs.Activtity.ViewYourProfilesActivity;
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
    public void checkifExsists(final String email, final Context context) {
        String loginLink = "http://192.168.0.104/login.php";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginLink, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                PersonSession instance = PersonSession.getInstance();
                try {
                    if(response.equals("noUser")){
                        Intent z = new Intent(context, SelectUserTypeActivity.class);  // this is fine, but after they select their type, if its jobseeker send them to profile page
                        context.startActivity(z);
                    }else {
                        JSONObject values = new JSONObject(response);

                        instance.setTypeOfUser(values.getString("typeofuser"));

                        if (instance.getTypeOfUser().equals("jobprovider")) {
                            Intent i = new Intent(context, NavigationActivityForJobProvider.class);  //   also this part is fine I think
                            context.startActivity(i);
                        }

                        if (instance.getTypeOfUser().equals("jobseeker")) {

                          //send them to their profile select page first, where they can add their profiles, profiles corrospond to skillz
                            //Intent i = new Intent(c, NavigationActivityForJobSeeker.class);
                            Intent i = new Intent (context, ViewYourProfilesActivity.class);
                            context.startActivity(i);
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
               params.put("email",  email);

                return params;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(stringRequest);
    }


    /**
     * This method allows a firstime user to register their account
     *
     * @param firstName - first name of user
     * @param lastName - last name of user
     * @param email - email of the user
     * @param typeOfUser
     * @param context
     */

    public void registerAccount(final String firstName, final String lastName, final String email, final String typeOfUser, final Context context) {
        String loginLink = "http://192.168.0.104/insert.php";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginLink, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                PersonSession instance = PersonSession.getInstance();
                instance.setTypeOfUser(typeOfUser);

                if (instance.getTypeOfUser().equals("jobprovider")) {
                    Intent i = new Intent(context, NavigationActivityForJobProvider.class);
                    context.startActivity(i);
                }

                if (instance.getTypeOfUser().equals("jobseeker")) {
                    //Intent i = new Intent(c, NavigationActivityForJobSeeker.class); // THIS ONE WORKS
                    Intent i = new Intent (context, AddProfileActivity.class);

                    context.startActivity(i);
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {

            }
        }
        ) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("firstname", firstName);
                params.put("lastname", lastName);
                params.put("typeofuser", typeOfUser);

                return params;
            }
        };

        RequestSingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
}
