package edu.nsu.ir.Cases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import edu.nsu.ir.R;

public class CCaseAnalysisActivity extends AppCompatActivity {

    private Button mShareAnalysisButton;
    private Button mBacktoCasesButton;
    private Button mCaseViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nsu.ir.R.layout.c_activity_case_analysis);

        mShareAnalysisButton = (Button)findViewById(R.id.shareAnalysisButton);
        mBacktoCasesButton = (Button)findViewById(edu.nsu.ir.R.id.backCasesButton);
        mCaseViewButton = (Button)findViewById(edu.nsu.ir.R.id.caseDetailsViewButton);

        mShareAnalysisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CCaseAnalysisActivity.this, "Share from here Or Maybe Save", Toast.LENGTH_SHORT).show();
            }
        });

        mCaseViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCaseDetails();
            }
        });

        mBacktoCasesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save on Exit??
                startCasesHome();
            }
        });
    }
    private void startCasesHome(){
        Intent intent = new Intent(this, CCaseHomeActivity.class);
        startActivity(intent);
    }

    private void startCaseDetails(){
        Intent intent = new Intent(this, CCaseDetailsActivity.class);
        startActivity(intent);
    }
}
