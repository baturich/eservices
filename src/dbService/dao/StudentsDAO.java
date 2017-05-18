package dbService.dao;

import dbService.dataSets.StudentsDataSet;
import eservice.entity.Student;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class StudentsDAO {

    private Session session;

    public StudentsDAO(Session session) {
        this.session = session;
    }

    public StudentsDataSet get(Student student) throws HibernateException {
        List<StudentsDataSet> students;
        try{
            session.beginTransaction();
            String student_lName = student.getsLastName();
            Query query = session.createQuery(
                    "select s "
                        + "from StudentsDataSet s"
                        + " where s.lName = :lastName"
            )
                    .setParameter("lastName",student_lName);
//                    .setString("lastName",student_lName);
            students = (List<StudentsDataSet>) query.list();
            session.getTransaction().commit();
        } finally {
            closeSession();
        }
        if(students.size() > 0) return getStudent(student, students);
        else return null;


//        return (StudentsDataSet) session.get(StudentsDataSet.class, fName);
    }

    public StudentsDataSet getById(long id) throws HibernateException {
        return (StudentsDataSet) session.get(StudentsDataSet.class, id);
    }

    public long getUserId(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(StudentsDataSet.class);
        return ((StudentsDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();
    }

    public long insertUser(String name) throws HibernateException {
        return (Long) session.save(new StudentsDataSet(name));
    }

    public long insertUser(String sFirstName, String sLastName, String sOtchestvo, String sBirthdate, String sMobilePhone, String sNapryam, int iNNI, int iYear, int iOKR, int iFormaNavch, int iBudget) throws HibernateException {
        return (Long) session.save(new StudentsDataSet(sFirstName, sLastName, sOtchestvo, sBirthdate, sMobilePhone, sNapryam, iNNI, iYear, iOKR, iFormaNavch, iBudget));
    }

    private void closeSession() {
        if(session != null && session.isOpen()) {
            session.close();
        }
    }

    private StudentsDataSet getStudent(Student student, List<StudentsDataSet> students) {
        for(StudentsDataSet neededStudent : students)
            if(neededStudent.getlName().equals(student.getsLastName()))
                if(neededStudent.getfName().equals(student.getsFirstName()))
                    if(neededStudent.getOtchestvo().equals(student.getsOtchestvo()))
                        if(neededStudent.getBirthdate().equals(student.getsBirthdate()))
                            if(neededStudent.getMobilePhone().equals(student.getsMobilePhone()))
                                if(neededStudent.getNapryam().equals(student.getsNapryam()))
                                    if(neededStudent.getnNI() == student.getiNNI())
                                        if(neededStudent.getYear() == student.getiYear())
                                            if(neededStudent.getoKR() == student.getiOKR())
                                                if(neededStudent.getFormaNavch() == student.getiFormaNavch())
                                                    if(neededStudent.getBudget() == student.getiBudget())
                                                        return neededStudent;
        return null;
    }
}
