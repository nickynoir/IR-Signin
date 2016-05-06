package edu.nsu.ir.activities.cases;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.Date;

import edu.nsu.ir.R;
import edu.nsu.ir.model.CaseDAO;
import edu.nsu.ir.model.ProfileDAO;

public class CNewCaseActivity extends AppCompatActivity {

    private Button mQuestionButton;
    private EditText date, name, caseNumber,evidenceRecived,caseDescText;
    private CaseDAO Case = new CaseDAO();
    private ProfileDAO userProfile;
    private Date d;
    private String caseID, status,desc,dateModified, creatorEmail,osType,serialNumber,modelNumber,hostName, macAddress,ipAddress,email;
    private  int time = (int) (System.currentTimeMillis());

//    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nsu.ir.R.layout.c_activity_new_case);
        init();

    }



    private void init() {
        Bundle data = getIntent().getExtras();
        //Gets User profile and the message thread chosen
        userProfile = (ProfileDAO) data.getParcelable("userProfile");

        d = new Date();
        CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());

        date = (EditText) findViewById(R.id.dateText);
        date.setText(s.toString());
        name = (EditText) findViewById(R.id.analystNameText);
        name.setText(userProfile.getFname()+" "+userProfile.getLname());
        evidenceRecived     = (EditText) findViewById(R.id.evidenceText);
        caseNumber  = (EditText) findViewById(R.id.etCaseNumber);
        caseDescText = (EditText) findViewById(R.id.caseDescText);

        mQuestionButton = (Button)findViewById(edu.nsu.ir.R.id.stepHomeButton);
        mQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSteps();
            }
        });
    }




    private void startSteps(){
        doReg();

    }

    private void doReg(){

//        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLS.CREATE_CASE_URL,
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
               String caseId = ts + answer2.toString()+userProfile.getItem_uname();
                // params.put(AppURLS.KEY_INBOX_TO_UNAME, to);
                // params.put(AppURLS.KEY_INBOX_FROM_UNAME, userProfile.getItem_uname());
                Log.d("TAG-RESPONSE", caseId);
                params.put(AppURLS.CASE, caseId);
                Case.setCase_id(caseId);
                params.put(AppURLS.STATUS, "true");
                Case.setCase_status("true");


                params.put(AppURLS.DESC, caseDescText.getText().toString());
                Case.setDesc(caseDescText.getText().toString());

                params.put(AppURLS.DATE_MOD, " ");
                Case.setDateMod(" ");

                params.put(AppURLS.CreatorEmail, userProfile.getEmail());

                params.put(AppURLS.CASE_NUMBER, caseNumber.getText().toString());
                Case.setCaseNumber(caseNumber.getText().toString());

                params.put(AppURLS.OsType, " ");
                Case.setOs_type(" ");

                params.put(AppURLS.SerialNumber, " ");
                Case.setSerialNumber(" ");

                params.put(AppURLS.ModelNumber, "");
                Case.setModelNumber("");

                params.put(AppURLS.HostName, "");
                Case.setHostName("");

                params.put(AppURLS.MacAddress, " ");
                Case.setMacAddress("");
                params.put(AppURLS.IpAddress, "");
                Case.setIpAddress("");

                params.put(AppURLS.Email, userProfile.getEmail());
                Log.d("TAG-RESPONSE", Case.getCase_id());


                Intent intent = new Intent(getApplicationContext(),StepsHome.class);
                intent.putExtra("Case", Case);
                intent.putExtra("userProfile", userProfile);
                startActivity(intent);







                //returning parameter
                return params;
            }

        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
