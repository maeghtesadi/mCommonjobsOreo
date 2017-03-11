package com.oreo.mcommonjobs.Models.DatabaseTasks;

import android.content.Context;
import android.os.AsyncTask;

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
 * Created by jason on 2017-02-26.
 */

public class RegisterAccount extends AsyncTask<String, Void, String> {

    Context context;

    public RegisterAccount(Context context) {
        this.context = context;
    }

    protected String doInBackground(String... params) {
        String loginLink = "http://192.168.2.11/mcommonjobs/insert.php";
        String type = params[0];

        if (type.equals("insert")) {

            try {
                String firstname = params[1];
                String lastname = params[2];
                String email = params[3];
                String typeofuser = params[4];
                URL url = new URL(loginLink);

                HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
                httpcon.setRequestMethod("POST");
                httpcon.setDoOutput(true);
                httpcon.setDoInput(true);
                OutputStream outputStream = httpcon.getOutputStream();
                BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("firstName", "UTF-8") + "=" + URLEncoder.encode(firstname, "UTF-8") + "&" + URLEncoder.encode("lastName", "UTF-8") + "=" + URLEncoder.encode(lastname, "UTF-8")
                        + "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" + URLEncoder.encode("typeofuser", "UTF-8") + "=" + URLEncoder.encode(typeofuser, "UTF-8");

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

        return "failed to excute";
    }


    @Override
    protected void onPostExecute(String s) {

        String z = s;

    }
}
