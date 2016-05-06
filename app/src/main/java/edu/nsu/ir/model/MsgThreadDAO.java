package edu.nsu.ir.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 3/27/2016.
 */
public class MsgThreadDAO implements Parcelable {
    private String toUname,fromUname,Subject,threadId,imgPath;

    public MsgThreadDAO(String toUname, String fromUname, String Subject, String threadId,String imgPath) {

        this.toUname = toUname;
        this.fromUname = fromUname;
        this.Subject = Subject;
        this.threadId = threadId;
        this.imgPath= imgPath;//ignore
    }

    public MsgThreadDAO() {

    }

    public String getToUname() {
        return toUname;
    }

    public void setToUname(String toUname) {
        this.toUname = toUname;
    }

    public String getFromUname() {
        return fromUname;
    }

    public void setFromUname(String fromUname) {
        this.fromUname = fromUname;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }
    public MsgThreadDAO(Parcel in) {
        String[] data = new String[5];

        in.readStringArray(data);
        this.toUname = data[0];
        this.fromUname = data[1];
        this.Subject = data[2];
        this.threadId = data[3];
        this.imgPath = data[4];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.toUname,
                this.fromUname,
                this.Subject, this.threadId, this.imgPath});
    }

    public static final Creator CREATOR = new Creator() {
        public MsgThreadDAO createFromParcel(Parcel in) {
            return new MsgThreadDAO(in);
        }

        public MsgThreadDAO[] newArray(int size) {
            return new MsgThreadDAO[size];
        }
    };

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}

