package edu.nsu.ir.activities.cases;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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

import edu.nsu.ir.util.AppURLS;
import edu.nsu.ir.model.CCase;
import edu.nsu.ir.R;
import edu.nsu.ir.adapters.CListCaseAdapter;
import edu.nsu.ir.model.CaseDAO;
import edu.nsu.ir.model.ProfileDAO;

public class CCaseHomeActivity extends AppCompatActivity {

    private ListView mCaseListView;
    private Button mNewCaseButton;
    private CListCaseAdapter mCaseAdapter;
    private ArrayList<CaseDAO> allCases;
    private ProfileDAO userProfile;
    private CaseDAO tempCase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nsu.ir.R.layout.c_activity_case_home);

        init();

        //binding adapter to listview
        mCaseListView.setAdapter(mCaseAdapter);
        mNewCaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CCaseHomeActivity.this, "Starting a new case", Toast.LENGTH_SHORT).show();

                startNewCase();
            }
        });

        mCaseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            CCase value=(CCase)parent.getItemAtPosition(position);
               Toast.makeText(getApplicationContext(),value.getName(),Toast.LENGTH_LONG).show();
                startCaseDetails(value);
            }
        });

    }
    private void init(){
        Bundle data = getIntent().getExtras();

        //Gets User profile and the message thread chosen
        userProfile = (ProfileDAO) data.getParcelable("userProfile");
        allCases=new ArrayList<>();
        //Log.d("INSIDE init", dummyCases().toString());

        mCaseListView=(ListView)findViewById(R.id.c_lvCases);
        mNewCaseButton = (Button)findViewById(edu.nsu.ir.R.id.newCaseButton);
        doReg();
    }
    private void startNewCase(){
        Intent intent = new Intent(this, CNewCaseActivity.class);
        intent.putExtra("userProfile", userProfile);
        startActivity(intent);
    }

    private void startCaseDetails(CCase cCase){
        Intent intent = new Intent(this, CCaseDetailsActivity.class);
        intent.putExtra("case", cCase);
        intent.putExtra("userProfile", userProfile);

        startActivity(intent);
    }

    private void doReg(){

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppURLS.CASE_BY_EMAIL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG-RESPONSE", response.toString());

                        //CREATES JSON AND ASSIGNS ITS VALUES TO AN ARRAY OF MESSAGE THREADS
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray= (JSONArray) jsonObject.get("case");


                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject row = jsonArray.getJSONObject(i);

                                if(row.getString("email").matches(userProfile.getEmail()))
                                {
                                    tempCase = new CaseDAO();
                                    tempCase.setCase_id(row.getString("caseID"));
                                    tempCase.setCase_name(row.getString("caseName"));
                                    tempCase.setCase_status(row.getString("caseStatus"));
                                   tempCase.setCaseNumber(row.getString("caseNumber"));
                                    tempCase.setOs_type(row.getString("osType"));
                                   // tempCase.setCourt_number(row.getString(""));
                                    tempCase.setEvidence(row.getString("evidence"));
                                    tempCase.setSerialNumber(row.getString("erialNumber"));
                                    tempCase.setModelNumber(row.getString("odelNumber"));
                                    tempCase.setHostName(row.getString("ostName"));
                                    tempCase.setMacAddress(row.getString("MacAddress"));
                                    tempCase.setIpAddress(row.getString("IpAddress"));

                                }
                                allCases.add(tempCase);
                            }
                            mCaseAdapter=new CListCaseAdapter(getApplicationContext(),allCases);
                            mCaseListView.setAdapter(mCaseAdapter);
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
                params.put("username", userProfile.getEmail());
                //returning parameter
                return params;
            }
        };;

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
