package edu.nsu.ir.Cases;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.nsu.ir.R;

public class CListCaseAdapter extends BaseAdapter
    {

        private Context mContext;
        private ArrayList<CCase> allCases;
        private   LayoutInflater mInflater;
        private CCase mCase;

        public CListCaseAdapter(Context context, ArrayList<CCase> allCases) {
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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            mCase=allCases.get(position);

            if (mInflater==null){
                mInflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            }if(convertView==null){
                convertView=mInflater.inflate(R.layout.c_each_case,null);
                Log.d("INSIDE CONVERTVIEW","INSIDE CONVERTVIEW");
                TextView tvName= (TextView) convertView.findViewById(R.id.c_tvCaseName);
                TextView tvStatus= (TextView) convertView.findViewById(R.id.c_tvCaseStatus);

                tvName.setText(mCase.getName());

                String status="Closed";
                if(mCase.isStatus()){status="Active";}
                tvStatus.setText(status);
            }


            return convertView;
        }
    }
