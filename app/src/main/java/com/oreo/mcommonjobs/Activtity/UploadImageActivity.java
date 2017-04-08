package com.oreo.mcommonjobs.Activtity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.oreo.mcommonjobs.Models.URLPath;
import com.oreo.mcommonjobs.R;
import com.oreo.mcommonjobs.Session.PersonSession;
import com.oreo.mcommonjobs.Session.RequestSingleton;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jason on 2017-04-06.
 */

public class UploadImageActivity extends AppCompatActivity {
    ImageView imageview;
    Button chooseImageBTN, uploadImageBTN;
    EditText editTextnameofImage;
    private  final int IMG_REQUEST =1;
    public Bitmap bitmap;
    PersonSession personInstance = PersonSession.getInstance();

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


        uploadImageBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImg(personInstance.getEmail(), personInstance.getLastName(),getApplicationContext());
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



    /**
     * This method gets uploads an image to the server
     * @param email - email of the user
     * @param  name -name of the user
     * @param context
     */

    public void uploadImg(final String email , final String name, final Context context){

        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("image",imageToString(this.bitmap));
        params.put("name", name);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLPath.uploadImage, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {




                            CharSequence text = "Image uploaded successfully";
                            int duration = Toast.LENGTH_LONG;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            imageview.setImageResource(0);
                            imageview.setVisibility(View.GONE);




                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Error", "Unable to parse json array");
            }
        });
        RequestSingleton.getInstance(context).addToRequestQueue(jsonRequest);


    }


    /**
     * This method converts a bitmap into a string
     * @param bitmap - the bitmap of your photo
     * @return String - decoded bitmap into a string
     */
private String imageToString(Bitmap bitmap){


    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
    byte[] imgbytes = byteArrayOutputStream.toByteArray();
    return Base64.encodeToString(imgbytes,Base64.DEFAULT);

}











}
