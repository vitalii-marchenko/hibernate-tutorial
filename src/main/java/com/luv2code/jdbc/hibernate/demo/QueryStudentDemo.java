package com.luv2code.jdbc.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.jdbc.hibernate.demo.entity.Student;

public class QueryStudentDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            List<Student> theStudents = session.createQuery("from Student").getResultList();

            printStudents(theStudents);

            theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();

            printStudents(theStudents);

            theStudents = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'")
                    .getResultList();

            printStudents(theStudents);

            theStudents = session.createQuery("from Student s where s.email LIKE '%@gmail.com'").getResultList();

            printStudents(theStudents);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }

    private static void printStudents(List<Student> theStudents) {
        System.out.println("==========================\n");
        for (Student student : theStudents) {
            System.out.println(student);
        }
    }
}
