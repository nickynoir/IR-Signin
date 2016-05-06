package edu.nsu.ir.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.nsu.ir.R;
import edu.nsu.ir.model.Post;


/**
 * Created by admin on 4/21/2016.
 */
public class PostAdapter extends BaseAdapter{


    private Context context;
    private ArrayList<Post> allPost;
    private LayoutInflater inflater;
    private ViewHolder holder;
    private Post post;

    public PostAdapter(Context context, ArrayList<Post> allPost) {
        Log.d("CGETVIEW",allPost+"");
        this.context = context;
        this.allPost = allPost;
    }

    @Override
    public int getCount() {
        return allPost.size();
    }

    @Override
    public Object getItem(int position) {
        return allPost.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("GETVIEW",allPost+"");
        //check to see if object has been initialized
        //    if(profile==null){

        //instantiate profile from profile arraylist
        post=allPost.get(position);
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
            holder.mMessage= (TextView) convertView.findViewById(R.id.item_content);
            holder.mTitle=(TextView)convertView.findViewById(R.id.item_subject);
            holder.item_img=(ImageView)convertView.findViewById(R.id.item_img);

            holder.mTitle.setTag("h1");
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();

        }



        Uri uri=Uri.parse("");
        holder.item_img.setImageURI(uri);
      //  holder.mMessage.setText(post.getMessage());
        holder.mTitle.setText(post.getTitle());

        return convertView;
    }

    static class ViewHolder{

        TextView mTitle,mMessage;
        ImageView item_img;
    }

}
