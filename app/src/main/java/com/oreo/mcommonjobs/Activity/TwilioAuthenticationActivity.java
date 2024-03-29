package com.oreo.mcommonjobs.Activity;

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

/**
 * The TwilioAuthenticationActivity has a responsibility of sending a confirmation code to the user
 * via sms and the user has to input that code into the textfield in order to be logged in
 * @author  Sam
 * @author  Ali
 * @version 1.0
 * @since   2017-03-13
 */

public class TwilioAuthenticationActivity extends AppCompatActivity {

    private EditText editText_confirmation, editText_phoneNumber;
    private Button button_submit_confirmation, button_sendConfirmationCode;

    private Random randomConfirmationCodeGenerator;

    private int inputConfirmation, generatedConfirmation;
    private String phonenumber;

    /**
     * this method instantiates every UI element of this activity and sets the onclick listenners on the buttons
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twilio_authentication);

        randomConfirmationCodeGenerator = new Random();

        //[START Instantiating the UI elements]
        editText_confirmation = (EditText) findViewById(R.id.editText_confirmation_number);
        editText_phoneNumber = (EditText) findViewById(R.id.editText_phoneNumber);
        button_submit_confirmation = (Button) findViewById(R.id.button_submit_confirmation);
        button_sendConfirmationCode = (Button) findViewById(R.id.button_sendConfirmationCode);
        //[END Instantiating the UI elements]

        button_sendConfirmationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Prevents the app from crashing in case the user clicks a button without entering it's phone number
                if(editText_phoneNumber.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), R.string.please_enter_your_phone_number, Toast.LENGTH_LONG).show();
                }
                else{
                    //Generating a random confirmation code between 100000 and 999999.
                    generatedConfirmation = randomConfirmationCodeGenerator.nextInt(999999 - 100000) + 100000;

                    phonenumber = editText_phoneNumber.getText().toString();

                    //Using a SendConfirmationSms object to send the generated confirmation code to a specified phone number.
                    SendConfirmationSms smsAuthentication = new SendConfirmationSms(getApplicationContext());
                    smsAuthentication.execute(Integer.toString(generatedConfirmation), phonenumber);
                }
            }
        });
        button_submit_confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Prevents the app from crashing in case the user clicks a button without entering it's confirmation number.
                if(editText_confirmation.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), R.string.please_enter_your_confirmation_code, Toast.LENGTH_LONG).show();
                }
                else{
                    inputConfirmation = Integer.parseInt(editText_confirmation.getText().toString());

                    //Comparing the input confirmation code and the confirmation code generated by the app.
                    if(inputConfirmation == generatedConfirmation){
                        Toast.makeText(getApplicationContext(), R.string.confirmation_codes_matched, Toast.LENGTH_SHORT).show();

                        //Going to SelectUserTypeActivity when the input confirmation number is valid.
                        Intent intent = new Intent(getApplicationContext(),SelectUserTypeActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), R.string.confirmation_codes_not_matched, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
