package doa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(Student student){
        Session session = sessionFactory.getCurrentSession();
        session.save(student);
    }

    public List<Student> getAll(){
        return sessionFactory.getCurrentSession()
                .createQuery("from Student", Student.class)
                .list();
    }
}
