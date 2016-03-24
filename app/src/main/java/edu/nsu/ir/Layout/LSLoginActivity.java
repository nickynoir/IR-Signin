package edu.nsu.ir.Layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.nsu.ir.Cases.CCaseHomeActivity;

public class LSLoginActivity extends AppCompatActivity {
    private Button mLoginButton;
    private Button mNewAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nsu.ir.R.layout.ls_activity_login);

        mLoginButton = (Button)findViewById(edu.nsu.ir.R.id.loginButton);
        mNewAccountButton = (Button)findViewById(edu.nsu.ir.R.id.newAccountButton);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(LSLoginActivity.this, "This button works!", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(this, LSSignupActivity.class);
        startActivity(intent);
    }

    private void startCasesHome(){
        Intent intent = new Intent(this, CCaseHomeActivity.class);
        startActivity(intent);
    }
}
