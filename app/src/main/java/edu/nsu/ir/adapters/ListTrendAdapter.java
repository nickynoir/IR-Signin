package edu.nsu.ir.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import edu.nsu.ir.R;
import edu.nsu.ir.api.AppController;
import edu.nsu.ir.model.Post;


/**
 * Created by thomaskofiannan1 on 3/12/16.
 */
public class ListTrendAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Post> allPost;
    private Post post;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public ListTrendAdapter(Activity activity, List<Post> allPost) {
        this.activity=activity;
        this.allPost=allPost;
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
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_individual_trend, null);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.imgListPost);
        TextView title = (TextView) convertView.findViewById(R.id.tvTitleListGroup);
        TextView details = (TextView) convertView.findViewById(R.id.tvDetailsListGroup);
        TextView datePosted = (TextView) convertView.findViewById(R.id.tvDateListGroup);
        TextView author = (TextView) convertView.findViewById(R.id.tvAuthorPost);


        post=allPost.get(position);

        // thumbnail image
        //http://10.0.2.2:8888/android-api/testimony/uploads/gh.jpg
        //thumbNail.setImageUrl(post.getImageURL(), imageLoader);
        thumbNail.setImageUrl("http://10.0.2.2:8888/android-api/testimony/uploads/gh.jpg", imageLoader);

        // title
        title.setText(post.getTitle());
        //details
        details.setText(post.getDetails());
        //dateposeted
        datePosted.setText(post.getDateCreated());
        //author
        author.setText(post.getAuthor());

        return convertView;
    }
}
