package com.oreo.mcommonjobs.Controllers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.oreo.mcommonjobs.Session.RequestSingleton;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jason on 2017-03-02.
 */

public class JobProviderController {

/*
    public void createPosting(String type, String description, Context c) {

        Addjob addjob = new Addjob(c);
        addjob.execute("addjob", type, description);


    }
*/


    public void createPosting(final String type, final String description, final Context c) {


        String loginLink = "http://192.168.2.11/mcommonjobs/addjob.php";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginLink, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                



            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {


            }

        }
        ) {

            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("description", description);
                params.put("typeofjob", type);


                return params;
            }
        };


        RequestSingleton.getInstance(c).addToRequestQueue(stringRequest);


    }


}
