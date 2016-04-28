package edu.nsu.ir.Cases;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nyky on 4/12/2016.
 */
public class CaseDAO implements Parcelable {

    private static final long serialVersion = 0;
    private String case_name = "dummy";
    private String case_status = "dummy";
    private String case_id = "dummy";
    private String court_number = "dummy";
    private String evidence = "dummy";
    private String os_type = "dummy";

    public static long getSerialVersion() {
        return serialVersion;
    }

    public String getCase_name() {
        return case_name;
    }

    public void setCase_name(String case_name) {
        this.case_name = case_name;
    }

    public String isCase_status() {
        return case_status;
    }

    public void setCase_status(String case_status) {
        this.case_status = case_status;
    }

    public String getCase_id() {
        return case_id;
    }

    public void setCase_id(String case_id) {
        this.case_id = case_id;
    }

    public String getCourt_number() {
        return court_number;
    }

    public void setCourt_number(String court_number) {
        this.court_number = court_number;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getOs_type() {
        return os_type;
    }

    public void setOs_type(String os_type) {
        this.os_type = os_type;
    }

    protected CaseDAO(Parcel in) {
        case_name = in.readString();
        case_status  = in.readString();
        case_id  = in.readString();
        court_number  = in.readString();
        evidence = in.readString();
        os_type = in.readString();
    }

    public static final Creator<CaseDAO> CREATOR = new Creator<CaseDAO>() {
        @Override
        public CaseDAO createFromParcel(Parcel in) {
            return new CaseDAO(in);
        }

        @Override
        public CaseDAO[] newArray(int size) {
            return new CaseDAO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(case_name);
        dest.writeString(case_status);
        dest.writeString(case_id);
        dest.writeString(court_number);
        dest.writeString(evidence);
        dest.writeString(os_type);


    }
}
