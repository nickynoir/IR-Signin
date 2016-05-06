package edu.nsu.ir.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import edu.nsu.ir.R;
import edu.nsu.ir.activities.cases.CCaseHomeActivity;
import edu.nsu.ir.model.ProfileDAO;
import edu.nsu.ir.activities.profile.PEditProfileActivity;
import edu.nsu.ir.activities.profile.PViewInboxActivity;
import edu.nsu.ir.activities.profile.PViewProfileActivity;


//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    private ImageButton b1,b2,b3,b4,b5;
    private Button b6;
    private ProfileDAO userProfile;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        init();
        Bundle data = getIntent().getExtras();

        //Gets User profile and the message thread chosen
        userProfile = (ProfileDAO) data.getParcelable("userProfile");
         b1 = (ImageButton) findViewById(R.id.profileBtn);
      //  b2 = (ImageButton) findViewById(R.id.b2);
       b3 = (ImageButton) findViewById(R.id.imageButton4);
        b4 = (ImageButton) findViewById(R.id.imageButton5);
        b5 = (ImageButton) findViewById(R.id.action_Inbox);
        b6 = (Button) findViewById(R.id.button);
       b1.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Home.this, PViewProfileActivity.class);
                intent.putExtra("FindME", "true"/*get thread id*/);
                intent.putExtra("userProfile", new ProfileDAO(userProfile.getItem_uname(),userProfile.getFname(),
                        userProfile.getAge(),userProfile.getCity(),
                        userProfile.getEmail(),userProfile.getExperience(),userProfile.getCompany(),userProfile.getLname(),userProfile.getState()));

                startActivity(intent);
            }
        });

 /*       b2.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent2 = new Intent(MainActivity.this, .class);
                startActivityForResult(intent2, 0);
            }
        });*/

        b3.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Home.this, CCaseHomeActivity.class);
                intent.putExtra("userProfile", new ProfileDAO(userProfile.getItem_uname(),userProfile.getFname(),
                        userProfile.getAge(),userProfile.getCity(),
                        userProfile.getEmail(),userProfile.getExperience(),userProfile.getCompany(),userProfile.getLname(),userProfile.getState()));
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Home.this, SendMsg.class);
                intent.putExtra("prev", "false"/*get thread id*/);
                intent.putExtra("userProfile", new ProfileDAO(userProfile.getItem_uname(),userProfile.getFname(),
                        userProfile.getAge(),userProfile.getCity(),
                        userProfile.getEmail(),userProfile.getExperience(),userProfile.getCompany(),userProfile.getLname(),userProfile.getState()));
                startActivity(intent);
            }
        });

        b5.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Home.this, PViewInboxActivity.class);
                intent.putExtra("userProfile", new ProfileDAO(userProfile.getItem_uname(),userProfile.getFname(),
                        userProfile.getAge(),userProfile.getCity(),
                        userProfile.getEmail(),userProfile.getExperience(),userProfile.getCompany(),userProfile.getLname(),userProfile.getState()));
                startActivity(intent);
            }
        });
        b6.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Home.this, PEditProfileActivity.class);
                intent.putExtra("prev", "true"/*get thread id*/);
                //Intent intent = new Intent(MainActivity.this, PEditProfileActivity.class);
                intent.putExtra("userProfile", new ProfileDAO(userProfile.getItem_uname(),userProfile.getFname(),
                        userProfile.getAge(),userProfile.getCity(),
                        userProfile.getEmail(),userProfile.getExperience(),userProfile.getCompany(),userProfile.getLname(),userProfile.getState()));
                startActivity(intent);
                //artActivityForResult(intent, 0);

            }
        });

    }



private void init() {

    }

//   @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the saction bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
