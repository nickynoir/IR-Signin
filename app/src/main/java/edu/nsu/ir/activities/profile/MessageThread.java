package edu.nsu.ir.activities.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.nsu.ir.activities.SendMsg;
import edu.nsu.ir.util.AppURLS;
import edu.nsu.ir.R;
import edu.nsu.ir.adapters.MThreadAdapter;
import edu.nsu.ir.model.MessageDAO;
import edu.nsu.ir.model.MsgThreadDAO;
import edu.nsu.ir.model.ProfileDAO;


public class MessageThread extends AppCompatActivity {
    private ListView msg_thread;
    private ArrayList<MessageDAO> allmessages = new ArrayList<>();
    private MessageDAO message, Msg;
    private MThreadAdapter adapter;
    private Button reply;
    private ProfileDAO userProfile;
    private MsgThreadDAO msgThread;
    private TextView subject, ToUserName;
    private static String name;
    private static JSONArray jsonArrayChk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread);
        Bundle data = getIntent().getExtras();

        //Gets User profile and the message thread chosen
        userProfile = (ProfileDAO) data.getParcelable("userProfile");
        msgThread = (MsgThreadDAO) data.getParcelable("msgThread");

        //initalizes view componets
        init();


//        Toast.makeText(MessageThread.this, jsonArrayChk.toString(), Toast.LENGTH_SHORT).show();
        // queries back end and populates inbox
        doReg();


        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MessageThread.this, SendMsg.class);

                ///passes thread and profile info and logic value for the next activity
                intent.putExtra("userProfile", new ProfileDAO(userProfile.getItem_uname(), userProfile.getFname(),
                                userProfile.getAge(), userProfile.getCity(),
                                userProfile.getEmail(), userProfile.getExperience(),
                                userProfile.getCompany())
                );

                intent.putExtra("msgThread", new MsgThreadDAO(msgThread.getFromUname(), msgThread.getToUname(),
                                msgThread.getSubject(), msgThread.getThreadId(),
                                msgThread.getImgPath())
                );

                //logic value for the next activity
                intent.putExtra("prev", "true");
                startActivity(intent);

            }
        });


    }

    private void init() {
        msg_thread = (ListView) findViewById(R.id.msg_thread);
        reply = (Button) findViewById(R.id.reply);
        subject = (TextView) findViewById(R.id.subject);
        subject.setText(msgThread.getSubject());
        ToUserName = (TextView) findViewById(R.id.toName);
        ToUserName.setText(msgThread.getToUname());

        //  Intent intent;
        // userProfile = intent.getIntent().getSerializableExtra("userProfile");
        //  usrProfileFeed= (ListView) findViewById(R.id.usrProfileFeed);


    }


    private void doReg() {

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppURLS.ALLMSG,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("TAG-RESPONSE", response.toString());
                        packMessage(response);
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                int time = (int) (System.currentTimeMillis());
                Timestamp tsTemp = new Timestamp(time);
                String ts = tsTemp.toString();
                params.put(AppURLS.KEY_INBOX_ThreadId, msgThread.getThreadId());


                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void packMessage(String response) {
        try {

            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = (JSONArray) jsonObject.get("message");
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject row = jsonArray.getJSONObject(i);
                Msg = new MessageDAO();
                Log.d("userThreadID", msgThread.getThreadId());
                Log.d("RowThreadID", row.getString("threadId"));
                if (row.getString("threadId").matches(msgThread.getThreadId())) {

                    //String img_path, String threadId, String , String subject, String item_content
                    Msg.setImg_path(""/*row.getString("img_path")*/);
                    Msg.setThreadId(row.getString("threadId"));
                    //Msg.setSubject(row.getString("subject"));
                    Msg.setContent(row.getString("content"));
                    Msg.setUname(row.getString("username"));

                    allmessages.add(Msg);
                }
            }
            adapter = new MThreadAdapter(this, allmessages);
            msg_thread.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}