package com.oreo.mcommonjobs.Activtity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.oreo.mcommonjobs.R;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by jason on 2017-04-06.
 */

public class UploadImageActivity extends AppCompatActivity {
    ImageView imageview;
    Button chooseImageBTN, uploadImageBTN;
    EditText editTextnameofImage;
    private  final int IMG_REQUEST =1;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        chooseImageBTN = (Button) findViewById(R.id.btn_selectimage);
        uploadImageBTN = (Button) findViewById(R.id.btn_uploadimage);
        imageview = (ImageView)  findViewById(R.id.imageView_profileimage);
        editTextnameofImage =(EditText)findViewById(R.id.editText_imaginename);
        chooseImageBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

         selectImage();

            }
        });




    }

    public void selectImage(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Detects request codes
        if(requestCode==IMG_REQUEST && resultCode == RESULT_OK && data!=null) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                imageview.setImageBitmap(bitmap);
                imageview.setVisibility(View.VISIBLE);
                editTextnameofImage.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
