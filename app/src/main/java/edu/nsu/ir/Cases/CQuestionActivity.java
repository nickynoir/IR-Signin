package edu.nsu.ir.Cases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CQuestionActivity extends AppCompatActivity {

    private Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nsu.ir.R.layout.c_activity_question);

        mSubmitButton = (Button)findViewById(edu.nsu.ir.R.id.submitButton);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CQuestionActivity.this, "Preparing Response Steps", Toast.LENGTH_SHORT).show();
                startCaseAnalysis();
            }
        });
    }
    private void startCaseAnalysis(){
        Intent intent = new Intent(this, CCaseAnalysisActivity.class);
        startActivity(intent);
    }
}
