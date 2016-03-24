package edu.nsu.ir.Cases;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by admin on 3/3/2016.
 */
public class ProfileDAO implements Parcelable
{

    private static final long serialVersion = 1L;
    private String fname= "dummy";
    private String lname = "dummy";
    private String age= "dummy";
    private  String local= "dummy";
    private String email= "dummy";
    private String experience= "dummy";
    private String item_subject= "dummy";
    private String item_content= "dummy";
    private String image_path= "dummy";
    private String uname= "dummy";
    private int threadID;
    private ProfileDAO userprofile;


    public ProfileDAO(String item_subject, String item_content, String image_path) {
        this.item_subject = item_subject;
        this.item_content = item_content;
        this.image_path = image_path;
    }
    public ProfileDAO(String email){
        this.email = email;
    }
    public ProfileDAO(ProfileDAO userprofile){
        this.userprofile=  userprofile;
    }
    public ProfileDAO(String item_subject, String uname, String image_path,int threadID ) {
        this.item_subject = item_subject;
        this.item_content = uname;
        this.image_path = image_path;
        this.threadID = threadID;
    }

    public ProfileDAO(String fname, String lname, String age, String local, String email, String experience) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.local = local;
        this.email = email;
        this.experience = experience;
    }

    protected ProfileDAO(Parcel in) {
        fname = in.readString();
        lname = in.readString();
        age = in.readString();
        local = in.readString();
        email = in.readString();
        experience = in.readString();
        item_subject = in.readString();
        item_content = in.readString();
        image_path = in.readString();
        uname = in.readString();
        threadID = in.readInt();
        userprofile = in.readParcelable(ProfileDAO.class.getClassLoader());
    }

    public static final Creator<ProfileDAO> CREATOR = new Creator<ProfileDAO>() {
        @Override
        public ProfileDAO createFromParcel(Parcel in) {
            return new ProfileDAO(in);
        }

        @Override
        public ProfileDAO[] newArray(int size) {
            return new ProfileDAO[size];
        }
    };

    public void addThreadInfo(String uname, int threadID){
        this.uname=uname;
        this.threadID = threadID;
    }

    public String getItem_subject() {
        return item_subject;
    }
    public String getItem_uname() {
        return uname;
    }

    public void setItem_subject(String item_subject) {
        this.item_subject = item_subject;
    }

    public String getItem_content() {
        return item_content;
    }

    public void setItem_content(String item_content) {
        this.item_content = item_content;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int threadID() {
        return threadID;
    }

    public void threadID(int threadID) {
        this.threadID = threadID;
    }


    @Override
    public String toString() {
        return "ProfileDAO{" +
                "fname='" + fname + '\'' +
                "fname='" + lname + '\'' +
                ", age='" + age + '\'' +
                ", local='" + local + '\'' +
                ", email='" + email + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }

    public ProfileDAO getUserprofile() {
        return userprofile;
    }

    public void setUserprofile(ProfileDAO userprofile) {
        this.userprofile = userprofile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fname);
        dest.writeString(age);
        dest.writeString(local);
        dest.writeString(email);
        dest.writeString(experience);
        dest.writeString(item_subject);
        dest.writeString(item_content);
        dest.writeString(image_path);
        dest.writeString(uname);
        dest.writeInt(threadID);
        dest.writeParcelable(userprofile, flags);
    }
}
