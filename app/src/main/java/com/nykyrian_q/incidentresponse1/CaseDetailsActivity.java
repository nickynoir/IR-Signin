package com.nykyrian_q.incidentresponse1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CaseDetailsActivity extends AppCompatActivity {

    private Button mShareButton;
    private RadioGroup mStatusGroup;
    private RadioButton mActiveButton, mClosedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_details);

        mShareButton = (Button)findViewById(R.id.shareDetailsButton);
        mStatusGroup = (RadioGroup)findViewById(R.id.caseStatusRadioGroup);
        mActiveButton = (RadioButton)findViewById(R.id.activeRadioButton);
        mClosedButton = (RadioButton)findViewById(R.id.closedRadioButton);

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startShareView();
            }
        });
    }
    private void startShareView(){
        Intent intent = new Intent(this, ShareCaseActivity.class);
        startActivity(intent);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.activeRadioButton:
                if (checked)
                    Toast.makeText(CaseDetailsActivity.this, "Case is Active", Toast.LENGTH_SHORT).show();
                break;
            case R.id.closedRadioButton:
                if (checked)
                    Toast.makeText(CaseDetailsActivity.this, "Case is Closed", Toast.LENGTH_SHORT).show();
        }
    }
}
