package com.oreo.mcommonjobs.Activtity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.oreo.mcommonjobs.Controllers.JobProviderController;
import com.oreo.mcommonjobs.Models.Application;
import com.oreo.mcommonjobs.Models.URLPath;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.PersonSession;
import com.oreo.mcommonjobs.Session.RequestSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2017-03-19.
 */

public class ViewApplicants extends AppCompatActivity {
    private List<Application> applicants = new ArrayList<>();
     JobProviderController jobProviderController = new JobProviderController();
    PersonSession personInstance = PersonSession.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applicants);

        populateApplicantList();

    }



 private void populateApplicantList (){

    applicants =  getApplicants(personInstance.getEmail(),this.getApplicationContext());



 }


 private class customAdapter extends ArrayAdapter<Application>{


     /**
      * Constructor for customAdapter
      * Takes fragment layout, decorates it with values taken from a job and than returns the converted view
      * @return convertView(VIEW)
      */
     public customAdapter() {
         super(ViewApplicants.this, R.layout.applicantfragment, applicants);
     }

     /**
      * Method description here
      *
      * @param position
      * @param convertView
      * @param parent
      * @return
      */
     @NonNull
     @Override
     public View getView(int position, View convertView, ViewGroup parent) {

         if (convertView == null) {
             convertView = getLayoutInflater().inflate(R.layout.applicantfragment, parent, false);
         }

         final Application currentApplicant = applicants.get(position);

         TextView jobtitle = (TextView) convertView.findViewById(R.id.textView2);
         TextView name = (TextView) convertView.findViewById(R.id.desc);

         jobtitle.setText(currentApplicant.getJobtype());
         name.setText(currentApplicant.getUser_name());


         convertView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

            Intent i = new Intent(getApplicationContext(), ViewApplicantCredentials.class);
                  i.putExtra("username", currentApplicant.getUser_name());
                  i.putExtra("typeofjob", currentApplicant.getJobtype());
                  i.putExtra("availibility", currentApplicant.getAvalibility());
                  i.putExtra("expected_wage", currentApplicant.getExpected_wage());
                  i.putExtra("yearsofExperience", currentApplicant.getYearsofExperience());
                 startActivity(i);


             }
         });












         return convertView;
     }



 }

    /**
     * This method allows a user of type JobProvider to get the list of people who applied to a job they posted
     * @param email - email of the Jobprovider
     * @param context
     * @return List of applications
     */
    private List<Application> getApplicants(final String email,Context context){

        final List<Application> applicants = new ArrayList<>();

        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.getApplicants, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ;
                        try {

                            JSONArray jsonApplicantssarray = response.getJSONArray("applications");

                            for (int i = 0; i < jsonApplicantssarray.length(); i++) {
                                JSONObject applicant_current_position = jsonApplicantssarray.getJSONObject(i);

                                String displayname = applicant_current_position.getString("displayname");
                                String typeofjob = applicant_current_position.getString("typeofjob");
                                String yearsofExperience = applicant_current_position.getString("yearsofexperience");
                                String expected_wage = applicant_current_position.getString("expected_wage");
                                String availability = applicant_current_position.getString("availability");
                                //Application app= new Application(typeofjob,displayname);
                                Application app = new Application(typeofjob,displayname,yearsofExperience,availability,expected_wage);
                                applicants.add(app);

                            }

                            ListView applicantslist = (ListView) (findViewById(R.id.applicantslist));
                            ArrayAdapter<Application> adapter = new customAdapter();
                            applicantslist.setAdapter(adapter);

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



        return applicants;
    }

 }



