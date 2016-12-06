package cn.wxn.demo.entity;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DemoTest7_encache {
	 
	/**
	 * 创建二级缓存
	 */
	@Test
	public void test(){
		Session session = SessionFactoryUtil.getInstance().getSession();
		Transaction transaction = session.beginTransaction();
		Customer customer = session.get(Customer.class, 1L);
		System.out.println(customer.toString());
		transaction.commit();
		session.close();
		
		
		Session session2 = SessionFactoryUtil.getInstance().getSession();
		Transaction transaction2 = session2.beginTransaction();
		Customer customer2 = session2.get(Customer.class, 1L);
		System.out.println(customer2.toString());
		transaction2.commit();
		session2.close();

	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test1(){
		
		Session session2 = SessionFactoryUtil.getInstance().getSession();
		String hql = "from Customer where age>=20";
		Query query2 = session2.createQuery(hql);
		ArrayList<Customer> customers = (ArrayList<Customer>) query2.list();
		for (int i = 0; i < customers.size(); i++) {
			Customer customer = customers.get(i);
			System.out.println(customer);
		}
		session2.close();
		
		Session session3 = SessionFactoryUtil.getInstance().getSession();
		String hql3 = "from Customer where description like '%爱%'";
		Query query3 = session3.createQuery(hql3);
		ArrayList<Customer> customers2 = (ArrayList<Customer>) query3.list();
		for (int i = 0; i < customers2.size(); i++) {
			Customer customer = customers2.get(i);
			System.out.println(customer);
		}
		session3.close();
		
		Session session = SessionFactoryUtil.getInstance().getSession();
		String hql2 = "from Customer where gender = '女'";
		Query query = session.createQuery(hql2);
		Iterator iterate = query.iterate();
		while (iterate.hasNext()) {
			Customer customer = (Customer) iterate.next();
			System.out.println(customer.toString());
		}
		session.close();
	}
}
