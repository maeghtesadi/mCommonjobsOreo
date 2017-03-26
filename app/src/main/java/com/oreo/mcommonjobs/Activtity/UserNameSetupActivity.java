package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.UserSession;

public class UserNameSetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name_setup);

        Button continueBt = (Button) findViewById(R.id.continue_bt);
        final TextView firstNameTextView = (TextView) findViewById(R.id.firstNameText);
        final TextView lastNameTextView = (TextView) findViewById(R.id.lastNameText);

        continueBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String firstName = firstNameTextView.getText().toString();
                String lastName = lastNameTextView.getText().toString();
                UserSession session = UserSession.getInstance();
                session.setFirstName(firstName);
                session.setLastName(lastName);
                startActivity(new Intent(getApplicationContext(), UserTypeSelectionActivity.class));
            }
        });
    }
}
