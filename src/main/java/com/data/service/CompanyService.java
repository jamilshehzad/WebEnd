package com.data.Service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.data.Domain.Company;
import com.data.Domain.Person;
import com.data.Utility.HibernateUtil;

public class CompanyService {
	public boolean saveCompany(Company company){
		 Session session = HibernateUtil.openSession();
		 if(isCompanyExists(company)) return false;	
		
		 Transaction tx = null;	
		 try {
			 tx = session.getTransaction();
			 tx.begin();
			 session.saveOrUpdate(company);		
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
	
	public boolean isCompanyExists(Company company){
		 Session session = HibernateUtil.openSession();
		 boolean result = false;
		 Transaction tx = null;
		 try{
			 tx = session.getTransaction();
			 tx.begin();
			 Query query = session.createQuery("from Company where name='"+company.getName()+"'");
			 Company c = (Company)query.uniqueResult();
			 tx.commit();
			 if(c!=null) result = true;
		 }catch(Exception ex){
			 if(tx!=null){
				 tx.rollback();
			 }
		 }finally{
			 session.close();
		 }
		 return result;
	}
	
	public List<Company> getListOfCompanys(){
		 List<Company> list = new ArrayList<Company>();
		 Session session = HibernateUtil.openSession();
		 Transaction tx = null;	
		 try {
			 tx = session.getTransaction();
			 tx.begin();
			 list = session.createQuery("from Company").list();					
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
