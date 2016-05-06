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

public class StepUMView extends AppCompatActivity {
    String[] umSteps = {
            "Look at event log files in directories",
            "List recent security events",
            "Examine network configuration",
            "List network connections and related details",
            "List users and groups",
            "Look at scheduled jobs",
            "Check DNS setting and the hosts file",
            "Verify integrity of installed packages",
            "Look at auto-start services",
            "List processes",
            "Find recently modified files"
    };

    String[] moreInfoUM = {
            "(locations vary): /var/log/\n /var/adm/\n /var/spool/",
        "wtmp\nwho\nlast\n lastlog",
        "arp -an\n route print",
        "netstat -nap (Linux)\n netstat -na (Solaris)\n lsof -i",
        "more /etc/passwd",
            "more /etc/crontab  ls /etc/cron.*\n ls /var/at/jobs",
            "more /etc/resolv.conf more /etc/hosts",
            "rpm -Va (Linux)\n pkgchk (Solaris)",
            "chkconfig –list (Linux)\n ls /etc/rc*.d (Solaris)\n smf (Solaris 10+)",
            "ps aux (Linux, BSD)\n ps -ef (Solaris)\n lsof +L1",
            "ls -lat /\nfind / -mtime -2d -ls"
    };

    ListView lvUMExam;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_umview);
        lvUMExam = (ListView)findViewById(R.id.lvUMExam);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, umSteps);
        lvUMExam.setAdapter(adapter);

        lvUMExam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemNumber = position; //Get position of item clicked
                String theInfo = moreInfoUM[position]; //Set position of info to display from second array

                //Create dialog to display on tap
                AlertDialog.Builder moreInfo = new AlertDialog.Builder(StepUMView.this);
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
