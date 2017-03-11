package com.mcommonjobs.oreo.myapplication;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_REQUEST_SEND_SMS = 0;

    EditText editText_phoneNum, editText_confirmationNum;
    TextView textView_confirmationStatus;
    Button button_phoneNum, button_confirmationCode;

    String phoneNumber;
    int confirmationCode_sms, getConfirmationCode_input;
    Random randomConfirmationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        randomConfirmationCode = new Random();
        editText_phoneNum = (EditText) findViewById(R.id.editText_enter_phonenum);
        editText_confirmationNum = (EditText) findViewById(R.id.editText_enter_confirmation);
        textView_confirmationStatus = (TextView) findViewById(R.id.textView_confirmation_status);
        button_phoneNum = (Button) findViewById(R.id.button_submit_phone_number);
        button_confirmationCode = (Button) findViewById(R.id.button_submit_confirmation_number);
        button_phoneNum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                confirmationCode_sms = randomConfirmationCode.nextInt(2000 - 1000) + 1000;
                String message = "Mcommonjobs confirmation code: " + confirmationCode_sms;
                phoneNumber = editText_phoneNum.getText().toString();
                sendSMSMessage(phoneNumber, message);

            }
        });
        button_confirmationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getConfirmationCode_input = Integer.getInteger(editText_confirmationNum.getText().toString());
                if(getConfirmationCode_input != confirmationCode_sms){
                    Toast.makeText(getApplicationContext(), "Wrong code! Please try again.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Correct code!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void sendSMSMessage(String phoneNumber, String message){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
    }
}
