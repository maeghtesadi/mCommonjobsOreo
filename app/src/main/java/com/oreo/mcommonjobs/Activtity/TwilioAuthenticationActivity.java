package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.oreo.mcommonjobs.R;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oreo.mcommonjobs.Controllers.SendConfirmationSms;

import java.util.Random;

public class TwilioAuthenticationActivity extends AppCompatActivity {

    private EditText editText_confirmation, editText_phoneNumber;
    private Button button_submit_confirmation, button_sendConfirmationCode;

    private Random random;

    private int inputConfirmation, generatedConfirmation;
    private String phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twilio_authentication);

        random = new Random();
        editText_confirmation = (EditText) findViewById(R.id.editText_confirmation_number);
        editText_phoneNumber = (EditText) findViewById(R.id.editText_phoneNumber);
        button_submit_confirmation = (Button) findViewById(R.id.button_submit_confirmation);
        button_sendConfirmationCode = (Button) findViewById(R.id.button_sendConfirmationCode);
        button_sendConfirmationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_phoneNumber.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), "Please enter your phone number.", Toast.LENGTH_LONG).show();
                }
                else{
                    generatedConfirmation = random.nextInt(999999 - 100000) + 100000;
                    phonenumber = editText_phoneNumber.getText().toString();
                    SendConfirmationSms smsAuthentication = new SendConfirmationSms(getApplicationContext());
                    smsAuthentication.execute(Integer.toString(generatedConfirmation), phonenumber);
                }
            }
        });
        button_submit_confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText_confirmation.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), "Please enter your confirmation code.", Toast.LENGTH_LONG).show();
                }
                else{
                    inputConfirmation = Integer.parseInt(editText_confirmation.getText().toString());
                    if(inputConfirmation == generatedConfirmation){
                        Toast.makeText(getApplicationContext(), "Confirmation codes matched!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),SelectUserTypeActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Confirmation codes didn't match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
