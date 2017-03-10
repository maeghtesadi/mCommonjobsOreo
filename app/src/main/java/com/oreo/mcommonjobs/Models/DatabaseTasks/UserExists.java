package com.oreo.mcommonjobs.Models.DatabaseTasks;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.oreo.mcommonjobs.Activtity.NavigationActivityForJobProvider;
import com.oreo.mcommonjobs.Activtity.NavigationActivityForJobSeeker;
import com.oreo.mcommonjobs.Activtity.SelectUserTypeActivity;
import com.oreo.mcommonjobs.Session.PersonSession;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Jason on 2/18/2017.
 */

public class UserExists extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alert;



    String testresult;


    public String getTestresult() {
        return testresult;
    }


    public UserExists(Context context) {

        this.context = context;
    }

    protected void onPreExecute() {

        alert = new AlertDialog.Builder(context).create();
        alert.setTitle("Login Status");

    }


    protected String doInBackground(String... params) {

        String loginLink = "http://192.168.0.104/login.php";
        String type = params[0];

        if (type.equals("login")) {

            try {
                String email = params[1];
                URL url = new URL(loginLink);

                HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
                httpcon.setRequestMethod("POST");
                httpcon.setDoOutput(true);
                httpcon.setDoInput(true);
                OutputStream outputStream = httpcon.getOutputStream();
                BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

                buffWriter.write(post_data);
                buffWriter.flush();
                buffWriter.close();
                outputStream.close();

                //reads response from post request
                InputStream inputStream = httpcon.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;

                }

                bufferedReader.close();
                inputStream.close();
                httpcon.disconnect();


                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        return null;
    }

    protected void onPostExecute(String result) {

        PersonSession instance = PersonSession.getInstance();

        try {
            JSONObject values = new JSONObject(result);

            testresult = values.getString("typeofuser");
            instance.setTypeofuser(testresult);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if(this.testresult.equals("jobprovider")){
            Intent i = new Intent(this.context, NavigationActivityForJobProvider.class);
            context.startActivity(i);

        }

       if(this.testresult.equals("jobseeker")){
           Intent c = new Intent(this.context, NavigationActivityForJobSeeker.class);
           context.startActivity(c);

       }

        if(this.testresult==null){
           Intent z = new Intent(this.context, SelectUserTypeActivity.class);
           context.startActivity(z);

       }






/*
        if (result.equals("success")) {


            Intent i = new Intent(this.context, NavigationActivityForJobProvider.class);
            context.startActivity(i);


        } else {
            Intent i = new Intent(this.context, SelectUserTypeActivity.class);
            context.startActivity(i);

        }

*/
    }


}