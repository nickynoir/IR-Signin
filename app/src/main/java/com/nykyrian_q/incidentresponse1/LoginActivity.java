package com.nykyrian_q.incidentresponse1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button mLoginButton;
    private Button mNewAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginButton = (Button)findViewById(R.id.loginButton);
        mNewAccountButton = (Button)findViewById(R.id.newAccountButton);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(LoginActivity.this, "This button works!", Toast.LENGTH_SHORT).show();
                startCasesHome();
            }
        });

        mNewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignup();
            }
        });
    }

    private void startSignup(){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    private void startCasesHome(){
        Intent intent = new Intent(this, CaseHomeActivity.class);
        startActivity(intent);
    }
}
