package edu.nsu.ir.activities.forum;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import edu.nsu.ir.R;
import edu.nsu.ir.fragments.AddCommentDialogFragment;
import edu.nsu.ir.model.Comment;
import edu.nsu.ir.model.Post;
import edu.nsu.ir.util.AppHelpers;
import edu.nsu.ir.util.AppURLS;
import edu.nsu.ir.util.ErrorMessages;


public class DetailedPostActivity extends AppCompatActivity {
    private ShareActionProvider mShareActionProvider;
    private TextView tvDetailPost_Title,tvDetailPost_Detail,tvDetailPost_Comments,tvDetailPost_VoteUp,tvDetailPost_VoteDown;
    private ImageView ivVoteUp,ivVoteDown;
    private Post post;
    private Bundle commentBundle;
    private Comment comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        post=getIntent().getParcelableExtra(AppURLS.KEY_POST);
        if(post!=null){
            Log.e("POST-IN-DETAILS",post.toString());
            //popolate fields
            tvDetailPost_VoteDown.setText("7");
            tvDetailPost_VoteUp.setText("10");
            tvDetailPost_Detail.setText(post.getDetails());
            tvDetailPost_Comments.setText("No comments yet be the first to comment");
            tvDetailPost_Title.setText(post.getTitle());
        }else{
            Toast.makeText(getApplicationContext(), ErrorMessages.ERROR_POST_RETRIEVE,Toast.LENGTH_LONG).show();
        }

        ivVoteDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            tvDetailPost_VoteDown.setText(decreaseVotes());
            }
        });

        ivVoteUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            tvDetailPost_VoteUp.setText(increaseVotes());
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCommentDialogFragment myFragment = new AddCommentDialogFragment();
                comment.setDateCreated(AppHelpers.getCurrentDate());
                comment.setAuthor(AppHelpers.getCurrentUserName());
                commentBundle.putParcelable(AppURLS.KEY_COMMENT,comment);
                myFragment.setArguments(commentBundle);
                myFragment.show(getFragmentManager(), "theDialog");
            }
        });
    }


    private String increaseVotes(){
        String currentVotes=tvDetailPost_VoteUp.getText().toString();
        Integer votes=Integer.parseInt(currentVotes);
        return (votes+1)+"";
    }
    private String decreaseVotes(){
        String currentVotes=tvDetailPost_VoteDown.getText().toString();
        Integer votes=Integer.parseInt(currentVotes);
        return (votes-1)+"";
    }
    private void init(){
        comment=new Comment();
        commentBundle=new Bundle();
        Typeface titleFont = Typeface.createFromAsset(getAssets(),"fonts/Oswald-BoldItalic.ttf");
        Typeface detailsFont = Typeface.createFromAsset(getAssets(),"fonts/Oswald-DemiBold.ttf");
        Typeface commentFont = Typeface.createFromAsset(getAssets(),"fonts/Oswald-Light.ttf");
        Typeface authorFont = Typeface.createFromAsset(getAssets(),"fonts/Oswald-DemiBold.ttf");
        Typeface votesFont = Typeface.createFromAsset(getAssets(),"fonts/Oswald-ExtraLight.ttf");
       // textfield.setTypeface(tf,Typeface.BOLD);ExtraLight
        post=new Post();
        tvDetailPost_Title=(TextView)findViewById(R.id.tvDetailPost_Title);
        tvDetailPost_Detail=(TextView)findViewById(R.id.tvDetailPost_Detail);
        tvDetailPost_Comments=(TextView)findViewById(R.id.tvDetailPost_comments);
        tvDetailPost_VoteDown=(TextView)findViewById(R.id.tvDetailPost_VoteDown);
        tvDetailPost_VoteUp=(TextView)findViewById(R.id.tvDetailPost_VoteUp);
        ivVoteUp=(ImageView)findViewById(R.id.ivDetailPost_VoteUp);
        ivVoteDown=(ImageView)findViewById(R.id.ivDetailPost_VoteDown);

        //setting fonts for each textview
        tvDetailPost_Title.setTypeface(titleFont);
        tvDetailPost_Detail.setTypeface(detailsFont);
        tvDetailPost_Comments.setTypeface(commentFont);
        tvDetailPost_VoteDown.setTypeface(votesFont);
        tvDetailPost_VoteUp.setTypeface(votesFont);

        tvDetailPost_Detail.setMovementMethod(new ScrollingMovementMethod());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.detail, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_share_post:
               setShareIntent();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    // Call to update the share intent
    private void setShareIntent() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, post.getDetails());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Inspire someone today"));
    }

}
