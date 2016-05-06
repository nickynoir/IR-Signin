package edu.nsu.ir.activities.profile;

import android.app.AlertDialog;
import android.content.Context;
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

import java.util.HashMap;
import java.util.Map;

import edu.nsu.ir.util.AppURLS;
import edu.nsu.ir.model.CCase;

public class LSSignupActivity extends AppCompatActivity {
    private Button mNewAccount;
    private Button mLogin;
    private EditText mFistName, mLastName,mUserName, mEmail, mPassword, mCity, mState, mCompany, mExperience,mPassword2, mContact;
    private Context mContext;
    private CCase cCase;
     private  AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nsu.ir.R.layout.ls_activity_signup);
        init();
        // Create new acount
        mNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                cCase.setName(mFistName.getText().toString());
                Toast.makeText(LSSignupActivity.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();
                if(mPassword.getText().toString().matches(mPassword2.getText().toString()) ) {
                    doReg();
                }else {

                    alertDialog = new AlertDialog.Builder(LSSignupActivity.this).create();

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
                    alertDialog.show();
                }
                Intent intent = new Intent(LSSignupActivity.this, LSLoginActivity.class);
                startActivity(intent);
            }
        });
        // Back to login
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LSSignupActivity.this, LSLoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        cCase = new CCase();
        mContext=this;
        mNewAccount = (Button)findViewById(edu.nsu.ir.R.id.freshAccountButton);
        mLogin = (Button)findViewById(edu.nsu.ir.R.id.backLogin);
        mFistName=(EditText)findViewById(edu.nsu.ir.R.id.firstNameText);
        mLastName=(EditText)findViewById(edu.nsu.ir.R.id.lastNameText);
        mUserName =(EditText)findViewById(edu.nsu.ir.R.id.userNameText);
        mEmail =(EditText)findViewById(edu.nsu.ir.R.id.editText);
        mPassword  =(EditText)findViewById(edu.nsu.ir.R.id.editText2);
        mCity =(EditText)findViewById(edu.nsu.ir.R.id.cityText);
        mState  =(EditText)findViewById(edu.nsu.ir.R.id.stateText);
        mCompany =(EditText)findViewById(edu.nsu.ir.R.id.ls_companyText);
        mExperience =(EditText)findViewById(edu.nsu.ir.R.id.expText);
        mPassword2 = (EditText) findViewById(edu.nsu.ir.R.id.editText4);

    }
    private void doReg(){

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLS.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG-RESPONSE LSSignUp", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
//                params.put(AppURLS.KEY_NAME, userProfile.getName());
//                params.put(AppURLS.KEY_EMAIL, userProfile.getEmail());
//                params.put(AppURLS.KEY_LOCATION, userProfile.getLocation());
//                params.put(AppURLS.KEY_CHURCH, userProfile.getChurch());
//                params.put(AppURLS.KEY_PASSWORD, userProfile.getPassword());
//                params.put(AppURLS.KEY_IMAGEURL, userProfile.getImageURL());
                Log.d("CHECK", "Putting Params");
                params.put(AppURLS.F_NAME_KEY, mFistName.getText().toString());
                params.put(AppURLS.L_NAME_KEY,mLastName.getText().toString());
                params.put(AppURLS.AGE_Key,"N/A");

                params.put(AppURLS.USER_NAME_KEY, mUserName.getText().toString());
                params.put(AppURLS.EMAIL_KEY, mEmail.getText().toString());
                params.put(AppURLS.PASSWORD_KEY, mPassword.getText().toString());
                params.put(AppURLS.CITY_KEY,mCity.getText().toString());
                params.put(AppURLS.STATE_KEY, mState.getText().toString());
                params.put(AppURLS.COMPANY_NAME_KEY, mCompany.getText().toString());
                params.put(AppURLS.EXPERIENCE_KEY,mExperience.getText().toString());
                params.put(AppURLS.IMAGE_KEY, "TEST");
//                params.put(AppURLS.CONTACT_KEY, cCase.getName());



                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
