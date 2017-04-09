package com.oreo.mcommonjobs.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.oreo.mcommonjobs.Models.URLPath;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.PersonSession;
import com.oreo.mcommonjobs.Session.RequestSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the class for the Navigation Activity belonging to a JobProvider.
 *
 * @author jason
 * @author kimcodes
 */
public class NavigationActivityForJobProvider extends AppCompatActivity {


    Button btnAddJob ,viewApplicants , account, btnRating;
    PersonSession personInstance = PersonSession.getInstance();


    /**
     * Initializes the NavigationActivity for a JobProvider.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_jobprovider);
        btnAddJob = (Button) findViewById(R.id.btn_addjob);
        btnRating = (Button) findViewById(R.id.btn_rating);
        viewApplicants = (Button) findViewById(R.id.btn_view_applicants);
        account = (Button) findViewById(R.id.btn_account);

        getDisplayName_PhoneNumber(personInstance.getEmail(), getApplicationContext());

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AccountActivity.class);
                startActivity(i);

            }
        });


        btnAddJob.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateJobPostActivity.class);
                startActivity(i);
            }
        });


        viewApplicants.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v ){
                Intent i = new Intent(getApplicationContext(), ViewApplicants.class);
                startActivity(i);
            }
        });

        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ProviderRatingMenuActivity.class);
                startActivity(i);
            }
        });

    }


    /**
     * This method checks gets the displayName and phone number of jobprovider from database
     * @param email - email of the jobprovider
     * @param context
     */



    public void getDisplayName_PhoneNumber(final String email, Context context){

        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.getDisplayName, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ;
                        try {
                            PersonSession personInstance = PersonSession.getInstance();
                            personInstance.setDisplayName(response.getString("displayname"));

                            if(response.getString("phoneNumber").equals("None")){
                                personInstance.setPhoneNumber("Add your number");

                            }else{
                                personInstance.setPhoneNumber(response.getString("phoneNumber"));
                                String test = response.getString("phoneNumber");
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
        });
        RequestSingleton.getInstance(context).addToRequestQueue(jsonRequest);


    }




}
