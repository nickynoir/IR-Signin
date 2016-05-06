package edu.nsu.ir.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 3/3/2016.
 */
public class ProfileDAO implements Parcelable
{

    private static final long serialVersion = 1L;
    private String fname, lname;
    private String age;
    private String state, company, contact;
    private String city;
    private String email;
    private String experience;
    private String image_path;
    private String uname;

    private String item_subject;//ignore
    private String item_content;//ignore
    private int threadID;//igmore
    private ProfileDAO userprofile;//Ignore



    public ProfileDAO(String item_subject, String item_content, String image_path) {
        this.item_subject = item_subject;
        this.item_content = item_content;
        this.image_path = image_path;
    }
    public ProfileDAO( ){

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

    public ProfileDAO(String fname, String age, String city, String email, String experience) {
        this.fname = fname;
        this.age = age;
        this.city = city;
        this.email = email;
        this.experience = experience;
    }
    public ProfileDAO(String uname,String fname, String age, String city, String email, String experience,String company) {
        this.fname = fname;
        this.uname = uname;
     this.company = company;
        this.age = age;
        this.city = city;
        this.email = email;
        this.experience = experience;
    }
    public ProfileDAO(String uname,String fname, String age, String city, String email, String experience,String company,String lname, String state) {
        this.fname = fname;
        this.company=company;
        this.state = state;
        this.lname=lname;
        this.uname = uname;
        this.company = company;
        this.age = age;
        this.city = city;
        this.email = email;
        this.experience = experience;
    }
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
    public void setItem_uname(String uname) {
        this.uname = uname;
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

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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



    public ProfileDAO getUserprofile() {
        return userprofile;
    }

    public void setUserprofile(ProfileDAO userprofile) {
        this.userprofile = userprofile;
    }

    @Override
    public String toString() {
        return "ProfileDAO{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", age='" + age + '\'' +
                ", state='" + state + '\'' +
                ", company='" + company + '\'' +
                ", contact='" + contact + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", experience='" + experience + '\'' +
                ", image_path='" + image_path + '\'' +
                ", uname='" + uname + '\'' +
                ", item_subject='" + item_subject + '\'' +
                ", item_content='" + item_content + '\'' +
                ", threadID=" + threadID +
                ", userprofile=" + userprofile +
                '}';
    }

    public ProfileDAO(Parcel in){
        String[] data = new String[12];

        in.readStringArray(data);
        this.uname = data[0];
        this.fname = data[1];
        this.email = data[2];
        this.age = data[3];
        this.city = data[4];
        this.experience = data[5];
        this.item_subject = data[6];
        this.item_content = data[7];
        this.image_path = data[8];
        this.state = data[9];
        this.company = data[10];
        this.lname = data[11];


    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.uname,
                this.fname,
                this.email,this.age,this.city,this.experience,this.item_subject,
                this.item_content,this.image_path,
                this.state,this.company,this.lname});
    }
    public static final Creator CREATOR = new Creator() {
        public ProfileDAO createFromParcel(Parcel in) {
            return new ProfileDAO(in);
        }

        public ProfileDAO[] newArray(int size) {
            return new ProfileDAO[size];
        }
    };

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}

