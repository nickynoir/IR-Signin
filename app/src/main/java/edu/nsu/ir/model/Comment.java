package edu.nsu.ir.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thomaskofiannan1 on 3/15/16.
 */
public class Comment extends Base implements Parcelable{


    private String title;
    private String details;
    private String commentID;

    public Comment() {
    }
    public Comment(String author, String dateCreated, String postID) {

        this.author = author;
        this.dateCreated = dateCreated;
        this.postID = postID;
    }

    public Comment(String details,String author, String dateCreated, int dislikes, int likes, String postID,String commentID) {
        this.commentID=commentID;
        this.details=details;
        this.author = author;
        this.dateCreated = dateCreated;
        this.dislikes = dislikes;
        this.likes = likes;
        this.postID = postID;
    }

    protected Comment(Parcel in) {

        title = in.readString();
        details = in.readString();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Comment{" +

                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeString(details);
    }
}

