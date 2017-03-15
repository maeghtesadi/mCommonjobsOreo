package com.oreo.mcommonjobs.Activtity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.oreo.mcommonjobs.Controllers.UserController;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.PersonSession;

/**
 * Class/Activity description here
 *
 * @author Jason
 * @author kimcodes
 *
 */
public class SelectUserTypeActivity extends AppCompatActivity {

    Button btnJobSeeker, btnJobProvider;
    String typeOfUser;
    PersonSession personInstance = PersonSession.getInstance();
    Context selectUserTypeContext;
    UserController user = new UserController();

    /**
     * Initializes the SelectUserTypeActivity.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user_type);

        btnJobSeeker = (Button) findViewById(R.id.btn_jobseeker);
        btnJobProvider = (Button) findViewById(R.id.btn_jobprovider);

        selectUserTypeContext = this.getApplicationContext();

        btnJobSeeker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                typeOfUser = "jobseeker"; // turn this into an enum or something @jason
                personInstance.setTypeofuser("jobseeker");
                user.registerAccount(personInstance.getFirstName(), personInstance.getLastName(), personInstance.getEmail(), typeOfUser, selectUserTypeContext);
                Intent i = new Intent(getApplicationContext(), NavigationActivityForJobSeeker.class);
                startActivity(i);
            }
        });

        btnJobProvider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                typeOfUser = "jobprovider";
                personInstance.setTypeofuser("jobprovider");
                user.registerAccount(personInstance.getFirstName(), personInstance.getLastName(), personInstance.getEmail(), typeOfUser, selectUserTypeContext);
                Intent i = new Intent(getApplicationContext(), NavigationActivityForJobProvider.class);
                startActivity(i);
            }
        });
    }
}
