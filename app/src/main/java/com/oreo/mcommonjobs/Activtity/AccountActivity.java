package com.oreo.mcommonjobs.Activtity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.PersonSession;

/**
 * Created by jason on 2017-04-05.
 */

public class AccountActivity extends AppCompatActivity {

    PersonSession personInstance = PersonSession.getInstance();
    Button btn_editphonenumber, btn_editusername;
    TextView firstNameTextView, lastNameTextView, emailTextView, phoneNumberTextView, usernameTextView;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        inflateAccountFields();




        btn_editphonenumber= (Button)  findViewById(R.id.btn_edit_phonenumber);
        btn_editusername= (Button)  findViewById(R.id.btn_edit_username);



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




}
