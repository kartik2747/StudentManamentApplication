package com.jspiders.springmvc4.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.jspiders.springmvc4.pojo.AdminPojo;

@Repository
public class AdminRepository {
          private static EntityManagerFactory factory;
          private static EntityManager manager;
          private static EntityTransaction transaction;
          private static Query query;
          
          private static void openConnection() {
        	  factory = Persistence.createEntityManagerFactory("mvc");
        	  manager = factory.createEntityManager();
        	  transaction = manager.getTransaction();  
          }
          private static void closeConnection() {
        	  if (factory != null) {
        		  factory.close();
        	  }
        	  if(manager != null) {
        		  manager.close();
        	  }
        	  if(transaction != null) {
        		  if(transaction.isActive()) {
        			  transaction.rollback();
        		  }
        	  }
          }
		public AdminPojo getAdmin() {
			openConnection();
			transaction.begin();
			
			String jpql = "from AdminPojo";
			query = manager.createQuery(jpql);
			List<AdminPojo> admins =query.getResultList();
			
			if(admins.isEmpty()==false) {
				for (AdminPojo pojo : admins) {
					transaction.commit();
					closeConnection();
					return pojo;
				}
			}
			
			transaction.commit();
			closeConnection();
			return null;
		}
		public AdminPojo createAccount(String username, String password) {
			openConnection();
			transaction.begin();
			
			AdminPojo pojo = new AdminPojo();
			pojo.setUsername(username);
			pojo.setPassword(password);
			
			manager.persist(pojo);
			
			transaction.commit();
			closeConnection();
			return pojo;
		}
		public AdminPojo login(String username, String password) {
			openConnection();
			transaction.begin();
			
			String jpql = "from AdminPojo "
					+ "where username = '" + username + "' "
					+ "and password = '" + password + "'";
			
			query = manager.createQuery(jpql);
			
			List<AdminPojo> admins = query.getResultList();
			if(admins.isEmpty()== false) {
				for(AdminPojo pojo : admins) {
					transaction.commit();
					closeConnection();
					System.out.println(pojo);
					return pojo;
					
				}
			}
			
			transaction.commit();
			closeConnection();
			return null;
		}
          
}
