package com.oreo.mcommonjobs.Controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.oreo.mcommonjobs.Models.JobSeekerRating;
import com.oreo.mcommonjobs.Models.ReviewableJobProvider;
import com.oreo.mcommonjobs.Models.ReviewableJobSeeker;
import com.oreo.mcommonjobs.Models.URLPath;
import com.oreo.mcommonjobs.Session.RequestSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sam on 2017-04-01.
 */

public class JobSeekerRatingController {

    public void registerJobSeekerRating(final String providerEmail, final String seekerEmail, final int rating1, final int rating2, final int rating3, final String comment, final Context context){

        Map<String ,String> params = new HashMap<String, String>();
        params.put("providerEmail", providerEmail);
        params.put("seekerEmail", seekerEmail);
        params.put("rating1", Integer.toString(rating1));
        params.put("rating2", Integer.toString(rating2));
        params.put("rating3", Integer.toString(rating3));
        params.put("comment", comment);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLPath.addSeekerRating, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, "Job seeker rating registered.", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Handle Error.
            }
        });
        request.setShouldCache(false);
        RequestSingleton.getInstance(context).addToRequestQueue(request);
    }

    public List<JobSeekerRating> getJobSeekerRatingsList(final String seekerEmail, Context context){

        final List<JobSeekerRating> jobSeekerRatingList = new ArrayList<JobSeekerRating>();

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.getSeekerRatings, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonJobSeekerRatingsArray = response.getJSONArray("seekerRatings");

                    for(int i = 0; i < jsonJobSeekerRatingsArray.length(); i++){
                        JSONObject ratingCurrentPosition = jsonJobSeekerRatingsArray.getJSONObject(i);

                        String displayName = ratingCurrentPosition.getString("displayName");
                        String firstName = ratingCurrentPosition.getString("firstName");
                        String lastName = ratingCurrentPosition.getString("lastName");
                        String email = ratingCurrentPosition.getString("email");
                        int raterId = ratingCurrentPosition.getInt("raterid");
                        int jobSeekerId = ratingCurrentPosition.getInt("jobseekerid");
                        int rating1 = ratingCurrentPosition.getInt("rating1");
                        int rating2 = ratingCurrentPosition.getInt("rating2");
                        int rating3 = ratingCurrentPosition.getInt("rating3");
                        double averageRating = ratingCurrentPosition.getDouble("averagerating");
                        String comment = ratingCurrentPosition.getString("comment");

                        JobSeekerRating jobSeekerRating = new JobSeekerRating(displayName, firstName, lastName, email, raterId, jobSeekerId, rating1, rating2, rating3, averageRating, comment);

                        jobSeekerRatingList.add(jobSeekerRating);
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Unable to parse json array");
            }
        }){
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();
                params.put("seekerEmail", seekerEmail);

                return params;
            }

        };

        RequestSingleton.getInstance(context).addToRequestQueue(jsonRequest);

        return jobSeekerRatingList;
    }

    public List<ReviewableJobProvider> getReviewableJobProviders(final String seekerEmail, Context context){

        final List<ReviewableJobProvider> reviewableJobProviderList= new ArrayList<ReviewableJobProvider>();

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.getProvidersForSeeker, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonReviewableJobproviderArray = response.getJSONArray("providersForSeeker");

                    for(int i = 0; i < jsonReviewableJobproviderArray.length(); i++){
                        JSONObject reviewableJobProviderCurrentPosition = jsonReviewableJobproviderArray.getJSONObject(i);

                        String displayName = reviewableJobProviderCurrentPosition.getString("displayName");
                        String firstName = reviewableJobProviderCurrentPosition.getString("firstName");
                        String lastName = reviewableJobProviderCurrentPosition.getString("lastName");
                        String email = reviewableJobProviderCurrentPosition.getString("posterEmail");

                        ReviewableJobProvider reviewableJobProvider = new ReviewableJobProvider(displayName, firstName, lastName, email);

                        reviewableJobProviderList.add(reviewableJobProvider);
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Unable to parse json array");
            }
        }){
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();
                params.put("seekerEmail", seekerEmail);

                return params;
            }

        };

        RequestSingleton.getInstance(context).addToRequestQueue(jsonRequest);

        return reviewableJobProviderList;
    }
}
