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
    }

}
