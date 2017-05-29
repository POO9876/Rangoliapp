package com.appswager.rangoliapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Upload2Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView messageText;
    Drawable drawable;
    Bitmap bitmap1, bitmap2;
    ByteArrayOutputStream bytearrayoutputstream;
    byte[] BYTE;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "uploadKey";
    int number=1;
    String regexStr = "^[0-9]$";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private EditText etxtUpload,etxtMobile,etxtEmail;
    private ImageView imageview;
    String email;
    private ProgressDialog dialog = null;
    private JSONObject jsonObject;
    //Permision code that will be checked in the method onRequestPermissionsResult
    private int STORAGE_PERMISSION_CODE = 23;
    SharedPreferences sharedpreferences;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private int totalCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload2);

        ActionBar actionBar = getSupportActionBar();
        Button uploadButton = (Button) findViewById(R.id.uploadButton);
        Button btnselectpic = (Button) findViewById(R.id.button_selectpic);
        messageText  = (TextView)findViewById(R.id.messageText);
        imageview = (ImageView)findViewById(R.id.imageView_pic);
        etxtUpload = (EditText)findViewById(R.id.etxtUpload);
        etxtMobile = (EditText)findViewById(R.id.etxtMobile);
        etxtEmail = (EditText)findViewById(R.id.etxtEmail);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        prefs = getPreferences(Context.MODE_PRIVATE);
        editor = prefs.edit();
        btnselectpic.setOnClickListener(this);
        uploadButton.setOnClickListener(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Uploading Image...");
        dialog.setCancelable(false);

        jsonObject = new JSONObject();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_selectpic:

                if (canMakeSmores()) {

                    if (isReadStorageAllowed()) {
                        //If permission is already having then showing the toast
                        Toast.makeText(Upload2Activity.this, "You already have the permission", Toast.LENGTH_LONG).show();
                        //Existing the method with return
                        return;

                    }
                    requestStoragePermission();
                }
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, Utils.REQCODE);
                break;
            case R.id.uploadButton:


                if(totalCount<5) {
                    if (etxtUpload.getText().toString().trim().length() > 0) {

                        if (etxtMobile.getText().toString().trim().length() == 10) {


                            if (etxtEmail.getText().toString().trim().matches(emailPattern)) {


                                if ((imageview.getDrawable() != null)) {
                                    sharedpreferences = getSharedPreferences(MyPREFERENCES, 0);
                                    number = sharedpreferences.getInt(Name, 5);
                                    //editor.commit(); // commit changes

                                    Bitmap image = ((BitmapDrawable) imageview.getDrawable()).getBitmap();
                                    dialog.show();
                                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                    image.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
                                    String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

                                    String name = etxtUpload.getText().toString().trim();
                                    String email = etxtEmail.getText().toString().trim();
                                    String mobile = etxtMobile.getText().toString().trim();

                                    try {
                                        jsonObject.put(Utils.imageName, name + "-" + mobile + "-"  + email );
                                        Log.e("Image name", name + email + mobile);
                                        jsonObject.put(Utils.image, encodedImage);
                                    } catch (JSONException e) {
                                        Log.e("JSONObject Here", e.toString());
                                    }
                                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Utils.urlUpload, jsonObject,
                                            new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject jsonObject) {
                                                    Log.e("Message from server", jsonObject.toString());
                                                    totalCount = prefs.getInt("counter", 0);
                                                    totalCount++;
                                                    editor.putInt("counter", totalCount);
                                                    editor.commit();
                                                    dialog.dismiss();
                                                    messageText.setText("Image Uploaded Successfully");
                                                    etxtUpload.setText("");
                                                    etxtMobile.setText("");
                                                    etxtEmail.setText("");
                                                    imageview.setImageBitmap(null);
                                                    Toast.makeText(getApplication(), "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                                }
                                            }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError volleyError) {
                                            Log.e("Message from server", volleyError.toString());
                                            dialog.dismiss();
                                        }
                                    });
                                    jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                                    Volley.newRequestQueue(this).add(jsonObjectRequest);
                                    break;
                                } else {

                                    Toast.makeText(this, "First Select An Image !!!", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                etxtEmail.setError("enter valid email");
                            }

                        } else {

                            etxtMobile.setError("enter valid mobile");

                        }
                    } else {

                        etxtUpload.setError("Enter valid Full name");
                    }

                }
                else
                {
                    Toast.makeText(this, "you have Consumed all 5 attempt !!!", Toast.LENGTH_SHORT).show();
                }


        }


    }
    //We are calling this method to check the permission status
    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }
    //Requesting permission
    private void requestStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
    }
    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if(requestCode == STORAGE_PERMISSION_CODE){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Utils.REQCODE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            imageview.setImageURI(selectedImageUri);
        }
    }
    private boolean canMakeSmores(){

        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1;
    }
    private boolean isImage() {

        Bitmap image = ((BitmapDrawable) imageview.getDrawable()).getBitmap();

        return image != null;

    }
}
