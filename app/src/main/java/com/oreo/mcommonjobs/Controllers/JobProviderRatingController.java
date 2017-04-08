package com.oreo.mcommonjobs.Controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.oreo.mcommonjobs.Models.ReviewableJobSeeker;
import com.oreo.mcommonjobs.Models.JobProviderRating;
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

public class JobProviderRatingController {

    /**
     * This method creates a job provider rating and sends it to be entered into the database.
     * Makes a volley request, sends job information for server to handle adding a job provider rating to database
     *
     * @param seekerEmail
     * @param providerEmail
     * @param attitudeRating
     * @param helpfulnessRating
     * @param workEnvironmentRating
     * @param comment
     * @param context
     */
    public void registerJobProviderRating(final String seekerEmail, final String providerEmail, final int attitudeRating, final int helpfulnessRating, final int workEnvironmentRating, final String comment, final Context context){

        Map<String, String> params = new HashMap<String, String>();
        params.put("seekerEmail", seekerEmail);
        params.put("providerEmail", providerEmail);
        params.put("rating1", Integer.toString(attitudeRating));
        params.put("rating2", Integer.toString(helpfulnessRating));
        params.put("rating3", Integer.toString(workEnvironmentRating));
        params.put("comment", comment);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLPath.addProviderRating, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, "Rating registered.", Toast.LENGTH_SHORT).show();
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

    /**
     * This method returns a list of job provider ratings.
     *
     * @param providerEmail
     * @param context
     * @return list of job provider ratings
     */
    public List<JobProviderRating> getJobProviderRatings(final String providerEmail, Context context){

        final List<JobProviderRating> jobProviderRatingList = new ArrayList<JobProviderRating>();

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.getProviderRatings, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonJobProviderRatingsArray = response.getJSONArray("jobprovider_ratings");

                    for(int i = 0; i < jsonJobProviderRatingsArray.length(); i++){
                        JSONObject ratingCurrentPosition = jsonJobProviderRatingsArray.getJSONObject(i);

                        String displayName = ratingCurrentPosition.getString("displayName");
                        String firstName = ratingCurrentPosition.getString("firstName");
                        String lastName = ratingCurrentPosition.getString("lastName");
                        String email = ratingCurrentPosition.getString("email");
                        int raterId = ratingCurrentPosition.getInt("raterid");
                        int jobProviderId = ratingCurrentPosition.getInt("jobproviderid");
                        int rating1 = ratingCurrentPosition.getInt("rating1");
                        int rating2 = ratingCurrentPosition.getInt("rating2");
                        int rating3 = ratingCurrentPosition.getInt("rating3");
                        double averageRating = ratingCurrentPosition.getDouble("averagerating");
                        String comment = ratingCurrentPosition.getString("comment");

                        JobProviderRating jobProviderRating = new JobProviderRating(displayName, firstName, lastName, email, raterId, jobProviderId, rating1, rating2, rating3, averageRating, comment);

                        jobProviderRatingList.add(jobProviderRating);
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
                params.put("providerEmail", providerEmail);

                return params;
            }
        };

        RequestSingleton.getInstance(context).addToRequestQueue(jsonRequest);

        return jobProviderRatingList;
    }

    /**
     *This method returns a list of reviewable job seekers.
     *
     * @param providerEmail
     * @param context
     * @return list of reviewable job seekers
     */
    public List<ReviewableJobSeeker> getReviewableJobSeekers(final String providerEmail, Context context) {

        final List<ReviewableJobSeeker> reviewableJobSeekerList = new ArrayList<ReviewableJobSeeker>();

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.getHiredSeekersForJobProvider, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray reviewableJobProviderArray = response.getJSONArray("applications");

                    for (int i = 0; i < reviewableJobProviderArray.length(); i++) {
                        JSONObject seekerCurrentPosition = reviewableJobProviderArray.getJSONObject(i);

                        String displayName = seekerCurrentPosition.getString("displayName");
                        String firstName = seekerCurrentPosition.getString("firstName");
                        String lastName = seekerCurrentPosition.getString("lastName");
                        String email = seekerCurrentPosition.getString("email");

                        ReviewableJobSeeker reviewableJobSeeker = new ReviewableJobSeeker(displayName, firstName, lastName, email);

                        reviewableJobSeekerList.add(reviewableJobSeeker);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Unable to parse json array");
            }
        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("providerEmail", providerEmail);

                return params;
            }
        };

        RequestSingleton.getInstance(context).addToRequestQueue(jsonRequest);

        return reviewableJobSeekerList;
    }
}
