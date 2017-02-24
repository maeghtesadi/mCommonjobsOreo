package com.oreo.mcommonjobs.Activtity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.oreo.mcommonjobs.R;

/**
 * Created by kimcodes on 2017-02-22.
 */

public class ProfileSetUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        // find text to set the font
        TextView profileSetupHeader = (TextView) findViewById(R.id.page_title_profile_info);
        TextView continueText = (TextView) findViewById(R.id.continue_text);

        // set the font to Montserrat
        Typeface montserratTypeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.ttf");
        profileSetupHeader.setTypeface(montserratTypeface);
        continueText.setTypeface(montserratTypeface);

        Bundle bundle = getIntent().getExtras();
        String user_email = bundle.getString(("user_email"));
        String user_first_name = bundle.getString(("user_first_name"));
        String user_last_name = bundle.getString(("user_last_name"));

        TextView emailTextView = (TextView) findViewById(R.id.email_textview);
        emailTextView.setText(user_email);

        TextView firstNameTextView = (TextView) findViewById(R.id.firstname_textview);
        firstNameTextView.setText(user_first_name);

        TextView lastNameTextView = (TextView) findViewById(R.id.lastname_textview);
        lastNameTextView.setText(user_last_name);
    }

}
