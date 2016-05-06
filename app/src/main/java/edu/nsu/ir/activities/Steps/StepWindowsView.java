package edu.nsu.ir.activities.Steps;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

import edu.nsu.ir.R;

public class StepWindowsView extends AppCompatActivity {

    private static final String PREF_NAME = "yellow";
    String[] widowsSteps = {
        "Look at event logs",
         "Examine network configuration",
        "List network connections and related details",
        "List users and groups",
        "Look at scheduled jobs",
        "Look at auto-start programs",
        "List processes",
        "List services",
        "Check DNS setting and the hosts file",
        "Verify integrity of OS files",
        "Research recently modified files"
    };

    String[] moreInfoWindows = {
            "event vwr",
            "arp -a\nnetstat -nr",
            "netstat -nao\nnetstat -vb\nnet session\nnet use",
            "lusrmgr\nnet users\nnet localgroup administrators\n net group administrators",
            "schtasks",
            "msconfig",
            "taskmgr \nwmic process list full",
            "net start \ntasklist /svc",
            "ipconfig",
            "/all \nmore %SystemRoot%System32Driversetchosts,ipconfig  /displaydns",
            "sigverif",
            "dir /a/o-d/p %SystemRoot%System32"
    };


    ListView lvWindowsExam;
    Button btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_windows_view);
        Context context = getApplicationContext();
//        SharedPreferences shardPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
//        final SharedPreferences.Editor editor = shardPref.edit();

        lvWindowsExam = (ListView)findViewById(R.id.lvUMExam);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, widowsSteps);
        lvWindowsExam.setAdapter(adapter);

        lvWindowsExam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemNumber = position; //Get position of item clicked
                String itemNum = Integer.toString(itemNumber);
                String theInfo = moreInfoWindows[position]; //Set position of info to display from second array
                CheckedTextView checkedTextView = ((CheckedTextView)view);
                checkedTextView.setChecked(!checkedTextView.isChecked());
                String value = Boolean.toString(checkedTextView.isChecked());

//                if(checkedTextView.isChecked()){
//                    SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
//                    settings.edit().putBoolean("", true).commit();
//                }

//                Create dialog to display on tap
                AlertDialog.Builder moreInfo = new AlertDialog.Builder(StepWindowsView.this);
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

        btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backHome();
            }
        });
    }

    private void backHome() {
        Intent intent = new Intent(this, StepsHome.class);
        startActivity(intent);
    }
}
