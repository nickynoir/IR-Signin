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

import edu.nsu.ir.activities.cases.CCaseDetailsActivity;
import edu.nsu.ir.R;

public class StepResourcesView extends AppCompatActivity {

    String[] resourceList = {
            "Port lists",
            "Documentation",
            "Network diagrams and lists of critical assets",
            "Current baselines",
            "Cryptographic hashes"
    };

    String[] list = {
            " including commonly used ports and Trojan horse ports",
            "for OSs, applications, protocols, and intrusion detection and antivirus products",
            "such as database servers",
            "of expected network, system, and application activity",
            "of critical files22 to speed incident analysis, verification, and eradication"
    };




    ListView lvResources;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_resources_view);

        lvResources = (ListView) findViewById(R.id.lvUMExam);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, resourceList);
        lvResources.setAdapter(adapter);

        lvResources.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemNumber = position; //Get position of item clicked
                String itemNum = Integer.toString(itemNumber);
                String theInfo = list[position]; //Set position of info to display from second array

                AlertDialog.Builder moreInfo = new AlertDialog.Builder(StepResourcesView.this);
                moreInfo.setMessage(theInfo)
                        .setCancelable(false)
                        .setNegativeButton("Finished", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = moreInfo.create();
                alert.setTitle("More Info");
                alert.show();
            }
        });

        btnNext = (Button) findViewById(R.id.btnBack);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToDetails();


            }
        });
    }

    private void backToDetails() {
        Intent intent = new Intent(this, CCaseDetailsActivity.class);
        startActivity(intent);
    }
}
