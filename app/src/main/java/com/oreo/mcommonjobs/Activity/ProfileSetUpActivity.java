package com.oreo.mcommonjobs.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.PersonSession;

/**
 * This is the class for the Profile SetupActivity.
 *
 * @author kimcodes
 */
public class ProfileSetUpActivity extends AppCompatActivity {

    Button btnContinue;
    Typeface montserratBoldTypeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.ttf");
    Typeface montserratRegularTypeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");
    PersonSession personInstance = PersonSession.getInstance();

    /**
     * Initializes ProfileSetUpActivity.
     * Sets the UI TextViews font family.
     * Collects data to be displayed in the UI.
     * Sets onclick listeners.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        setFontForActivity();
        displayDataForActivity();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SelectUserTypeActivity.class);
                startActivity(i);
            }
        });
    }

    /**
     * This method sets the font for the texts in the Activity's UI.
     */
    private void setFontForActivity(){
        Typeface montserratBoldTypeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.ttf");
        Typeface montserratRegularTypeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");
        TextView profileSetupHeader = (TextView) findViewById(R.id.page_title_profile_info);
        profileSetupHeader.setTypeface(montserratBoldTypeface);
        btnContinue.setTypeface(montserratBoldTypeface);
    }

    /**
     * This method grabs the data needed to display on the Activity's UI.
     */
    private void displayDataForActivity(){
        TextView emailTextView = (TextView) findViewById(R.id.email_textview);
        emailTextView.setText(personInstance.getEmail());
        emailTextView.setTypeface(montserratBoldTypeface);

        TextView firstNameTextView = (TextView) findViewById(R.id.firstname_textview);
        firstNameTextView.setText(personInstance.getFirstName());
        firstNameTextView.setTypeface(montserratRegularTypeface);

        TextView lastNameTextView = (TextView) findViewById(R.id.lastname_textview);
        lastNameTextView.setText(personInstance.getLastName());
        lastNameTextView.setTypeface(montserratRegularTypeface);
    }
}
