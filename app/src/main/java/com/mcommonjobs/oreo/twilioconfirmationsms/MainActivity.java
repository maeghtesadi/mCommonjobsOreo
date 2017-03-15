package com.mcommonjobs.oreo.twilioconfirmationsms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editText_confirmation;
    private Button button_submit_confirmation;

    private Random random;

    private int inputConfirmation, generatedConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random = new Random();
        generatedConfirmation = random.nextInt(999999 - 100000) + 100000;
        SmsActivity smsActivity = new SmsActivity(this);
        smsActivity.execute(Integer.toString(generatedConfirmation)); //sending the confirmation code to the Twilio phph file
        editText_confirmation = (EditText) findViewById(R.id.editText_confirmation_number);
        button_submit_confirmation = (Button) findViewById(R.id.button_submit_confirmation);
        button_submit_confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputConfirmation = Integer.getInteger(editText_confirmation.getText().toString());
                if(inputConfirmation == generatedConfirmation){
                    Toast.makeText(getApplicationContext(), "Confirmation codes matched!", Toast.LENGTH_SHORT).show(); //temporary toast
                }
                else{
                    Toast.makeText(getApplicationContext(), "Confirmation codes didn't match!", Toast.LENGTH_SHORT).show(); //temporary toast
                }
            }
        });
    }
}
