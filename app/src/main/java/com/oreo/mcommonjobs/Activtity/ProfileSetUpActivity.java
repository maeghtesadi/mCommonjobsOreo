package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.oreo.mcommonjobs.R;

/**
 * Created by kimcodes on 2017-02-22.
 */

public class ProfileSetUpActivity extends AppCompatActivity {

    Button btn_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        // find text to set the font
        TextView profileSetupHeader = (TextView) findViewById(R.id.page_title_profile_info);
        btn_continue = (Button) findViewById(R.id.btn_continue);

        // set the font to Montserrat
        Typeface montserratBoldTypeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.ttf");
        Typeface montserratRegularTypeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");
        profileSetupHeader.setTypeface(montserratBoldTypeface);
        btn_continue.setTypeface(montserratBoldTypeface);

        // get data grabbed from google
        Bundle bundle = getIntent().getExtras();
        String user_email = bundle.getString(("user_email"));
        String user_first_name = bundle.getString(("user_first_name"));
        String user_last_name = bundle.getString(("user_last_name"));

        // display data
        TextView emailTextView = (TextView) findViewById(R.id.email_textview);
        emailTextView.setText(user_email);
        emailTextView.setTypeface(montserratBoldTypeface);

        TextView firstNameTextView = (TextView) findViewById(R.id.firstname_textview);
        firstNameTextView.setText(user_first_name);
        firstNameTextView.setTypeface(montserratRegularTypeface);

        TextView lastNameTextView = (TextView) findViewById(R.id.lastname_textview);
        lastNameTextView.setText(user_last_name);
        lastNameTextView.setTypeface(montserratRegularTypeface);
        // finished displaying data

        // find button and set it to launch the next activity
        btn_continue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), SelectUserTypeActivity.class);
                startActivity(i);
            }
        });

    }

}
