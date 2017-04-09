package com.oreo.mcommonjobs.Activtity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.oreo.mcommonjobs.Models.URLPath;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.RequestSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jason on 2017-04-08.
 */

public class ViewAcceptedApplicantProfileActivity extends AppCompatActivity {

    TextView firstNameTextView, lastNameTextView  , emailTextView, userNameTextView, phoneNumberTextView , ratingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applicants_account);

        populateLayout(getIntent().getStringExtra("username"));


    }





public void populateLayout(final String username){

    Map<String, String> params = new HashMap<String, String>();
    params.put("username", username);


    JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.getApplicantAccount, new JSONObject(params),
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    firstNameTextView = (TextView) findViewById(R.id.textView_firstname);
                    lastNameTextView = (TextView) findViewById(R.id.textView_lastname);
                    emailTextView = (TextView) findViewById(R.id.textView_email);
                    userNameTextView = (TextView) findViewById(R.id.textView_username);
                    phoneNumberTextView = (TextView) findViewById(R.id.textView_phonenumber);
                    ratingTextView = (TextView) findViewById(R.id.textView_rating);


                    try {
                        firstNameTextView.setText(response.getString("firstName").toString());
                        lastNameTextView.setText(response.getString("lastName").toString());
                        emailTextView.setText(response.getString("email").toString());
                        userNameTextView.setText(response.getString("displayName").toString());
                        phoneNumberTextView.setText(response.getString("phoneNumber").toString());
                        ratingTextView.setText( "Rating: " + response.getString("rating").toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            Log.e("Error", "Unable to parse json array");
        }
    });
    RequestSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonRequest);












}











}
