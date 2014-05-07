package com.data.Service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.data.Domain.Person;
import com.data.Utility.HibernateUtil;

public class PersonService {
	public boolean savePerson(Person person){
		 Session session = HibernateUtil.openSession();
		 if(isPersonExists(person)) return false;	
		
		 Transaction tx = null;	
		 try {
			 tx = session.getTransaction();
			 tx.begin();
			 session.saveOrUpdate(person);		
			 tx.commit();
		 } catch (Exception e) {
			 if (tx != null) {
				 tx.rollback();
			 }
			 e.printStackTrace();
		 } finally {
			 session.close();
		 }	
		 return true;
	}
	
	public boolean isPersonExists(Person person){
		 Session session = HibernateUtil.openSession();
		 boolean result = false;
		 Transaction tx = null;
		 try{
			 tx = session.getTransaction();
			 tx.begin();
			 Query query = session.createQuery("from Person where firstName='"+person.getFirstName()+"' and lastName='"+person.getLastName()+"'");
			 Person p = (Person)query.uniqueResult();
			 tx.commit();
			 if(p!=null) result = true;
		 }catch(Exception ex){
			 if(tx!=null){
				 tx.rollback();
			 }
		 }finally{
			 session.close();
		 }
		 return result;
	}
	
	public List<Person> getListOfPersons(){
		 List<Person> list = new ArrayList<Person>();
		 Session session = HibernateUtil.openSession();
		 Transaction tx = null;	
		 try {
			 tx = session.getTransaction();
			 tx.begin();
			 list = session.createQuery("from Person").list();					
			 tx.commit();
		 } catch (Exception e) {
			 if (tx != null) {
				 tx.rollback();
			 }
			 e.printStackTrace();
		 } finally {
			 session.close();
		 }
		 return list;
	}
}
