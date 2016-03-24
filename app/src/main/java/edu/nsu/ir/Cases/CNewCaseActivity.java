package edu.nsu.ir.Cases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CNewCaseActivity extends AppCompatActivity {

    private Button mQuestionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nsu.ir.R.layout.c_activity_new_case);

        mQuestionButton = (Button)findViewById(edu.nsu.ir.R.id.questionButton);

        mQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuestionair();
            }
        });
    }

    private void startQuestionair(){
        Intent intent = new Intent(this, CQuestionActivity.class);
        startActivity(intent);
    }
}
