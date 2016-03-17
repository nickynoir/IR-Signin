package com.nykyrian_q.incidentresponse1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ShareCaseActivity extends AppCompatActivity {

    private Button mAttachButton;
    private Button mSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_case);

        mAttachButton = (Button)findViewById(R.id.attachButton);
        mSendButton = (Button)findViewById(R.id.sendButton);

        mAttachButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShareCaseActivity.this, "Adding Attachments", Toast.LENGTH_SHORT).show();
            }
        });

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShareCaseActivity.this, "Share Case", Toast.LENGTH_SHORT).show();
                startCaseHome();
            }
        });
    }
    private void startCaseHome(){
        Intent intent = new Intent(this, CaseHomeActivity.class);
        startActivity(intent);
    }
}
