package com.oreo.mcommonjobs.Models;

/**
 * Created by Ali on 3/16/2017.
 */

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
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * this class is responsible to call the Twilio PHP scripts on the server whichi will implement the twilio API for sending an sms to the user.
 *
 * @author Sam
 * @author Ali
 * @since 3-16-2017
 * @version 1.0
 */
public class SendConfirmationSms extends AsyncTask<String, Void, String>{

    private Context context;

    public SendConfirmationSms(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * this method establishes a connection to the server and once it is connected to the server the method will send to the php scripts the confirmaton code and the phonenumber.
     *
     * @param strings
     * @return A string that represents the result of the post request.
     * @throws MalformedURLException
     * @throws IOException
     */
    @Override
    protected String doInBackground(String... strings) {
        try{
            String confirmationCode = (String) strings[0];
            String phoneNumber = (String) strings[1];
            String link = "http://[Ip address here]/twilio/index.php";

            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data = URLEncoder.encode("confirmationCode", "UTF-8") + "=" + URLEncoder.encode(confirmationCode, "UTF-8") + "&" + URLEncoder.encode("phoneNumber","UTF-8") + "=" + URLEncoder.encode(phoneNumber, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            //reads response from post request
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "";
            String line = "";

            while((line = bufferedReader.readLine()) != null){
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}

