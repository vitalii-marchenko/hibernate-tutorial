package com.luv2code.jdbc.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.jdbc.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            int studentId = 1;
            session.beginTransaction();

            Student meStudent = session.get(Student.class, studentId);

            System.out.println("Updating student.....");

            meStudent.setFirstName("Scooby");

            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Updating email for all students");

            session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
