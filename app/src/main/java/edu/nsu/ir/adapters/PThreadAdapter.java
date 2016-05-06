package edu.nsu.ir.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.nsu.ir.R;
import edu.nsu.ir.model.ProfileDAO;


/**
 * Created by admin on 3/17/2016.
 */
public class PThreadAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ProfileDAO>allProfiles;
    private LayoutInflater inflater;
    private ViewHolder holder;
    private ProfileDAO profile;

    public PThreadAdapter(Context context, ArrayList<ProfileDAO> allProfiles) {

        this.context = context;
        this.allProfiles = allProfiles;
    }

    @Override
    public int getCount() {
        return allProfiles.size();
    }

    @Override
    public Object getItem(int position) {
        return allProfiles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //check to see if object has been initialized
    //    if(profile==null){

            //instantiate profile from profile arraylist
            profile=allProfiles.get(position);
     //   }
        //check to see if object has been initialized
        if(inflater==null){
            //load it from a system service
            inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        }

        //check to see if object has been initialized
        if(holder==null){
            //instantiate the holder reference
            holder=new ViewHolder();
        }
        if(convertView==null){
            //inflate instance of the view
            convertView=inflater.inflate(R.layout.list_item,null);
            holder.item_content= (TextView) convertView.findViewById(R.id.item_content);
            holder.item_subject=(TextView)convertView.findViewById(R.id.item_subject);
            holder.item_img=(ImageView)convertView.findViewById(R.id.item_img);

            holder.item_subject.setTag("h1");
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();

        }



        Uri uri=Uri.parse(profile.getImage_path());
        holder.item_img.setImageURI(uri);
        holder.item_content.setText(profile.getItem_content());
        holder.item_subject.setText(profile.getItem_subject());

        return convertView;
    }

    static class ViewHolder{

        TextView item_subject,item_content;
        ImageView item_img;
    }
}
