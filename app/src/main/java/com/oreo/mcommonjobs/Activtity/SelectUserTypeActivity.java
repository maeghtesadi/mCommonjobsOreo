package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.oreo.mcommonjobs.Controller.PersonController;
import com.oreo.mcommonjobs.R;

public class SelectUserTypeActivity extends AppCompatActivity {

    Button btn_jobseeker, btn_jobprovider;
    String typeofuser;
    RegisterAccount reg = new RegisterAccount(this);
    PersonController instance = PersonController.getInstance();
    String debugz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user_type);

        btn_jobseeker = (Button) findViewById(R.id.btn_jobseeker);
        btn_jobprovider = (Button) findViewById(R.id.btn_jobprovider);



        btn_jobseeker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                typeofuser = "jobseeker" ;

                reg.execute("insert",instance.getFirstName(),instance.getLastName(),instance.getEmail(),typeofuser);

                // add person to table in db, we get their info ffrom the personcontroller, and we also add weather tehy are job seeker or provider
                // based off button they clicked
                Intent i = new Intent(getApplicationContext(), NavigationActivity.class);
                startActivity(i);
            }
        });

        btn_jobprovider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                typeofuser = "jobprovider";
                reg.execute("insert",instance.getFirstName(),instance.getLastName(),instance.getEmail(),typeofuser);
                Intent i = new Intent(getApplicationContext(), NavigationActivity.class);
                startActivity(i);
            }
        });
    }
}
