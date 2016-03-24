package edu.nsu.ir.Cases;

import android.os.Parcel;
import android.os.Parcelable;

public class CCase implements Parcelable {
    private String name;
    private boolean status;

    public CCase() {
    }

    public CCase(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    protected CCase(Parcel in) {
        name = in.readString();
        status = in.readByte() != 0;
    }

    public static final Creator<CCase> CREATOR = new Creator<CCase>() {
        @Override
        public CCase createFromParcel(Parcel in) {
            return new CCase(in);
        }

        @Override
        public CCase[] newArray(int size) {
            return new CCase[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CCase{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte) (status ? 1 : 0));
    }
}
