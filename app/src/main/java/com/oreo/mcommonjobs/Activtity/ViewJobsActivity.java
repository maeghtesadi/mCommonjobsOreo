package com.oreo.mcommonjobs.Activtity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.oreo.mcommonjobs.Models.Job;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.RequestSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2017-03-12.
 */

public class ViewJobsActivity extends AppCompatActivity {

    ListView jobslist;
    private List<Job> jobs = new ArrayList<>();
    String debug = "hello";
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_jobs);

       // t = (TextView) (findViewById(R.id.hello)) ;

        engine();


    }



 private void engine(){


/*
    // String url ="http://192.175.117.182/test.php";
     String url ="http://192.168.0.104/getjobs.php";

     // Request a json response from the provided URL.
     JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
             new Response.Listener<JSONObject>() {
                 @Override
                 public void onResponse(JSONObject response) {
                debug="lol";
                     // Display the first 500 characters of the response string.
                     t.setText("Response is: "+ response.toString());
                 }
             }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {
             t.setText("That didn't work!");
         }
     });
     // Add the request to the RequestQueue.
     */
    // RequestSingleton.getInstance(this).addToRequestQueue(jsonRequest);
















     //  String url = "http://127.0.0.1/getjobs.php";
     String url = "http://192.168.0.104/getjobs.php";
    //  String url = "http://localhost/getjobs.php";

     //String url ="http://192.175.117.182/test.php";
     JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
             new Response.Listener<JSONObject>() {
                 @Override
                 public void onResponse(JSONObject response) {
                     //debug=response.toString();
                     debug="i am here";
                     try {

                         JSONArray jobz = response.getJSONArray("jobs");


                         for (int i = 0; i < jobz.length(); i++) {
                             JSONObject temp = jobz.getJSONObject(i);

                             String des = temp.getString("description");
                             String typeofjob = temp.getString("typeofjob");


                             jobs.add(new Job(des, typeofjob));

                         }
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }
                    // t.setText("Response is: "+ response.toString());

                 } //hello
             }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {
             //t.setText("That didn't work!");
         }
     });

     RequestSingleton.getInstance(this).addToRequestQueue(jsonRequest);


     ArrayAdapter<Job> adapter = new customAdapter();

     ListView jobslist = (ListView) (findViewById(R.id.joblist));
     jobslist.setAdapter(adapter);


 }


private class customAdapter extends ArrayAdapter<Job>{


    public customAdapter() {
        super(ViewJobsActivity.this, R.layout.jobfragment, jobs);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.jobfragment, parent, false);
        }

        Job currentJob = jobs.get(position);

       // ImageView newsImage = (ImageView) convertView.findViewById(R.id.leftIco);
        TextView heading = (TextView) convertView.findViewById(R.id.heading);
        TextView desc = (TextView) convertView.findViewById(R.id.desc);



        heading.setText(currentJob.getCategory());
        desc.setText(currentJob.getDescription());





        return convertView;
        //   return super.getView(position, convertView, parent);



    }
}




}






