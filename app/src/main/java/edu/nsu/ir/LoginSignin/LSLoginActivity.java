package edu.nsu.ir.LoginSignin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import edu.nsu.ir.Cases.AppURLS;
import edu.nsu.ir.Cases.CCaseHomeActivity;
import edu.nsu.ir.R;


public class LSLoginActivity extends AppCompatActivity {
    private Button mLoginButton;
    private Button mNewAccountButton;
    private EditText mEmail, mPassword;
    private ProfileDAO profile;

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
//                 ******Password and Email Checking *********
//                if (mEmail.getText().toString().equals("admin") &&
//                        mPassword.getText().toString().equals("admin")) {
//                    startCasesHome();
//                }else {
//                    Toast.makeText(LSLoginActivity.this, "Login Incorrect", Toast.LENGTH_SHORT).show();
//                }

                startCasesHome();
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
