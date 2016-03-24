package edu.nsu.ir.LoginSignin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.nsu.ir.Cases.CCaseHomeActivity;
import edu.nsu.ir.R;

public class LSLoginActivity extends AppCompatActivity {
    private Button mLoginButton;
    private Button mNewAccountButton;
    private EditText mEmail, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nsu.ir.R.layout.ls_activity_login);

        mLoginButton = (Button)findViewById(edu.nsu.ir.R.id.loginButton);
        mNewAccountButton = (Button)findViewById(edu.nsu.ir.R.id.newAccountButton);
        mEmail = (EditText)findViewById(R.id.loginEmail);
        mPassword = (EditText)findViewById(R.id.passwordText);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 ******Password and Email Checking *********
//                if (mEmail.getText().toString().equals("admin") &&
//                        mPassword.getText().toString().equals("admin")) {
//                    startCasesHome();
//                }else {
//                    Toast.makeText(LSLoginActivity.this, "Login Incorrect", Toast.LENGTH_SHORT).show();
//                }

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
