package eservice.entity.impl;


import eservice.entity.Student;

public class StudentImpl implements Student {
    private String sFirstName, sLastName, sOtchestvo, sBirthdate, sMobilePhone, sNapryam;
    private int iNNI; //номер института
    private int iYear; //курс
    private int iOKR; //1-баклавр, 2-спец., 3-магистр
    private int iFormaNavch; //1-дневная, 2-заочная
    private int iBudget; //1-бюджет, 2-контракт

    public StudentImpl(String sFirstName, String sLastName, String sOtchestvo, String sBirthdate, String sMobilePhone, String sNapryam, int iNNI, int iYear, int iOKR, int iFormaNavch, int iBudget) {
        this.sFirstName = sFirstName;
        this.sLastName = sLastName;
        this.sOtchestvo = sOtchestvo;
        this.sBirthdate = sBirthdate;
        this.sMobilePhone = sMobilePhone;
        this.sNapryam = sNapryam;
        this.iNNI = iNNI;
        this.iYear = iYear;
        this.iOKR = iOKR;
        this.iFormaNavch = iFormaNavch;
        this.iBudget = iBudget;
    }

    @Override
    public String toString() {
        return "StudentImpl{" +
                "sFirstName='" + sFirstName + '\'' +
                ", sLastName='" + sLastName + '\'' +
                ", sOtchestvo='" + sOtchestvo + '\'' +
                ", sBirthdate='" + sBirthdate + '\'' +
                ", sMobilePhone='" + sMobilePhone + '\'' +
                ", sNapryam='" + sNapryam + '\'' +
                ", iNNI=" + iNNI +
                ", iYear=" + iYear +
                ", iOKR=" + iOKR +
                ", iFormaNavch=" + iFormaNavch +
                ", iBudget=" + iBudget +
                '}';
    }

    @Override
    public String getsFirstName() {
        return sFirstName;
    }

    @Override
    public String getsLastName() {
        return sLastName;
    }

    @Override
    public String getsOtchestvo() {
        return sOtchestvo;
    }

    @Override
    public String getsBirthdate() {
        return sBirthdate;
    }

    @Override
    public String getsMobilePhone() {
        return sMobilePhone;
    }

    @Override
    public String getsNapryam() {
        return sNapryam;
    }

    @Override
    public int getiNNI() {
        return iNNI;
    }

    @Override
    public int getiYear() {
        return iYear;
    }

    @Override
    public int getiOKR() {
        return iOKR;
    }

    @Override
    public int getiFormaNavch() {
        return iFormaNavch;
    }

    @Override
    public int getiBudget() {
        return iBudget;
    }
}
