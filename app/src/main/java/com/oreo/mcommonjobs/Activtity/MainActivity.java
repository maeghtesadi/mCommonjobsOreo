package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.oreo.mcommonjobs.R;

public class MainActivity extends AppCompatActivity {
    EditText usernameInput;
    EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, SignInActivity.class));
    }

    /**
    public void OnLogin(View view){

        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        String type = "login";

        /*
        LoginActivity loginActivity = new LoginActivity(this);
        loginActivity.execute(type,username,password);

    }**/
}