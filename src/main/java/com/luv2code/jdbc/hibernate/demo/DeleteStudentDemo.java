package com.luv2code.jdbc.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.jdbc.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            int studentId = 1;
            session.beginTransaction();

            Student myStudent = session.get(Student.class, studentId);

            System.out.println("Deleting student");
            session.delete(myStudent);

            session.getTransaction().commit();

            session.createQuery("delete from Student where id=2").executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
