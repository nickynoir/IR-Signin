package edu.nsu.ir.Cases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CCaseDetailsActivity extends AppCompatActivity {

    private Button mShareButton;
    private RadioGroup mStatusGroup;
    private RadioButton mActiveButton, mClosedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nsu.ir.R.layout.c_activity_case_details);

        mShareButton = (Button)findViewById(edu.nsu.ir.R.id.shareDetailsButton);
        mStatusGroup = (RadioGroup)findViewById(edu.nsu.ir.R.id.caseStatusRadioGroup);
        mActiveButton = (RadioButton)findViewById(edu.nsu.ir.R.id.activeRadioButton);
        mClosedButton = (RadioButton)findViewById(edu.nsu.ir.R.id.closedRadioButton);

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startShareView();
            }
        });
    }
    private void startShareView(){
        Intent intent = new Intent(this, CShareCaseActivity.class);
        startActivity(intent);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case edu.nsu.ir.R.id.activeRadioButton:
                if (checked)
                    Toast.makeText(CCaseDetailsActivity.this, "Case is Active", Toast.LENGTH_SHORT).show();
                break;
            case edu.nsu.ir.R.id.closedRadioButton:
                if (checked)
                    Toast.makeText(CCaseDetailsActivity.this, "Case is Closed", Toast.LENGTH_SHORT).show();
        }
    }
}
