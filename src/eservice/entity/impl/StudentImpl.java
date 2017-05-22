package eservice.entity.impl;


import eservice.entity.Student;

public class StudentImpl implements Student {
    private String sFirstName, sLastName, sOtchestvo, sBirthdate, sMobilePhone, sNapryam;
    private int iNNI; //номер института
    private int iYear; //курс
    private int iOKR; //1-баклавр, 2-спец., 3-магистр
    private int iFormaNavch; //1-дневная, 2-заочная
    private int iBudget; //1-бюджет, 2-контракт
    private int iSex; //1-мужской, 2-женский
    private String sEmail, sDestination;

    public StudentImpl(String sFirstName, String sLastName, String sOtchestvo, String sBirthdate, String sMobilePhone, String sNapryam, int iNNI, int iYear, int iOKR, int iFormaNavch, int iBudget, String sEmail, int iSex, String sDestination) {
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
        this.sEmail = sEmail;
        this.iSex = iSex;
        this.sDestination = sDestination;
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
                ", sEmail='" + sEmail + '\'' +
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

    @Override
    public String getsEmail() {
        return sEmail;
    }

    @Override
    public int getiSex() {
        return iSex;
    }

    @Override
    public String getsDestination() {
        return sDestination;
    }
}
