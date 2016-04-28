package edu.nsu.ir.Cases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import edu.nsu.ir.R;

public class CNewCaseActivity extends AppCompatActivity {

    private Button mQuestionButton;
//    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nsu.ir.R.layout.c_activity_new_case);
        init();

    }



    private void init() {
        mQuestionButton = (Button)findViewById(edu.nsu.ir.R.id.questionButton);
        mQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuestionair();
            }
        });
    }




    private void startQuestionair(){
        Intent intent = new Intent(this, CCaseAnalysisActivity.class);
        startActivity(intent);
    }
}
