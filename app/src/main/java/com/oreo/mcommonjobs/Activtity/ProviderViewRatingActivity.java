package com.oreo.mcommonjobs.Activtity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.PersonSession;

/**
 * This is the class for the provider viewing one of their ratings Activity.
 *
 * @author Armine-i
 * @author sammoosavi
 */

public class ProviderViewRatingActivity extends AppCompatActivity {

    PersonSession personSession = PersonSession.getInstance();
    TextView seekerName, avgrating, attitudeR, helpfulnessR, QualityR, comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_view_rating);

        seekerName = (TextView) findViewById(R.id.seekerName);
        avgrating = (TextView) findViewById(R.id.avgRating);
        attitudeR = (TextView) findViewById(R.id.rating1);
        helpfulnessR = (TextView) findViewById(R.id.rating2);
        QualityR = (TextView) findViewById(R.id.rating3);
        comment = (TextView) findViewById(R.id.comment);

        seekerName.setText(getIntent().getStringExtra("DISPNAME_EXTRA") + " " + getIntent().getStringExtra("LASTNAME_EXTRA"));
        avgrating.setText(Double.toString(getIntent().getDoubleExtra("AVGR_EXTRA", 0.0)));
        attitudeR.setText(getIntent().getIntExtra("R1_EXTRA", 0));
        helpfulnessR.setText(getIntent().getIntExtra("R2_EXTRA", 0));
        QualityR.setText(getIntent().getIntExtra("R3_EXTRA", 0));
        comment.setText(getIntent().getStringExtra("COMMENT_EXTRA"));
    }
}