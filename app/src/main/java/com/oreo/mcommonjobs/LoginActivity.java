package com.oreo.mcommonjobs;

import android.app.AlertDialog;
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
 * Created by Rameen on 2/18/2017.
 */

public class LoginActivity extends AsyncTask<String,Void,String> {
        Context context;
        AlertDialog alert;

        LoginActivity(Context context){

            this.context = context;
        }
        protected void onPreExecute(){

            alert = new AlertDialog.Builder(context).create();
            alert.setTitle("Login Status");

        }


        protected String doInBackground(String... params){

            String loginLink = "http://192.168.2.11/test/login.php";
            String type = params[0];

            if(type.equals("login")){

                try {
                    String username = params[1];
                    String password = params[2];
                    URL url = new URL(loginLink);

                    HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();
                    httpcon.setRequestMethod("POST");
                    httpcon.setDoOutput(true);
                    httpcon.setDoInput(true);
                    OutputStream outputStream = httpcon.getOutputStream();
                    BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                    String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&" +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");

                    buffWriter.write(post_data);
                    buffWriter.flush();
                    buffWriter.close();
                    outputStream.close();

                    //reads response from post request
                    InputStream inputStream = httpcon.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                    String result = "";
                    String line = "";

                    while((line = bufferedReader.readLine()) != null){
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

    protected void onPostExecute(String result){

        alert.setMessage(result);
        alert.show();


    }







}