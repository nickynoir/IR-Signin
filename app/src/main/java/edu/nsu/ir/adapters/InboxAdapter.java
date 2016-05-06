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
import edu.nsu.ir.model.MsgThreadDAO;


/**
 * Created by admin on 3/17/2016.
 */
public class InboxAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MsgThreadDAO>allMessages;
    private LayoutInflater inflater;
    private ViewHolder holder;
    private MsgThreadDAO msgThread;

    public InboxAdapter(Context context, ArrayList<MsgThreadDAO> allMessages) {
        System.out.println("InboxAdapter");
        this.context = context;
        this.allMessages = allMessages;
        System.out.println("InboxAdapter done");
    }

    @Override
    public int getCount() {
        return allMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return allMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("getView");
        //check to see if object has been initialized
       // if(msgThread==null){
            System.out.println("msgThread==null");

            msgThread=allMessages.get(position);
       // }
        //check to see if object has been initialized
        if(inflater==null){
            System.out.println("inflater==null");
            //load it from a system service
            inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        }

        //check to see if object has been initialized
        if(holder==null){
            System.out.println("holder==null");
            //instantiate the holder reference
            holder=new ViewHolder();
        }
        if(convertView==null){
            System.out.println("convertView==null");
            //inflate instance of the view
            convertView=inflater.inflate(R.layout.list_item,null);
            holder.item_subject= (TextView) convertView.findViewById(R.id.item_content);
            holder.item_Uname=(TextView)convertView.findViewById(R.id.item_subject);
            holder.item_img=(ImageView)convertView.findViewById(R.id.item_img);
            holder.item_subject.setTag("h1");
            convertView.setTag(holder);
            System.out.println("");
        }else{
            System.out.println("else");
            holder=(ViewHolder)convertView.getTag();

        }

        System.out.println("all good");

        Uri uri=Uri.parse("");
        holder.item_img.setImageURI(uri);
        holder.item_Uname.setText(msgThread.getFromUname());
        holder.item_subject.setText(msgThread.getSubject());
        System.out.println("return");
        return convertView;
    }

    static class ViewHolder{
        TextView item_subject,item_Uname;
        ImageView item_img;
    }
}
