package edu.nsu.ir.activities;

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

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.nsu.ir.util.AppURLS;
import edu.nsu.ir.R;
import edu.nsu.ir.model.MessageDAO;
import edu.nsu.ir.model.MsgThreadDAO;
import edu.nsu.ir.model.ProfileDAO;


/**
 * Created by admin on 3/16/2016.
 */
public class SendMsg extends AppCompatActivity {

    private JSONArray jsonArray,jsonArrayChk;
    private Button send;
    private String to, message, userId, threadId, from,sub,msgId;
    private Date date;
    private EditText viewTo, subject;
    private EditText msg;
    private boolean prev;
    private MsgThreadDAO msgThread;
    private ProfileDAO userProfile;
    private MessageDAO messageDAO;
    private  int time = (int) (System.currentTimeMillis());
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_new_message);
        init();
        //unpacks logic value
        Intent intent = getIntent();
         prev = Boolean.parseBoolean(intent.getStringExtra("prev"));

        //packs profile and thread objects
        Bundle data = getIntent().getExtras();
       userProfile = (ProfileDAO) data.getParcelable("userProfile");
         //   userProfile = new ProfileDAO();
        doRegCheck();

        //this is used to figure out if this is a new thread or a reply
        if(prev == true) {

            msgThread = (MsgThreadDAO) data.getParcelable("msgThread");
            threadId = msgThread.getThreadId();

            subject.setText(msgThread.getSubject());

            EditText to = (EditText) findViewById(R.id.to);
            to.setText(msgThread.getToUname());
        }
        else{
            //Creates thread id

            int time = (int) (System.currentTimeMillis());
            Timestamp tsTemp = new Timestamp(time);
            String ts =  tsTemp.toString();
            Random rn = new Random();
            Integer answer = new Integer(rn.nextInt(6 - 4 + 1) + 4);

            threadId = ts + answer.toString()+userProfile.getItem_uname();
            msgThread = new MsgThreadDAO();
            msgThread.setThreadId(threadId);

        }
        Log.d("ThreadID", msgThread.getThreadId());

        //send button
        send.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {

                to = viewTo.getText().toString();
                message = msg.getText().toString();
                sub = subject.getText().toString();
                from = userProfile.getItem_uname();

                if (threadId != null && !sub.matches("") && !to.matches("") && from != null) {
                    if (Chk()) {

                        if (prev == false) {
                            CreateMsgThread();
                        }

                        doReg();
                        //create thread id and save message along with other users id and date
                        Intent intent = new Intent(SendMsg.this, Home.class);
                   /* intent.putExtra("userProfile", new ProfileDAO(userProfile.getItem_uname(),userProfile.getFname(),
                            userProfile.getAge(),userProfile.getCity(),
                            userProfile.getEmail(),userProfile.getExperience(), userProfile.getCompany())
                    );*/
                        startActivityForResult(intent, 0);
                    } else                        {

                        AlertDialog alertDialog = new AlertDialog.Builder(SendMsg.this).create();

                        // Setting Dialog Title
                        alertDialog.setTitle("Alert Dialog");

                        // Setting Dialog Message
                        alertDialog.setMessage("THIS USERNAME DOES NOT EXIST");


                        // Setting OK Button
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog closed
                                Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                            }
                        });

                        // Showing Alert Message
                        alertDialog.show();

                    }


                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(SendMsg.this).create();

                    // Setting Dialog Title
                    alertDialog.setTitle("Alert Dialog");

                    // Setting Dialog Message
                    alertDialog.setMessage("CHECK IN PUT!");


                    // Setting OK Button
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog closed
                            Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                        }
                    });

                    // Showing Alert Message
                    alertDialog.show();
                }


            }
        });
    }


    private void init(){
        //load values from database
        send= (Button) findViewById(R.id.send);
        msg = (EditText) findViewById(R.id.message);
        viewTo = (EditText) findViewById(R.id.to);
        subject = (EditText) findViewById(R.id.subject);


    }
    private boolean Chk(){
        try {
            for(int i=0;i<jsonArrayChk.length();i++){
                JSONObject row = jsonArrayChk.getJSONObject(i);
                Log.d("Profiles",jsonArrayChk.toString());
                if(to.matches(row.getString("uname"))){
                    return true;
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;

    }


    private void CreateMsgThread(){

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLS.CREATE_MSGTHREAD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG-RESPONSE", response.toString());

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
                Timestamp tsTemp = new Timestamp(time);
                String ts =  tsTemp.toString();

                params.put(AppURLS.KEY_INBOX_TO_UNAME, to);
                params.put(AppURLS.KEY_INBOX_FROM_UNAME, from);
               // params.put(AppURLS.KEY_TimeStamp, ts);
                params.put(AppURLS.KEY_INBOX_ThreadId, msgThread.getThreadId());
                params.put(AppURLS.KEY_MESSAGE_SUBJECT, sub);



                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void doReg(){

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLS.CREATE_MSG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG-RESPONSE", response.toString());

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
                Timestamp tsTemp = new Timestamp(time);
                String ts =  tsTemp.toString();

                Random rn2 = new Random();
                Integer answer2 = new Integer(rn2.nextInt(6 - 4 + 1) + 4);
                msgId = ts + answer2.toString()+userProfile.getItem_uname();
               // params.put(AppURLS.KEY_INBOX_TO_UNAME, to);
               // params.put(AppURLS.KEY_INBOX_FROM_UNAME, userProfile.getItem_uname());

                params.put(AppURLS.KEY_MESSAGE_ID, msgId);
                params.put(AppURLS.KEY_TimeStamp, ts);
                Log.d("threadID", msgThread.getThreadId());
                params.put(AppURLS.KEY_INBOX_ThreadId, msgThread.getThreadId());
                params.put(AppURLS.KEY_MESSAGE_Content, message);
                params.put(AppURLS.KEY_MESSAGE_USER, userProfile.getItem_uname());
                Log.d("msgID", msgId);
                Log.d("message", message);



                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void doRegCheck(){

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLS.GET_ALL_USERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG-RESPONSE", response.toString());
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            jsonArrayChk= (JSONArray) jsonObject.get("profile");
                        } catch (JSONException e) {
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
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



}

