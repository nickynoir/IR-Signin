package com.nykyrian_q.incidentresponse1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class CaseHomeActivity extends AppCompatActivity {

    private ListView mCaseListView;
    private Button mNewCaseButton;
    private Button mCurrentCaseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_home);

        mNewCaseButton = (Button)findViewById(R.id.newCaseButton);
        mCurrentCaseButton = (Button)findViewById(R.id.currentCaseButton);

        mNewCaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CaseHomeActivity.this, "Starting a new case", Toast.LENGTH_SHORT).show();
                startNewCase();
            }
        });

        mCurrentCaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCaseDetails();
            }
        });
    }

    private void startNewCase(){
        Intent intent = new Intent(this, NewCaseActivity.class);
        startActivity(intent);
    }

    private void startCaseDetails(){
        Intent intent = new Intent(this, CaseDetailsActivity.class);
        startActivity(intent);
    }
}
