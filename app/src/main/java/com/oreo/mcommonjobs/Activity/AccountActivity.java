package com.oreo.mcommonjobs.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.oreo.mcommonjobs.Models.URLPath;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.PersonSession;
import com.oreo.mcommonjobs.Session.RequestSingleton;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jason on 2017-04-05.
 */

public class AccountActivity extends AppCompatActivity {

    PersonSession personInstance = PersonSession.getInstance();
    Button btn_editphonenumber, btn_editusername;
    ImageButton profileImage;
    TextView firstNameTextView, lastNameTextView, emailTextView, phoneNumberTextView, usernameTextView;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        inflateAccountFields();


        profileImage = (ImageButton) findViewById(R.id.imageButton);
        btn_editphonenumber= (Button)  findViewById(R.id.btn_edit_phonenumber);
        btn_editusername= (Button)  findViewById(R.id.btn_edit_username);



        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), UploadImageActivity.class);
                startActivity(i);

            }
        });


        btn_editphonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personInstance.setPhoneNumber(phoneNumberTextView.getText().toString());
                updatePhoneNumber(personInstance.getEmail(),phoneNumberTextView.getText().toString(), getApplicationContext());
            }
        });


        btn_editusername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personInstance.setDisplayName(usernameTextView.getText().toString());
                updateDisplayName(personInstance.getEmail(),usernameTextView.getText().toString(), getApplicationContext());

            }
        });


    }

    /**
     * This method takes all the values stored in personinstance(our session class for user) and uses it to fill in the appropiate values on the
     *  , activity_account layout
     */

    public void inflateAccountFields(){

        firstNameTextView = (TextView) findViewById(R.id.textView_firstname);
        firstNameTextView.setText(personInstance.getFirstName());

        lastNameTextView = (TextView) findViewById(R.id.textView_lastname);
        lastNameTextView.setText(personInstance.getLastName());

        emailTextView = (TextView) findViewById(R.id.textView_email);
        emailTextView.setText(personInstance.getEmail());

        phoneNumberTextView = (EditText) findViewById(R.id.textView_phonenumber);
        phoneNumberTextView.setText(personInstance.getPhoneNumber());


        usernameTextView = (EditText) findViewById(R.id.textView_username);
        usernameTextView.setText(personInstance.getDisplayName());

    }

    /**
     * This method updates the phone number of a user in the database
     * @param email - email of the jobseeker
     * @param  phoneNumber the phone number of the user
     * @param context
     */

    public void updatePhoneNumber(final String email,final String phoneNumber ,final Context context){

        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("phoneNumber",phoneNumber);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.updatePhoneNumber, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        CharSequence text = "Number updated!";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Error", "Unable to parse json array");
            }
        });
        RequestSingleton.getInstance(context).addToRequestQueue(jsonRequest);

        CharSequence text = "Number updated!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


    /**
     * This method updates the users displayname
     * @param email - email of the jobseeker
     *  @param displayName - displayname of the user
     * @param context
     */

    public void updateDisplayName(final String email,final String displayName , final Context context){

        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("displayName", displayName);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.updateDisplayName, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        CharSequence text = "Displayname updated!";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Error", "Unable to parse json array");
            }
        });
        RequestSingleton.getInstance(context).addToRequestQueue(jsonRequest);


        CharSequence text = "Displayname updated!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }


}
