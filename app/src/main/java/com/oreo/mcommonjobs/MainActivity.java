package com.oreo.mcommonjobs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText usernameInput;
    EditText passwordInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameInput = (EditText) findViewById(R.id.username);
        passwordInput = (EditText) findViewById(R.id.password);
    }

    public void OnLogin(View view){

        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        String type = "login";

        LoginActivity loginActivity = new LoginActivity(this);
        loginActivity.execute(type,username,password);

    }
}
