package com.oreo.mcommonjobs.Activtity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.oreo.mcommonjobs.R;

/**
 * Created by jason on 2017-04-05.
 */

public class AccountActivity extends AppCompatActivity {


    TextView firstNameTextView, lastNameTextView, emailTextView, phoneNumberTextView;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        firstNameTextView = (TextView) findViewById(R.id.textView_firstname);
        lastNameTextView = (TextView) findViewById(R.id.textView_lastname);
        emailTextView = (TextView) findViewById(R.id.textView_email);
        phoneNumberTextView = (TextView) findViewById(R.id.textView_phonenumber);

    }




}
