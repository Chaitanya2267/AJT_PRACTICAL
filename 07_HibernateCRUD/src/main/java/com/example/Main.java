package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        
        // 1. Build SessionFactory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        
        // --- 1. CREATE ---
        Session session1 = factory.openSession();
        Transaction tx1 = session1.beginTransaction();
        
        Student newStudent = new Student("John", "john@example.com");
        session1.save(newStudent);
        
        tx1.commit();
        session1.close();
        System.out.println(">>> Student Created with ID: " + newStudent.getId());

        // --- 2. READ ---
        Session session2 = factory.openSession();
        Student fetchedStudent = session2.get(Student.class, newStudent.getId());
        System.out.println(">>> Fetched Name: " + fetchedStudent.getName());
        session2.close();

        // --- 3. UPDATE ---
        Session session3 = factory.openSession();
        Transaction tx3 = session3.beginTransaction();
        
        fetchedStudent.setEmail("newemail@example.com");
        session3.update(fetchedStudent);
        
        tx3.commit();
        session3.close();
        System.out.println(">>> Student Email Updated.");

        
        
        factory.close();
    }
}
