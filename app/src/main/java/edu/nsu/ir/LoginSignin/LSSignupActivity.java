package edu.nsu.ir.LoginSignin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LSSignupActivity extends AppCompatActivity {
    private Button mNewAccount;
    private Button mLogin;
    private EditText mFistName, mLastName,mUserName, mEmail, mPassword, mCity, mState, mCompany, mExperience, mContact;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nsu.ir.R.layout.ls_activity_signup);

        mNewAccount = (Button)findViewById(edu.nsu.ir.R.id.freshAccountButton);
        mLogin = (Button)findViewById(edu.nsu.ir.R.id.backLogin);

        mNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LSSignupActivity.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LSSignupActivity.this, LSLoginActivity.class);
                startActivity(intent);
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LSSignupActivity.this, LSLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
