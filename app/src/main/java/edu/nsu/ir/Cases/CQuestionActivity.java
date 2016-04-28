package edu.nsu.ir.Cases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import edu.nsu.ir.R;

public class CQuestionActivity extends AppCompatActivity {

    private Button mSubmitButton;
    private String currentChoice1;
    private String currentChoice2;
    private String currentChoice3;
    private String currentChoice4;

    private TextView mQ2Text;
    private Spinner q1Spinner;
    private Spinner q2Spinner;
    private ArrayAdapter<CharSequence> q3AdapterWindows;
    private ArrayAdapter<CharSequence> q3AdapterMac;
    private ArrayAdapter<CharSequence> q3AdapterLinux;
    private ArrayAdapter<CharSequence> q3AdapterUnknown;
    private Spinner q3Spinner;
    private Spinner q4Spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nsu.ir.R.layout.c_activity_question);

        mSubmitButton = (Button)findViewById(edu.nsu.ir.R.id.submitButton);

        init();
        spinnerInit();

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCaseAnalysis();
            }
        });

    }

    private void init() {
        mQ2Text = (TextView) findViewById(R.id.q2Text);
    }

    private void spinnerInit() {
        // question 1
        q1Spinner = (Spinner) findViewById(R.id.q1Spinner);
        final ArrayAdapter<CharSequence> q1Adapter = ArrayAdapter.createFromResource(this,
                R.array.machine_state, android.R.layout.simple_spinner_item);
        q1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        q1Spinner.setAdapter(q1Adapter);
        q1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentChoice1 =  q1Spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        //question 2
        q2Spinner = (Spinner)findViewById(R.id.q2Spinner);
        ArrayAdapter<CharSequence> q2Adapter = ArrayAdapter.createFromResource(this, R.array.os_type, android.R.layout.simple_spinner_item);
        q2Spinner.setAdapter(q2Adapter);
        q2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentChoice2 = q2Spinner.getSelectedItem().toString();
                // based on currentChoice2
                switch (position){
                    case 0:
                        q3Spinner.setAdapter(q3AdapterWindows);
                        break;
                    case 1:
                        q3Spinner.setAdapter(q3AdapterLinux);
                        break;
                    case 2:
                        q3Spinner.setAdapter(q3AdapterMac);
                        break;
                    case 3:
                        q3Spinner.setAdapter(q3AdapterUnknown);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //question 3
        q3Spinner = (Spinner)findViewById(R.id.q3Spinner);
        q3AdapterWindows = ArrayAdapter.createFromResource(this, R.array.windows_version, android.R.layout.simple_spinner_item);
        q3AdapterMac = ArrayAdapter.createFromResource(this, R.array.mac_version, android.R.layout.simple_spinner_item);
        q3AdapterLinux = ArrayAdapter.createFromResource(this, R.array.linux_version, android.R.layout.simple_spinner_item);
        q3AdapterUnknown = ArrayAdapter.createFromResource(this, R.array.unknown_version, android.R.layout.simple_spinner_item);
        q3Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               currentChoice3 = q3Spinner.getSelectedItem().toString();
            }
           @Override
           public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //question 4
        q4Spinner = (Spinner)findViewById(R.id.q4Spinner);
        final ArrayAdapter<CharSequence> q4Adapter = ArrayAdapter.createFromResource(this,
                R.array.attack_vector, android.R.layout.simple_spinner_item);
        q1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        q4Spinner.setAdapter(q4Adapter);
        q4Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentChoice4 = q4Spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    private void startCaseAnalysis(){
        Intent intent = new Intent(this, CCaseAnalysisActivity.class);
        intent.putExtra("choice1", currentChoice1);
        startActivity(intent);

    }
}
