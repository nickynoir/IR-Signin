package edu.nsu.ir.model;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thomaskofiannan1 on 3/11/16.
 */
public class Post extends Base implements Parcelable{

    private String title;
    private String details;
    private String imageURL;

    public Post() {
    }

    public Post(String details, String imageURL, String title,String author, String dateCreated, int dislikes, int likes, String postID) {
        this.details = details;
        this.imageURL = imageURL;
        this.title = title;
        this.author = author;
        this.dateCreated = dateCreated;
        this.dislikes = dislikes;
        this.likes = likes;
        this.postID = postID;
    }

    public Post(String details, String imageURL, String title,String author, String dateCreated,  String postID) {
        this.details = details;
        this.imageURL = imageURL;
        this.title = title;
        this.author = author;
        this.dateCreated = dateCreated;
        this.postID = postID;
    } public Post(String details,  String title,String author, String dateCreated,  String postID) {
        this.details = details;
        this.imageURL = imageURL;
        this.title = title;
        this.author = author;
        this.dateCreated = dateCreated;
        this.postID = postID;
    }
    public Post(String details,  String title,String author) {
        this.details = details;
        this.title = title;
        this.author = author;
    }

    protected Post(Parcel in) {
        title = in.readString();
        details = in.readString();
        imageURL = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Post{" +
                "details='" + details + '\'' +
                ", title='" + title + '\'' +
                ", imageURL='" + imageURL + '\'' +
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
        dest.writeString(imageURL);
    }
}

