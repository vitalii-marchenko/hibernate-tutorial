package com.luv2code.jdbc.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.jdbc.hibernate.demo.entity.Student;

public class ReadStudentDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            System.out.println("Creating a new student object...");
            Student tempStudent = new Student("Daffy", "Duck", "daffy@gmail.com");

            session.beginTransaction();

            System.out.println("Saving the student... " + tempStudent);
            session.save(tempStudent);

            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Getting student with id...: " + tempStudent.getId());

            Student foundStudent = session.get(Student.class, tempStudent.getId());

            System.out.println("Student found "+  foundStudent);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
