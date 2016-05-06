package edu.nsu.ir.activities.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.nsu.ir.util.AppURLS;
import edu.nsu.ir.R;
import edu.nsu.ir.adapters.InboxAdapter;
import edu.nsu.ir.model.MsgThreadDAO;
import edu.nsu.ir.model.ProfileDAO;

public class PViewInboxActivity extends AppCompatActivity {
    private ListView inbox_items;
    private ArrayList<MsgThreadDAO> allMessages=new ArrayList<>();
    private MsgThreadDAO Messages,tempMsg;
    private InboxAdapter adapter;
    private ProfileDAO userProfile;
    private String str1, str2, str3,str4,str5,str6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inbox);
        // UNPACKS PROFILE
        Bundle data = getIntent().getExtras();
        userProfile = (ProfileDAO) data.getParcelable("userProfile");

        init();
        doReg();


        //LIST OF INBOX THREADS
         inbox_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);

                Messages =(MsgThreadDAO) parent.getItemAtPosition(position);

                    //ORGANIZES TO AND FROM FOR PROPER DATA RETRIEVAL AND POSTING IN THE NEXT VIEW
                    // BY COMPARING THE CURRENT USER TO THE TO AND FROM OF THE CLICKED THREAD
                   /*str1 = userProfile.getItem_uname();
                    str2 = Messages.getFromUname();
                    str3 = Messages.getToUname();
                    if(str1!=str2){
                        Messages.setToUname(str2);
                        Messages.setFromUname(str1);
                        Toast.makeText(getApplicationContext(),str1+"<str1 "+str2+"<str2",Toast.LENGTH_LONG).show();
                    }
                else{
                        Messages.setToUname(str3);
                        Messages.setFromUname(str1);
                        Toast.makeText(getApplicationContext(),str1+"<str1 "+str3+"<str3" +
                                "",Toast.LENGTH_LONG).show();
                    }*/
                //PASSES BOTH THE CURRENT USER AND MSG THREAD TO THE NEXT VIEW
                Intent intent = new Intent(PViewInboxActivity.this, MessageThread.class);
                intent.putExtra("userProfile", new ProfileDAO(userProfile.getItem_uname(),userProfile.getFname(),
                        userProfile.getAge(),userProfile.getCity(),
                        userProfile.getEmail(),userProfile.getExperience(),userProfile.getCompany(),userProfile.getLname(),userProfile.getState()));

                intent.putExtra("msgThread", new MsgThreadDAO(Messages.getFromUname(), Messages.getToUname(),
                                                              Messages.getSubject(),Messages.getThreadId(),
                                                              Messages.getImgPath())
                );

                startActivity(intent);
           }



         });

    }

    private void init() {
        inbox_items= (ListView) findViewById(R.id.inbox_items);

    }
    private void doReg(){

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppURLS.ALLTHREADS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG-RESPONSE", response.toString());

                        //CREATES JSON AND ASSIGNS ITS VALUES TO AN ARRAY OF MESSAGE THREADS
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray= (JSONArray) jsonObject.get("msgthread");


                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject row = jsonArray.getJSONObject(i);
                                Log.d("UserUn",userProfile.getItem_uname());
                                Log.d("FromUname",row.getString("fromUname"));
                                Log.d("ToUname",row.getString("toUname"));

                                if(row.getString("fromUname").matches(userProfile.getItem_uname())||row.getString("toUname").matches(userProfile.getItem_uname()))
                                {
                                    tempMsg = new MsgThreadDAO();
                                    str4 = userProfile.getItem_uname();
                                    str5 = row.getString("fromUname");
                                    str6 = row.getString("toUname");
                                    if (str4 == str5) {
                                        tempMsg.setToUname(str6);
                                        tempMsg.setFromUname(str5);
                                    } else {

                                        tempMsg.setToUname(str5);
                                        tempMsg.setFromUname(str6);
                                    }

                                    tempMsg.setSubject(row.getString("Subject"));
                                    tempMsg.setThreadId(row.getString("threadId"));
                                    //tempMsg.setImgPath(row.getString("imgPath"));
                                    //Toast.makeText(context, post.toString(), Toast.LENGTH_LONG).show();
                                    allMessages.add(tempMsg);
                                }
                            }
                            adapter=new InboxAdapter(getApplicationContext(),allMessages);
                            inbox_items.setAdapter(adapter);
                            // Toast.makeText(context, jsonArray.toString(), Toast.LENGTH_LONG).show();
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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put("username", userProfile.getItem_uname());
                //returning parameter
                return params;
            }
        };;

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}