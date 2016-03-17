package com.nykyrian_q.incidentresponse1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewCaseActivity extends AppCompatActivity {

    private Button mQuestionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_case);

        mQuestionButton = (Button)findViewById(R.id.questionButton);

        mQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuestionair();
            }
        });
    }

    private void startQuestionair(){
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
}
