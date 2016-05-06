package edu.nsu.ir.activities.Steps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.nsu.ir.activities.cases.CCaseDetailsActivity;
import edu.nsu.ir.R;
import edu.nsu.ir.model.CaseDAO;
import edu.nsu.ir.model.ProfileDAO;

public class StepsHome extends AppCompatActivity {

    private Button btnBackCD, btnStart, btnTools, btnResources, btnWIE, btnUMIE;
    private ProfileDAO userProfile;
    private CaseDAO Case;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_home);

        init();


        btnBackCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToDetails();

            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBeforeYouStart();
            }
        });
        btnTools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToolsView();
            }
        });
        btnResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startResourcesView();
            }
        });
        btnWIE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWindowsView();
            }
        });
        btnUMIE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startUMView();
            }
        });

        Bundle data = getIntent().getExtras();
        //Gets User profile and the message thread chosen
        userProfile = (ProfileDAO) data.getParcelable("userProfile");
        Case = (CaseDAO) data.getParcelable("case");
    }

    private void startUMView() {
        Intent intent = new Intent(this, StepUMView.class);
        startActivity(intent);
    }

    private void startWindowsView() {
        Intent intent = new Intent(this, StepWindowsView.class);
        startActivity(intent);
    }

    private void startResourcesView() {
        Intent intent = new Intent(this, StepResourcesView.class);
        startActivity(intent);
    }

    private void startToolsView() {
        Intent intent = new Intent(this, StepToolsView.class);
        startActivity(intent);
    }

    private void startBeforeYouStart() {
        Intent intent = new Intent(this, StepStart.class);
        startActivity(intent);
    }

    private void backToDetails() {
        Intent intent = new Intent(this, CCaseDetailsActivity.class);
        startActivity(intent);
    }

    private void init() {
        btnBackCD = (Button) findViewById(R.id.btnBackTCD);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnTools = (Button)findViewById(R.id.btnTools);
        btnResources = (Button)findViewById(R.id.btnResources);
        btnWIE = (Button)findViewById(R.id.btnWIE);
        btnUMIE = (Button)findViewById(R.id.btnUMIE);

    }
}
