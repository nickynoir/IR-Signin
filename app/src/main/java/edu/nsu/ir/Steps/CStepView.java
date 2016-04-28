package edu.nsu.ir.Steps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import edu.nsu.ir.R;

public class CStepView extends AppCompatActivity {
    String[] beforeStart = {
            "Have all important contact information (law enforcement, organizational contacts)",
            "Legal tools (e.g. evidence bags, chain of evidence forms, etc)",
            "Encryption software",
            "Secure storage facility"
    };

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

    String[] resourceList = {
            "Port lists",
            "Documentation",
            "Network diagrams and lists of critical assets",
            "Current baselines",
            "Cryptographic hashes"
    };

    ListView lvStart;
    ListView lvTools;
    ListView lvResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nsu.ir.R.layout.c_activity_step_view);

        lvStart = (ListView) findViewById(R.id.lvStart);
        lvTools = (ListView) findViewById(R.id.lvTools);
        lvResources = (ListView) findViewById(R.id.lvResources);

        ArrayAdapter<String> adapterStart = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, beforeStart);
        lvStart.setAdapter(adapterStart);
        lvStart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                View ctvStart = lvStart.getChildAt(position);
            }
        });

        ArrayAdapter<String> adapterTools = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, toolList);
        lvTools.setAdapter(adapterTools);
        lvTools.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View ctvTools = lvTools.getChildAt(position);
                Toast.makeText(CStepView.this, "Tools position " + position, Toast.LENGTH_SHORT).show();

            }
        });

        ArrayAdapter<String> adapterResources = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, resourceList);
        lvResources.setAdapter(adapterResources);
        lvResources.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CStepView.this, "Resources position " + position, Toast.LENGTH_SHORT).show();
            }
        });

        ListUtils.setDynamicHeight(lvStart);
        ListUtils.setDynamicHeight(lvTools);
        ListUtils.setDynamicHeight(lvResources);


    }

    /**
     * Set height and with of listview
     */
    public static class ListUtils {
        public static void  setDynamicHeight(ListView mListView){
            ListAdapter mListAdapter = mListView.getAdapter();
            if(mListAdapter == null){
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();

        }
    }
}
