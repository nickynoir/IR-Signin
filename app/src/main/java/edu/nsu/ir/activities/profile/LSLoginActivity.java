package edu.nsu.ir.activities.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.nsu.ir.activities.cases.CCaseHomeActivity;
import edu.nsu.ir.util.AppURLS;
import edu.nsu.ir.activities.Home;
import edu.nsu.ir.R;
import edu.nsu.ir.model.ProfileDAO;


public class LSLoginActivity extends AppCompatActivity {
    private Button mLoginButton;
    private Button mNewAccountButton;
    private ProfileDAO UserProfile;
    private EditText mEmail, mPassword;
    private  AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nsu.ir.R.layout.ls_activity_login);

        mLoginButton = (Button) findViewById(edu.nsu.ir.R.id.loginButton);
        mNewAccountButton = (Button) findViewById(edu.nsu.ir.R.id.newAccountButton);
        mEmail = (EditText) findViewById(R.id.loginEmail);
        mPassword = (EditText) findViewById(R.id.passwordText);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doReg();
            }
        });

        mNewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignup();
            }
        });
    }

    //Start Signup
    private void startSignup() {
        Intent intent = new Intent(this, LSSignupActivity.class);
        startActivity(intent);
    }

    //Start Case Home
    private void startCasesHome() {
        Intent intent = new Intent(this, CCaseHomeActivity.class);
        startActivity(intent);
    }
    private void doReg(){

         alertDialog = new AlertDialog.Builder(LSLoginActivity.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Alert Dialog");

        // Setting Dialog Message
        alertDialog.setMessage("Check your credidentials");


        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });


        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLS.GET_PROFILE_URL,
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {
                        if(response.contains("No profile found")){
                            alertDialog.show();
                        }

                        Log.d("TAG-RESPONSE LSLogin", response.toString());
                        UserProfile = new ProfileDAO();
                        //CREATES JSON AND ASSIGNS ITS VALUES TO AN ARRAY OF MESSAGE THREADS
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                           JSONArray jsonArray= (JSONArray) jsonObject.get("profile");


                           // for(int i=0;i<jsonArray.length();i++){
                                JSONObject row = jsonArray.getJSONObject(0);


                                if(row.getString("password").matches(mPassword.getText().toString()))
                                {
                                    UserProfile.setItem_uname(row.getString("uname"));
                                    UserProfile.setFname(row.getString("fname"));
                                    UserProfile.setLname(row.getString("lname"));
                                    UserProfile.setEmail(mEmail.getText().toString());
                                    UserProfile.setCity(row.getString("city"));
                                    UserProfile.setState(row.getString("state"));
                                    UserProfile.setCompany(row.getString("company"));
                                    UserProfile.setExperience(row.getString("experience"));
                                    UserProfile.setAge(row.getString("age"));
                                    UserProfile.setContact(row.getString("email"));
                                    UserProfile.setImage_path("image_path");

                                    Intent intent = new Intent(LSLoginActivity.this, Home.class);
                                    //Intent intent = new Intent(MainActivity.this, PEditProfileActivity.class);
                                    intent.putExtra("userProfile", new ProfileDAO(UserProfile.getItem_uname(), UserProfile.getFname(),
                                            UserProfile.getAge(), UserProfile.getCity(),
                                            UserProfile.getEmail(), UserProfile.getExperience(), UserProfile.getCompany(), UserProfile.getLname(), UserProfile.getState()));
                                    startActivity(intent);

                                }
                                else{
                                    // Showing Alert Message
                                    alertDialog.show();
                                    Log.d("ELSE", "ROOR");
                                }
                           //
                        } catch (JSONException e) {
                            Log.d("ERRRRROR", "ROOR");

                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put("email", mEmail.getText().toString());
                //returning parameter
                return params;
            }
        };;

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}


//    //Creating a string request
//    StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLS.REGISTER_URL,
//            new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Log.d("TAG-RESPONSE", response.toString());
//                    try {
//                        JSONObject jsonObject=new JSONObject(response);
//                        JSONArray jsonArray= (JSONArray) jsonObject.get("profile");
//                        for(int i=0;i<jsonArray.length();i++){
//                            JSONObject row = jsonArray.getJSONObject(i);
//                            //String toUname, String fromUname, String Subject, String threadId,String imgPath)
//                            profile = new ProfileDAO();
//                            profile.getEmail();
////                            tempMsg.setToUname(row.getString("toUname"));
////                            tempMsg.setFromUname(row.getString("fromUname"));
////                            tempMsg.setSubject(row.getString("Subject"));
////                            tempMsg.setThreadId(row.getString("threadId"));
////                            tempMsg.setImgPath(row.getString("imgPath"));
////                            //Toast.makeText(context, post.toString(), Toast.LENGTH_LONG).show();
////                            allMessages.add(tempMsg);
//                        }
//                        // Toast.makeText(context, jsonArray.toString(), Toast.LENGTH_LONG).show();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//
//                    new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    //You can handle error here if you want
//                }
//            }){
//        @Override
//        protected Map<String, String> getParams()throws AuthFailureError {
//            Map<String,String> params = new HashMap<>();
//            //Adding parameters to request
//            params.put((AppURLS.EMAIL_KEY, mEmail.getText().toString());
//
//
//
//            //returning parameter
//            return params;
//        }
//    };
//
//
//
//    //Adding the string request to the queue
//    RequestQueue requestQueue = Volley.newRequestQueue(this);
//    requestQueue.add(stringRequest);
//}
//
//
//}
