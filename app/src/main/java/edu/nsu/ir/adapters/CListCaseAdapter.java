package edu.nsu.ir.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.nsu.ir.R;
import edu.nsu.ir.model.CaseDAO;

public class CListCaseAdapter extends BaseAdapter
    {

        private Context mContext;
        private ArrayList<CaseDAO> allCases;
        private   LayoutInflater mInflater;
        private CaseDAO mCase;

        public CListCaseAdapter(Context context, ArrayList<CaseDAO> allCases) {
            mContext = context;
            this.allCases = allCases;
            Log.d("ALLCASES",allCases.toString());
        }

        @Override
        public int getCount() {
            return allCases.size();
        }

        @Override
        public Object getItem(int position) {

            return allCases.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * Sets the case name text and status
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            mCase=allCases.get(position);

            if (mInflater==null){
                mInflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            // Set Status
            if(convertView==null){
                convertView=mInflater.inflate(R.layout.c_each_case,null);
                Log.d("INSIDE CONVERTVIEW","INSIDE CONVERTVIEW");
                TextView tvName= (TextView) convertView.findViewById(R.id.c_tvCaseName);
                TextView tvStatus= (TextView) convertView.findViewById(R.id.c_tvCaseStatus);

                tvName.setText(mCase.getCase_name());

                String status="Closed";

                if(Boolean.parseBoolean(mCase.isCase_status())){status="Active";}
                tvStatus.setText(status);
            }
            return convertView;
        }
    }
