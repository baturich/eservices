package dbService.dataSets;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "students")
public class StudentsDataSet implements Serializable { // Serializable Important to Hibernate!
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String fName;

    @Column (name = "lName")
    private String lName;

    @Column
    private String otchestvo;

    @Column
    private String birthdate;

    @Column
    private String mobilePhone;

    @Column
    private String napryam;

    @Column
    private int nNI; //номер института

    @Column
    private int year; //курс

    @Column
    private int oKR; //1-баклавр, 2-спец., 3-магистр

    @Column
    private int formaNavch; //1-дневная, 2-заочная

    @Column
    private int budget; //1-бюджет, 2-контракт

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public StudentsDataSet() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public StudentsDataSet(long id, String fName) {
        this.setId(id);
        this.setfName(fName);
    }

    public StudentsDataSet(String lName) {
        this.setId(-1);
        this.setlName(lName);
    }

    @SuppressWarnings("UnusedDeclaration")
    public StudentsDataSet(String sFirstName, String sLastName, String sOtchestvo, String sBirthdate, String sMobilePhone, String sNapryam, int iNNI, int iYear, int iOKR, int iFormaNavch, int iBudget) {
        this.fName = sFirstName;
        this.lName = sLastName;
        this.otchestvo = sOtchestvo;
        this.birthdate = sBirthdate;
        this.mobilePhone = sMobilePhone;
        this.napryam = sNapryam;
        this.nNI = iNNI;
        this.year = iYear;
        this.oKR = iOKR;
        this.formaNavch = iFormaNavch;
        this.budget = iBudget;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getNapryam() {
        return napryam;
    }

    public void setNapryam(String napryam) {
        this.napryam = napryam;
    }

    public int getnNI() {
        return nNI;
    }

    public void setnNI(int nNI) {
        this.nNI = nNI;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getoKR() {
        return oKR;
    }

    public void setoKR(int oKR) {
        this.oKR = oKR;
    }

    public int getFormaNavch() {
        return formaNavch;
    }

    public void setFormaNavch(int formaNavch) {
        this.formaNavch = formaNavch;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }


    @Override
    public String toString() {
        return "StudentsDataSet{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", otchestvo='" + otchestvo + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", napryam='" + napryam + '\'' +
                ", nNI=" + nNI +
                ", year=" + year +
                ", oKR=" + oKR +
                ", formaNavch=" + formaNavch +
                ", budget=" + budget +
                '}';
    }
}