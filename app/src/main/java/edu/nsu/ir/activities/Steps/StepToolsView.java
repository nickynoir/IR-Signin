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
import android.widget.CheckedTextView;
import android.widget.ListView;

import edu.nsu.ir.R;


public class StepToolsView extends AppCompatActivity {

    String[] toolList = {
            "Forensic workastation and/or backup devices",
            "Laptops",
            "Spare workstations, servers, and networking equipment, or the virtualized equivalents",
            "Blank removable media",
            "Portable printer",
            "Packet sniffers and protocol analyzers",
            "Digital forensic software",
            "Removable media",
            "Evidence gathering accessories",
            "Clean OS images"
    };

    String[] list = {
            " to create disk images, preserve log files, and save other relevant incident data",
            " for activities such as analyzing data, sniffing packets, and writing reports",
            "Smay be used for many purposes, such as restoring backups and trying out malware",
            "USB, CD",
            " print copies of log files and other evidence from non-networked systems",
            "to capture and analyze network traffic",
            "to analyze disk images",
            "with trusted versions of programs to be used to gather evidence from systems",
            "including hard-bound notebooks, digital cameras, audio recorders,chain of custody forms, evidence storage bags and tags, and evidence tape, to preserve evidence for possible legal actions",
            "Clean OS images"
    };


    ListView lvTools;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_tools_view);

        lvTools = (ListView) findViewById(R.id.lvUMExam);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, toolList);
        lvTools.setAdapter(adapter);

        lvTools.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemNumber = position; //Get position of item clicked
                String itemNum = Integer.toString(itemNumber);
                String theInfo = list[position]; //Set position of info to display from second array
                CheckedTextView checkedTextView = ((CheckedTextView)view);
                checkedTextView.setChecked(!checkedTextView.isChecked());
                String value = Boolean.toString(checkedTextView.isChecked());

                //                Create dialog to display on tap
                AlertDialog.Builder moreInfo = new AlertDialog.Builder(StepToolsView.this);
                moreInfo.setMessage(theInfo)
                        .setCancelable(false)
                        .setNegativeButton("Finished", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = moreInfo.create();
                alert.setTitle("Run from Command Line");
                alert.show();
            }
        });

        btnNext = (Button) findViewById(R.id.btnBack);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startResources();

            }
        });

    }

    private void startResources() {
        Intent intent = new Intent(this, StepResourcesView.class);
        startActivity(intent);
    }
}
