package com.oreo.mcommonjobs.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.oreo.mcommonjobs.Controllers.UserController;
import com.oreo.mcommonjobs.Models.ApplicationStatus;
import com.oreo.mcommonjobs.Models.URLPath;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.PersonSession;
import com.oreo.mcommonjobs.Session.RequestSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the class for the Navigation Activity belonging to a JobSeeker.
 *
 * @author jason
 * @author kimcodes
 */
public class NavigationActivityForJobSeeker extends AppCompatActivity {


    Button btnViewAllJobs, btnProfile, btnViewProfileJobs, btnApplications, btnAccount, btnRatings, btnSharedJobs;
    private List<ApplicationStatus> listOfApplications = new ArrayList<>();
    PersonSession personInstance = PersonSession.getInstance();
    UserController userController = new UserController();

    /**
     * Initializes the NavigationActivity for a JobSeeker.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_jobseeker);
        btnViewAllJobs = (Button) findViewById(R.id.btn_addjob);
        btnRatings = (Button) findViewById(R.id.btn_ratings);
        btnProfile = (Button) findViewById(R.id.btn_profile);
        btnViewProfileJobs = (Button) findViewById(R.id.btn_view_jobs_for_your_profile);
        btnApplications = (Button) findViewById(R.id.btn_pending_applications);

        btnAccount = (Button) findViewById(R.id.btn_account);



        getDisplayName_PhoneNumber(personInstance.getEmail(), getApplicationContext());

        btnSharedJobs = (Button) findViewById(R.id.btn_shared_jobs);


        checkIfAcceptedApplication(personInstance.getEmail(),getApplicationContext());


        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AccountActivity.class);
                startActivity(i);
                overridePendingTransition(R.layout.fade_in, R.layout.fade_out);

            }
        });




        btnViewProfileJobs.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(getApplicationContext(), ViewJobsActivity.class);
               i.putExtra("EXTRA_JOB_BUTTON_CLICKED", "profileJobs");
               startActivity(i);
           }
       });



        btnSharedJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ViewJobsActivity.class);
                i.putExtra("EXTRA_JOB_BUTTON_CLICKED", "sharedJobs");
                startActivity(i);
            }
        });

        btnViewAllJobs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ViewJobsActivity.class);
                i.putExtra("EXTRA_JOB_BUTTON_CLICKED", "allJobs");
                startActivity(i);
            }
        });

        btnRatings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SeekerRatingMenuActivity.class);
                startActivity(i);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), ViewProfilesActivity.class);
            startActivity(i);
            overridePendingTransition(R.layout.fade_in, R.layout.fade_out);
            }
    }   );

        btnApplications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ViewPendingApplicationsActivity.class);
                startActivity(i);
            }
        });

    }


    /**
     * This method checks gets the displayName and phone number of jobseeker from database
     * @param email - email of the jobseeker
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








    /**
     * This method checks if the jobseeker has any pending applications that have been accepted, if so a toast will advert him and his button for view pending applications will be green
     * @param email - email of the jobseeker
     * @param context
     */


    public void checkIfAcceptedApplication(final String email, Context context){

        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.checkIfAcceptedApplication, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ;
                        try {

                         String applicantStatus= response.getString("applications");

                            if(applicantStatus.equals("true")){

                                btnApplications = (Button) findViewById(R.id.btn_pending_applications);
                                btnApplications.setBackgroundColor(Color.GREEN);

                                CharSequence text = getString(R.string.you_have_applications_that_have_been_accepted);
                                int duration = Toast.LENGTH_LONG;

                                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                                toast.show();

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
