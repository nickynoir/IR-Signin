package edu.nsu.ir.activities.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.nsu.ir.activities.SendMsg;
import edu.nsu.ir.util.AppURLS;
import edu.nsu.ir.R;
import edu.nsu.ir.adapters.PostAdapter;
import edu.nsu.ir.model.Post;
import edu.nsu.ir.model.ProfileDAO;


public class PViewProfileActivity extends AppCompatActivity {

    private TextView p_view_profile_displayName, local, company,exp, email;
    private Button p_msgM;
    private ListView PostFeed;
   private ArrayList<Post> allpost,demo =new ArrayList<>();

    private ProfileDAO userFound;
    private PostAdapter adapter;
    private ProfileDAO userProfile;
    private Post post,tempPost;
    private Boolean FindME;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_activity_view_profile);
        Bundle data = getIntent().getExtras();

        userProfile = (ProfileDAO) data.getParcelable("userProfile");
        PostFeed = (ListView) findViewById(R.id.usrProfileFeed);


        Intent intent = getIntent();
        //USED TO DETERMINE IF THE CURRENT USERS OR ANOTHER USERS PROFILE IS BEING VIEWED
        FindME = Boolean.parseBoolean(intent.getStringExtra("FindME"));

        //USED TO RETRIEVE THE PROFILE OF THE SEARCHED OR SELECTED NON USER PROFILE
        if (FindME == false){
            userFound = (ProfileDAO) data.getParcelable("userFound");
        }

        //DISPLAYS PROFILE CONTENTS ACCORDINGLY
        init(FindME);

        doReg();
     //   Log.d("DOREG",doReg()+"");
        Log.d("ALLDEMO3",demo+"");
//        adapter=new PostAdapter(this,doReg());
//        PostFeed.setAdapter(adapter);

        ///passes thread and profile info and logic value for the next activity
        p_msgM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PViewProfileActivity.this, SendMsg.class);
                //logic value for the next activity
                intent.putExtra("prev", "fales");
               // PROFILES USERNAME PASSED TO NEXT ACTIVITY
                intent.putExtra("user", userFound.getItem_uname());
                startActivity(intent);

            }
        });

        //NEEDS TO BE CONVERTED FOR POST PURPOSES

        PostFeed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                post = (Post) parent.getItemAtPosition(position);
                //Toast.makeText(getApplicationContext(), profile.getItem_subject(), Toast.LENGTH_LONG).show();
            }
        });

    }

    //DISPLAYS PROFILE CONTENTS ACCORDINGLY
    private void init(Boolean FindME){
        FindME = this.FindME;

        if (FindME == true) {
           // PostFeed = (ListView) findViewById(R.id.PostFeed);

            p_view_profile_displayName = (TextView) findViewById(R.id.p_view_profile_displayName);
            p_view_profile_displayName.setText(userProfile.getItem_uname());
            p_msgM = (Button) findViewById(R.id.msgUser);
            p_msgM.setVisibility(View.GONE);
            local = (TextView) findViewById(R.id.local);
            local.setText(userProfile.getCity() + "," + userProfile.getState());
            company = (TextView) findViewById(R.id.company);
            exp = (TextView) findViewById(R.id.exp);
            exp.setText(userProfile.getExperience() + " Years");
            email = (TextView) findViewById(R.id.email);
            email.setText(userProfile.getEmail());
        }
        else{

            p_view_profile_displayName = (TextView) findViewById(R.id.p_view_profile_displayName);
            p_view_profile_displayName.setText(userFound.getItem_uname());
            p_msgM = (Button) findViewById(R.id.msgUser);
            local = (TextView) findViewById(R.id.local);
            local.setText(userFound.getCity() + "," + userFound.getState());
            company = (TextView) findViewById(R.id.company);
            exp = (TextView) findViewById(R.id.exp);
            exp.setText(userFound.getExperience() + " Years");
            email = (TextView) findViewById(R.id.email);
            email.setText(userFound.getEmail());

        }


    }
    private void doReg(){
        allpost=new ArrayList<>();
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLS.GET_BYEMAILPOST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG-RESPONSE", response.toString());
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray= (JSONArray) jsonObject.get("message");

                            for(int i=0;i<jsonArray.length();i++){
                                Toast.makeText(PViewProfileActivity.this, userProfile.getEmail(), Toast.LENGTH_SHORT).show();
                                JSONObject row = jsonArray.getJSONObject(i);


                              tempPost =new Post(row.getString("title"),row.getString("author"),row.getString("details"));

//                                Toast.makeText(PViewProfileActivity.this, tempPost.getAuthor()+" "+tempPost.getTitle()+" "+tempPost.getMessage(), Toast.LENGTH_SHORT).show();
                                System.out.print(tempPost.getTitle());
                                allpost.add(tempPost);
                            }
                            Log.d("ALLPOSTbefore",allpost+"");
                            adapter=new PostAdapter(getApplicationContext(),allpost );
                            PostFeed.setAdapter(adapter);

                            demo=allpost;
                            Log.d("ALLPOSTafter",allpost+"");
                            Log.d("ALLDEMO",demo+"");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PViewProfileActivity.this, "**********************************************************************************************************************************************************", Toast.LENGTH_SHORT).show();
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
                params.put(AppURLS.EMAIL_Key, userProfile.getEmail());
                //returning parameter
                return params;
            }
        };
        Log.d("ALLPOST2",allpost+"");
        Log.d("ALLDEMO2",demo+"");
        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater=getMenuInflater();
//        menuInflater.inflate(R.menu.menu_main,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.action_Home:
//                Toast.makeText(getApplicationContext(),"Goback home",Toast.LENGTH_LONG).show();
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
