package com.oreo.mcommonjobs;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ProfileSetUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        TextView profileSetupHeader;
        profileSetupHeader = (TextView) findViewById(R.id.page_title_profile_info);

        Typeface montserratTypeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.ttf");
        profileSetupHeader.setTypeface(montserratTypeface);
    }

}
