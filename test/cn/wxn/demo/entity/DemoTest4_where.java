package cn.wxn.demo.entity;

import java.util.ArrayList;

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

public class DemoTest4_where {

	private SessionFactory sessionFactory;
	private Session openSession;
	private Transaction transaction;

	@Before
	public void init() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			openSession = sessionFactory.openSession();
			transaction = openSession.beginTransaction();
		} catch (Exception ex) {
			ex.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}

		if (openSession != null) {
			System.out.println("openSession is not null");
		}

	}

	@After
	public void destory() {
		transaction.commit();
		openSession.close();
		sessionFactory.close();
	}

	//////////////////////////////////////////////////////////////////////////
	///////////////// *****************************************////////////////
	///////////////// *****************************************////////////////
	///////////////// *****************************************////////////////
	///////////////// *****************************************////////////////
	///////////////// *****************************************////////////////
	//////////////////////////////////////////////////////////////////////////

	
	@Test
	public void test1(){
		String hql = "from Commodity commodity where commodity.price>400";
		Query createQuery = openSession.createQuery(hql);
		ArrayList<Commodity> commodities = (ArrayList<Commodity>) createQuery.list();
		if (commodities != null && commodities.size() > 0) {
			for(Commodity commodity : commodities) {
				System.out.println(commodity.toString());
			}
		}
	}
	
	
	@Test
	public void test2(){
		String hql = "from Commodity commodity where commodity.description is null";
		Query createQuery = openSession.createQuery(hql);
		ArrayList<Commodity> commodities = (ArrayList<Commodity>) createQuery.list();
		if (commodities != null && commodities.size() > 0) {
			for(Commodity commodity : commodities) {
				System.out.println(commodity.toString());
			}
		}
	}
	
	@Test
	public void test3(){
		String hql = "from Commodity commodity where commodity.price in (200,400)";
		Query createQuery = openSession.createQuery(hql);
		ArrayList<Commodity> commodities = (ArrayList<Commodity>) createQuery.list();
		if (commodities != null && commodities.size() > 0) {
			for(Commodity commodity : commodities) {
				System.out.println(commodity.toString());
			}
		}
	}
	
	@Test
	public void test4(){
		String hql = "from Commodity commodity where commodity.price between 20 and 200";
		Query createQuery = openSession.createQuery(hql);
		ArrayList<Commodity> commodities = (ArrayList<Commodity>) createQuery.list();
		if (commodities != null && commodities.size() > 0) {
			for(Commodity commodity : commodities) {
				System.out.println(commodity.toString());
			}
		}
	}
	
	
	
	@Test
	public void test5(){
		String hql = "from Customer c where c.name like '张_'";
		Query createQuery = openSession.createQuery(hql);
		ArrayList<Customer> customers = (ArrayList<Customer>) createQuery.list();
		if (customers != null && customers.size() > 0) {
			for(Customer customer : customers) {
				System.out.println(customer.toString());
			}
		}
	}
	
	
	@Test
	public void test6(){
		String hql = "from Customer c where c.address like '%北京%'";
		Query createQuery = openSession.createQuery(hql);
		ArrayList<Customer> customers = (ArrayList<Customer>) createQuery.list();
		if (customers != null && customers.size() > 0) {
			for(Customer customer : customers) {
				System.out.println(customer.toString());
			}
		}
	}
	
	@Test
	public void test7(){
		String hql = "from Commodity c where c.price between 300 and 5000 and c.category like '%电脑%'";
		Query createQuery = openSession.createQuery(hql);
		ArrayList<Commodity> customers  = (ArrayList<Commodity>) createQuery.list();
		if (customers != null && customers.size() > 0) {
			for(Commodity commodity : customers) {
				System.out.println(commodity.toString());
			}
		}
	}
	
	
	@Test
	public void test8(){
		String hql = "from Commodity c where c.price between 300 and 5000 or c.category like '%电脑%'";
		Query createQuery = openSession.createQuery(hql);
		ArrayList<Commodity> customers  = (ArrayList<Commodity>) createQuery.list();
		if (customers != null && customers.size() > 0) {
			for(Commodity commodity : customers) {
				System.out.println(commodity.toString());
			}
		}
	}
	
	
	@Test
	public void test9(){
		String hql = "from OrderForm o where o.orderItems is empty";
		Query createQuery = openSession.createQuery(hql);
		ArrayList<OrderForm> orderForms  = (ArrayList<OrderForm>) createQuery.list();
		if (orderForms != null && orderForms.size() > 0) {
			for(OrderForm orderForm : orderForms) {
				System.out.println(orderForm.toString());
			}
		}
	}
	
	
	
	@Test
	public void test10(){
		String hql = "from Commodity c where c.price * 5 < 1000";
		Query createQuery = openSession.createQuery(hql);
		ArrayList<Commodity> commodities    = (ArrayList<Commodity>) createQuery.list();
		if (commodities != null && commodities.size() > 0) {
			for(Commodity commodity : commodities) {
				System.out.println(commodity.toString());
			}
		}
	}
	
	
	
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void test11(){
		String hql = "from Customer c where c.name='张三'";
		Query createQuery = openSession.createQuery(hql);
		Customer customer = (Customer) createQuery.uniqueResult();
		System.out.println(customer);
		
	}
	
	
	
	
}
