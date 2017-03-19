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
 * JobSeekerController class that handles all functionality related to jobs
 * for a user of type JobProvider.
 *
 * @author Jason
 */
public class JobProviderController {


    /**
     * This method creates a job posting and sends it to be entered into the database.
     *
     * @param type
     * @param description
     * @param c
     */
    public void createPosting(final String type, final String description, final Context c) {

        String loginLink = "http://xxx.xxx.x.x/mcommonjobs/addjob.php";

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
