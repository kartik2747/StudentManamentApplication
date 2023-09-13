package com.jspiders.springmvc4.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.jspiders.springmvc4.pojo.StudentPojo;
import com.mysql.cj.Query;

@Repository
public class StudentRepository {
       private static EntityManagerFactory factory;
       private static EntityManager manager;
       private static EntityTransaction transaction;
       private static javax.persistence.Query query;
       
       public static void openConnection() {
    	   factory = Persistence.createEntityManagerFactory("mvc");
    	   manager = factory.createEntityManager();
    	   transaction = manager.getTransaction();
       }
       private static void closeConnection() {
    	   if(factory != null) {
    		   factory.close();
    	   }
    	   	if (manager != null) {
    	   		manager.close();
    	   	}
    	   	if (transaction != null) {
    	   		if(transaction.isActive()){
    	   			transaction.rollback();
    	   		}
    	   	}
       }
	public StudentPojo searchStudent(int id) {
		openConnection();
		transaction.begin();
		
		StudentPojo pojo = manager.find(StudentPojo.class, id);
		
		transaction.commit();
		closeConnection();
		return pojo;
	}
	public StudentPojo addStudent(String name, String email, long contact, String address) {
		openConnection();
		transaction.begin();
		
		StudentPojo pojo = new StudentPojo();
		pojo.setName(name);
		pojo.setEmail(email);
		pojo.setContact(contact);
		pojo.setAddress(address);
		
		manager.persist(pojo);
		
		transaction.commit();
		closeConnection();
		return pojo;
	}
	public List<StudentPojo> findAllStudents() {
		openConnection();
		transaction.begin();
		
		String jpql = "from StudentPojo";
		query = manager.createQuery(jpql);
		List<StudentPojo> students = query.getResultList();
		
		transaction.commit();
		closeConnection();
		return students;
	}
	public StudentPojo removeStudent(int id) {
		openConnection();
		transaction.begin();
		
		StudentPojo pojo = manager.find(StudentPojo.class, id);
		 if(pojo != null) {
			 manager.remove(pojo);
		 }
		
		transaction.commit();
		closeConnection();
		return pojo;
	}
	public StudentPojo updateStudent(int id, String name, String email, long contact, String address) {
		openConnection();
		transaction.begin();
		
		StudentPojo pojo = manager.find(StudentPojo.class, id);
		pojo.setName(name);
		pojo.setEmail(email);
		pojo.setContact(contact);
		pojo.setAddress(address);
		
		manager.persist(pojo);
		
		transaction.commit();
		closeConnection();
		return pojo;
	}
       
       
}
