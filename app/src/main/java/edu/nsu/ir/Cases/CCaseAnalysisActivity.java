package edu.nsu.ir.Cases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import edu.nsu.ir.R;
import edu.nsu.ir.Steps.CStepView;

public class CCaseAnalysisActivity extends AppCompatActivity {

    private Button mShareAnalysisButton;
    private Button mBacktoCasesButton;
    private Button mCaseViewButton;
    private String choice1;
    ListView lvSteps;

    String[] toolList = {
            "Forensic workastation and/or backup devices",
            "Laptops",
            "Spare workstations, servers, and networking equipment, or the virtualized equivalents",
            "Blank removable media",
            "Portable printer",
            "Packet sniffers and protocol analyzers",
            "Digital forensic software",
            "Removable media",
            "Evidence gathering accessories",
            "Clean OS images"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nsu.ir.R.layout.c_activity_case_analysis);

        init();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice, toolList);
        lvSteps.setAdapter(adapter);
        getChoices();
        // Share Button
        mShareAnalysisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CCaseAnalysisActivity.this, "Share from here Or Maybe Save", Toast.LENGTH_SHORT).show();
            }
        });

        //View Case Details
        mCaseViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCaseDetails();
            }
        });

        // Back to Case Home
        mBacktoCasesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save on Exit??
                startCasesHome();
            }
        });
    }

    private void init() {
        lvSteps = (ListView)findViewById(R.id.stepsList);
        mShareAnalysisButton = (Button)findViewById(R.id.shareAnalysisButton);
        mBacktoCasesButton = (Button)findViewById(edu.nsu.ir.R.id.backCasesButton);
        mCaseViewButton = (Button)findViewById(edu.nsu.ir.R.id.caseDetailsViewButton);
    }

    private void getChoices(){
        choice1 = getIntent().getStringExtra("choice1");
    }

    private void makeChoices(){
        if(choice1 == "ON"){
            Toast.makeText(CCaseAnalysisActivity.this, "Machines is on", Toast.LENGTH_SHORT).show();
        }
    }

    private void startCasesHome(){
        Intent intent = new Intent(this, CCaseHomeActivity.class);
        startActivity(intent);
    }

    private void startCaseDetails(){
        Intent intent = new Intent(this, CCaseDetailsActivity.class);
        startActivity(intent);
    }

    private void startStpOne(){
        Intent intent = new Intent(this, CStepView.class);
        startActivity(intent);
    }
}
