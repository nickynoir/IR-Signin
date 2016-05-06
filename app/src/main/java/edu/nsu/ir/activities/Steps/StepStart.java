package edu.nsu.ir.activities.Steps;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import edu.nsu.ir.R;

public class StepStart extends AppCompatActivity {
    String[] beforeStart = {
            "Have all important contact information (law enforcement, organizational contacts)",
            "Legal tools (e.g. evidence bags, chain of evidence forms, etc)",
            "Encryption software",
            "Secure storage facility"
    };

    String[] moreInfo = {
            "for team members and others within and outside the organization (primary and backup contacts), such as law enforcement and other incident response teams; ",
            "including hard-bound notebooks, digital cameras, audio recorders,chain of custody forms, evidence storage bags and tags, and evidence tape, to preserve evidence for possible legal actions",
            "test1",
            " used for communications among team members, within the organization and with external parties; for Federal agencies, software must use a FIPS-validated encryption algorithm",
    };

    ListView lvBeforeStart;
    Button btnNext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nsu.ir.R.layout.c_activity_step_view);

        lvBeforeStart = (ListView) findViewById(R.id.lvUMExam);
        ArrayAdapter<String> adapater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, beforeStart);
        lvBeforeStart.setAdapter(adapater);

        lvBeforeStart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemNumber = position; //Get position of item clicked
                String theInfo = moreInfo[position]; //Set position of info to display from second array

                //Create dialog to display on tap
                AlertDialog.Builder moreInfo = new AlertDialog.Builder(StepStart.this);
                moreInfo.setMessage(theInfo)
                        .setCancelable(false)
                        .setNegativeButton("Finished", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = moreInfo.create();
                alert.setTitle("More Information");
                alert.show();
            }
        });


        btnNext = (Button) findViewById(R.id.btnBack);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToolsSteps();
            }
        });

    }

    private void startToolsSteps() {
        Intent intent = new Intent(this, StepToolsView.class);
        startActivity(intent);
    }
}
