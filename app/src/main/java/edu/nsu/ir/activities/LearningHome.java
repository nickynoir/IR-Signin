package edu.nsu.ir.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import edu.nsu.ir.R;

public class LearningHome extends AppCompatActivity {
    private ListView lvLearning;
    private Button btnToHome;
    String[] learningList = {
            "Data Acquisition Methods",
            "Preparing for a Search",
            "Undrestanding Computer Memory",
            "UNIX",
            "Conducting E-mail Investigations",
            "Introduction to Mobile Forensics"
    };

    String[] sites = {
            "http://nkykyq.nykyrian-q.com/Data%20Acquisition%20Methods.html",
            "http://nkykyq.nykyrian-q.com/Preparing%20for%20a%20Search.html",
            "http://nkykyq.nykyrian-q.com/Understanding%20Computer%20Memory.html",
            "http://nkykyq.nykyrian-q.com/UNIX.html",
            "http://nkykyq.nykyrian-q.com/emailInvestigation.html",
            "http://nkykyq.nykyrian-q.com/mobileForensics.html"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learnig_home);
        init();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,learningList);
        lvLearning.setAdapter(adapter);

        lvLearning.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemNumber = position; //Get position of item clicked
                Uri uri = Uri.parse(sites[position]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        backToHome();
    }

    private void backToHome() {
//        Intent intent = new Intent(this, Home.class);
//        startActivity(intent);
    }

    private void init() {
        lvLearning = (ListView) findViewById(R.id.lvLearning);
        btnToHome = (Button) findViewById(R.id.btnToHome);
    }
}
