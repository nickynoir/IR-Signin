package edu.nsu.ir.activities.forum;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import edu.nsu.ir.R;
import edu.nsu.ir.api.ConnectServer;
import edu.nsu.ir.model.Post;
import edu.nsu.ir.model.StatusReponse;
import edu.nsu.ir.util.AppHelpers;


public class PostActivity extends AppCompatActivity {

    private ImageButton postImage;
    private Button btnPostTestimony;
    private EditText etTitle,etDetails;
    private Post post;
    private Gson gson;
    private StatusReponse statusReponse;
    private Context context;
    private ImageView imageView;
    private static final int ACTION_REQUEST_GALLERY=10;
    private static final int ACTION_REQUEST_CAMERA=20;
    private static final int CAMERA_PERMISSION_REQUEST_CODE=30;
    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

    private String UPLOAD_URL ="http://simplifiedcoding.16mb.com/VolleyUpload/upload.php";

    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";
    //private static Context staticContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();



        btnPostTestimony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addPost();
            }
        });
    }
private void addPost(){
    String title,date,postID,author;
    title=etTitle.getText().toString();
    date= AppHelpers.getCurrentDate();
    postID=AppHelpers.generatePostID(title, date);
    author=AppHelpers.getCurrentUserName();
    post=new Post(etDetails.getText().toString(),title, author,date,postID);
    ConnectServer.sendPost(post);
}
    @TargetApi(Build.VERSION_CODES.M)
    private void checkCameraPermissions(){
        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST_CODE);
        }
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Now user should be able to use camera
            }
            else {
                // Your app will not have this permission. Turn off all functions
                // that require this permission or it will force close like your
                // original question
            }
        }
    }

    public void clearFields(){
        etDetails.setText("");
        etTitle.setText("");


    }
    private void init(){
        post=new Post();
        Typeface titleFont = Typeface.createFromAsset(getAssets(),"fonts/Oswald-BoldItalic.ttf");
        Typeface detailsFont = Typeface.createFromAsset(getAssets(),"fonts/Oswald-DemiBold.ttf");
        Typeface commentFont = Typeface.createFromAsset(getAssets(),"fonts/Oswald-ExtraLight.ttf");
        Typeface authorFont = Typeface.createFromAsset(getAssets(),"fonts/Oswald-DemiBold.ttf");
        Typeface votesFont = Typeface.createFromAsset(getAssets(),"fonts/Oswald-Stencil.ttf");
        context=this;
        imageView=new ImageView(context);
        gson=new Gson();
        statusReponse=new StatusReponse();

        etDetails=(EditText) findViewById(R.id.etDetailsPost);
        etTitle=(EditText) findViewById(R.id.etTitlePost);
        btnPostTestimony=(Button) findViewById(R.id.btnPostTestimony);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)    {

            switch (requestCode) {
                case ACTION_REQUEST_GALLERY:
                    if (resultCode == Activity.RESULT_OK) {
                        bitmap = (Bitmap) data.getExtras().get("data");
                        //upload the image to server here
                    }
                    break;

                case ACTION_REQUEST_CAMERA:
                    Uri filePath = data.getData();
                    try {
                        //Getting the Bitmap from Gallery
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                        //Setting the Bitmap to ImageView
                        // imageView.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(context, s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(context, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name
                String name = etTitle.getText().toString().trim();

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, name);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
}
