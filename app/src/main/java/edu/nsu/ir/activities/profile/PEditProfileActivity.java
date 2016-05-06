package edu.nsu.ir.activities.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import edu.nsu.ir.activities.Home;
import edu.nsu.ir.R;
import edu.nsu.ir.model.ProfileDAO;


public class PEditProfileActivity extends AppCompatActivity {
    private Button save;
    private EditText fname,lname, city, state,company, contact;
    private TextView p_name,p_local,p_company,p_exp,p_email;
    private String nm,em,cty,st;
    private int id;
    private ProfileDAO userProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_activity_pedit_profile);

        //unpacks current user profile
        Bundle data = getIntent().getExtras();
        userProfile = (ProfileDAO) data.getParcelable("userProfile");

        //initalizes and populates view componets
        init();

        //save changes button
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(fname.getText().toString().matches("")){
                    fname.setText(userProfile.getFname());
                    Toast.makeText(PEditProfileActivity.this, "FIRST NAME FIELD WAS EMPTY", Toast.LENGTH_SHORT).show();

                }
                if(lname.getText().toString().matches("")){
                     lname.setText(userProfile.getLname());
                    Toast.makeText(PEditProfileActivity.this, "LAST NAME FIELD WAS EMPTY", Toast.LENGTH_SHORT).show();

                }
                if(state.getText().toString().matches("")){
                    state.setText(userProfile.getState());
                    Toast.makeText(PEditProfileActivity.this, "STATE FIELD WAS EMPTY", Toast.LENGTH_SHORT).show();


                }
                if(contact.getText().toString().matches("")){
                    contact.setText(userProfile.getContact());
                    Toast.makeText(PEditProfileActivity.this, "contact FIELD WAS EMPTY", Toast.LENGTH_SHORT).show();


                }

                if(company.getText().toString().matches("")){
                    company.setText(userProfile.getCompany());
                    Toast.makeText(PEditProfileActivity.this, "COMPANY FIELD WAS EMPTY", Toast.LENGTH_SHORT).show();


                }
                if(city.getText().toString().matches("")){
                    city.setText(userProfile.getCity());
                    Toast.makeText(PEditProfileActivity.this, "CITY FIELD WAS EMPTY", Toast.LENGTH_SHORT).show();


                }
                    userProfile.setFname(fname.getText().toString());
                    userProfile.setLname(lname.getText().toString());
                    userProfile.setState(state.getText().toString());
                //    userProfile.setContact(contact.getText().toString());
                    userProfile.setCompany(company.getText().toString());
                    userProfile.setCity(city.getText().toString());
                Log.d("TAG-RESPONSE", "made it1");
                doReg();
                Intent intent = new Intent(PEditProfileActivity.this, Home.class);
                intent.putExtra("userProfile", new ProfileDAO(userProfile.getItem_uname(), userProfile.getFname(),
                        userProfile.getAge(), userProfile.getCity(),
                        userProfile.getEmail(), userProfile.getExperience(), userProfile.getCompany(), userProfile.getLname(), userProfile.getState()));
                startActivity(intent);


            }
        });
    }

    //INITIALIZES VIEW COMPONETS
    private void init(){

        save= (Button) findViewById(R.id.submit);
        p_name = (TextView) findViewById(R.id.p_name);
        p_name.setText(userProfile.getFname()+ " "+userProfile.getLname());
        fname = (EditText) findViewById(R.id.fname);
        fname.setText(userProfile.getFname());
        lname = (EditText) findViewById(R.id.lname);
        lname.setText(userProfile.getLname());
        company = (EditText) findViewById(R.id.company);
        company.setText(userProfile.getCompany());
        contact = (EditText) findViewById(R.id.contact);
        contact.setText(userProfile.getContact());
        city = (EditText) findViewById(R.id.city);
        city.setText(userProfile.getCity());
        p_local = (TextView) findViewById(R.id.p_local);
        p_local.setText(userProfile.getCity()+ ","+userProfile.getState());
        p_company =  (TextView) findViewById(R.id.p_company);
        p_company.setText(userProfile.getCompany());
        p_exp = (TextView) findViewById(R.id.p_exp);
        p_exp.setText(userProfile.getExperience()+" Years");
        p_email  = (TextView) findViewById(R.id.p_email);
        p_email.setText(userProfile.getEmail());
       state = (EditText) findViewById(R.id.st);
        state.setText(userProfile.getState());
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        if (id == R.id.action_Home) {
//            return true;
//        }
//        if (id == R.id.action_Inbox) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    private void doReg(){

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLS.PROFILE_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG-RESPONSE", response.toString());


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("TAG-RESPONSE", error.getMessage());
                        Log.d("TAG-RESPONSE", "ERROR");
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                Log.d("TAG-RESPONSE", "made it2");
                params.put(AppURLS.F_NAME_KEY, userProfile.getFname());
                params.put(AppURLS.L_NAME_KEY,userProfile.getLname());
                params.put(AppURLS.AGE_KEY,userProfile.getEmail());
                params.put(AppURLS.COMPANY_NAME_KEY,userProfile.getCompany());
                params.put(AppURLS.CITY_KEY, userProfile.getCity());
                params.put(AppURLS.STATE_KEY, userProfile.getState());
                params.put(AppURLS.USER_NAME_KEY,userProfile.getItem_uname());
                params.put(AppURLS.KEY_INBOX_ImgPath,""/*userProfile.getImage_path()*/);
                params.put(AppURLS.EXPERIENCE_KEY, userProfile.getExperience());
                Log.d("TAG-RESPONSE", "made it3");

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
