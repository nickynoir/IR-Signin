package edu.nsu.ir.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nyky on 4/12/2016.
 */
public class CaseDAO implements Parcelable {

    private static final long serialVersion = 0;
    private String case_name;
    private String case_status;
    private String case_id;
    private String court_number;
    private String evidence;
    private String os_type;
    private String serialNumber;
    private String modelNumber;
    private String hostName;
    private String macAddress;
    private String ipAddress, CaseNumber,Desc,dateMod;
public CaseDAO(){}
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
        Desc =in.readString();
        CaseNumber = in.readString();

        serialNumber= in.readString();
        modelNumber= in.readString();
        hostName= in.readString();
        macAddress= in.readString();
        ipAddress= in.readString();
        dateMod =in.readString();

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
        dest.writeString(ipAddress);
        dest.writeString(macAddress);
        dest.writeString(hostName);
        dest.writeString(modelNumber);
        dest.writeString(serialNumber);
        dest.writeString(CaseNumber);
        dest.writeString(Desc);
        dest.writeString(dateMod);
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCaseNumber() {
        return CaseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        CaseNumber = caseNumber;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getDateMod() {
        return dateMod;
    }

    public void setDateMod(String dateMod) {
        this.dateMod = dateMod;
    }
}
