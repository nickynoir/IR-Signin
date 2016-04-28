package edu.nsu.ir.Cases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.nsu.ir.R;

public class CCaseHomeActivity extends AppCompatActivity {

    private ListView mCaseListView;
    private Button mNewCaseButton;
    private CListCaseAdapter mCaseAdapter;
    private ArrayList<CCase>allCases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nsu.ir.R.layout.c_activity_case_home);

        init();

        //binding adapter to listview
        mCaseListView.setAdapter(mCaseAdapter);
        mNewCaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CCaseHomeActivity.this, "Starting a new case", Toast.LENGTH_SHORT).show();
                startNewCase();
            }
        });

        mCaseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            CCase value=(CCase)parent.getItemAtPosition(position);
               Toast.makeText(getApplicationContext(),value.getName(),Toast.LENGTH_LONG).show();
                startCaseDetails(value);
            }
        });

    }
    private void init(){
        allCases=new ArrayList<>();
        Log.d("INSIDE init", dummyCases().toString());
        mCaseAdapter=new CListCaseAdapter(this,dummyCases());
        mCaseListView=(ListView)findViewById(R.id.c_lvCases);
        mNewCaseButton = (Button)findViewById(edu.nsu.ir.R.id.newCaseButton);

    }
    private void startNewCase(){
        Intent intent = new Intent(this, CNewCaseActivity.class);
        startActivity(intent);
    }

    private void startCaseDetails(CCase cCase){
        Intent intent = new Intent(this, CCaseDetailsActivity.class);
        intent.putExtra("case",cCase);

        startActivity(intent);
    }
    private ArrayList<CCase> dummyCases(){
        allCases.add(new CCase("Case 1",true));
        allCases.add(new CCase("Case 2",false));
        allCases.add(new CCase("Case 3",true));
        allCases.add(new CCase("Case 4",false));
        return allCases;
    }
}
