package edu.nsu.ir.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 3/23/2016.
 */
public class MessageDAO implements Parcelable {
    private String threadId = "123",time;
    private String item_uname, subject, item_content, img_path, password;

    public MessageDAO(String img_path, String threadId, String item_uname, String subject, String item_content) {

        this.img_path = img_path;//
        this.threadId = threadId;
        this.item_uname = item_uname;
        this.subject = subject;
        this.item_content = item_content;
    }

    public MessageDAO() {

    }

    public String getThreadId() {
        return threadId;
    }

    public String getUname() {
        return item_uname;
    }

    public void setUname(String item_uname) {
        this.item_uname = item_uname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return item_content;
    }

    public void setContent(String item_content) {
        this.item_content = item_content;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public MessageDAO(Parcel in) {
        String[] data = new String[5];

        in.readStringArray(data);
        this.threadId = data[0];
        this.item_uname = data[1];
        this.subject = data[2];
        this.item_content = data[3];
        this.img_path = data[4];


    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.threadId,
                this.item_uname,
                this.subject, this.item_content,this.img_path});
    }

    public static final Creator CREATOR = new Creator() {
        public MessageDAO createFromParcel(Parcel in) {
            return new MessageDAO(in);
        }

        public MessageDAO[] newArray(int size) {
            return new MessageDAO[size];
        }
    };

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
