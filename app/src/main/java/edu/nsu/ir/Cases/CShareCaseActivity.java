package edu.nsu.ir.Cases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CShareCaseActivity extends AppCompatActivity {

    private Button mAttachButton;
    private Button mSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nsu.ir.R.layout.c_activity_share_case);

        init();
        // Add attachments
        mAttachButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CShareCaseActivity.this, "Adding Attachments", Toast.LENGTH_SHORT).show();
            }
        });
        //Send email
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CShareCaseActivity.this, "Share Case", Toast.LENGTH_SHORT).show();
                startCaseHome();
            }
        });
    }

    private void init() {
        mAttachButton = (Button)findViewById(edu.nsu.ir.R.id.attachButton);
        mSendButton = (Button)findViewById(edu.nsu.ir.R.id.sendButton);
    }

    private void startCaseHome(){
        Intent intent = new Intent(this, CCaseHomeActivity.class);
        startActivity(intent);
    }
}
