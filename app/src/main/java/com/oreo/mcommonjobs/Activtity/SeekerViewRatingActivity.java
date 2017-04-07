package com.oreo.mcommonjobs.Activtity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.PersonSession;

public class SeekerViewRatingActivity extends AppCompatActivity {

    PersonSession personSession = PersonSession.getInstance();
    TextView name, avgrating, attitudeR, punctualityR, cleanlinessR, comments;

    /**
     * onCreate method initialize the SeekerViewRatingActivity
     * the method sets the textviews with values passed from previous activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker_view_rating);

        name = (TextView)findViewById(R.id.providerName1);
        avgrating = (TextView)findViewById(R.id.avgRating);
        attitudeR = (TextView)findViewById(R.id.rating1);
        punctualityR = (TextView)findViewById(R.id.rating2);
        cleanlinessR = (TextView)findViewById(R.id.rating3);
        comments = (TextView)findViewById(R.id.comment);

        name.setText(getIntent().getStringExtra("DISPNAME_EXTRA") + " " + getIntent().getStringExtra("LASTNAME_EXTRA"));
        avgrating.setText(Double.toString(getIntent().getDoubleExtra("AVG_EXTRA", 0.0)) +"/5");
        attitudeR.setText(Integer.toString(getIntent().getIntExtra("R1_EXTRA", 0)) +"/5");
        punctualityR.setText(Integer.toString(getIntent().getIntExtra("R2_EXTRA", 0)) +"/5");
        cleanlinessR.setText(Integer.toString(getIntent().getIntExtra("R3_EXTRA", 0)) +"/5");
        comments.setText(getIntent().getStringExtra("COMMENT_EXTRA"));
    }
}
