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
import edu.nsu.ir.model.MessageDAO;


//import android.support.v7.widget.RecyclerView;


/**
 * Created by admin on 3/23/2016.
 */

public class MThreadAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MessageDAO> allmessages;
    private LayoutInflater inflater;
    private ViewHolder holder;
    private MessageDAO message;

    public MThreadAdapter(Context context, ArrayList<MessageDAO> allmessages) {
        System.out.println("thread Ad 1");
        this.context = context;
        this.allmessages = allmessages;
    }
    @Override
    public int getCount() {
        return allmessages.size();
    }

    @Override
    public Object getItem(int position) {
        return allmessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("thread Ad 2");
        //check to see if object has been initialized
     //   if(message==null){

            //instantiate profile from profile arraylist
            message=allmessages.get(position);
       // }
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
            holder.item_uname= (TextView) convertView.findViewById(R.id.item_subject);
            holder.item_content=(TextView)convertView.findViewById(R.id.item_content);
            holder.item_img=(ImageView)convertView.findViewById(R.id.item_img);

        //    holder.item_uname.setTag("h1");
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();

        }


        Uri uri=Uri.parse(message.getImg_path());
        holder.item_img.setImageURI(uri);
        holder.item_content.setText(message.getContent());
        holder.item_uname.setText(message.getUname());

        return convertView;
    }

    static class ViewHolder{

        TextView item_uname,item_content;
        ImageView item_img;
    }
}


