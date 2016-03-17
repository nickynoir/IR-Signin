package com.nykyrian_q.incidentresponse1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {

    private Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mSubmitButton = (Button)findViewById(R.id.submitButton);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(QuestionActivity.this, "Preparing Response Steps", Toast.LENGTH_SHORT).show();
                startCaseAnalysis();
            }
        });
    }
    private void startCaseAnalysis(){
        Intent intent = new Intent(this, CaseAnalysisActivity.class);
        startActivity(intent);
    }
}
