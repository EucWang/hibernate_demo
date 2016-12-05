package cn.wxn.demo.entity;

import java.util.ArrayList;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.hql.internal.ast.HqlASTFactory;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import antlr.collections.List;
import cn.wxn.demo.entity.Commodity;
import cn.wxn.demo.entity.Customer;
import cn.wxn.demo.entity.OrderItem;
import cn.wxn.demo.entity.Seller;

public class DemoTest2_from {
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
	/////////////////*****************************************////////////////
	/////////////////*****************************************////////////////
	/////////////////*****************************************////////////////
	/////////////////*****************************************////////////////
	/////////////////*****************************************////////////////
	//////////////////////////////////////////////////////////////////////////
	
	/**
	 * 最简单的查询
	 * 简单Query创建, 以及查询语句
	 */
	@Test
	public void testSimpleQuery(){
		
		String hql = "from Seller";   //from子句  -- 指定类名,
		Query query = openSession.createQuery(hql);
		ArrayList<Seller> sellers = (ArrayList<Seller>) query.list();
		if (sellers != null && sellers.size() > 0) {
			for(Seller seller : sellers){
				System.out.println(seller);
			}
		}
	}
	
	
	/**
	 * 关联对象的数据,只有在使用到的时候hibernate才会去查询数据库
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testSimpleQuery2(){
		String hql = "from Commodity";
		Query query = openSession.createQuery(hql);
		ArrayList<Commodity> commodities = (ArrayList<Commodity>)query.list();
		for(Commodity commodity : commodities) {
			System.out.println(commodity);
			Set<OrderItem> orderItems = commodity.getOrderItems();
			for(OrderItem item : orderItems){
				System.out.println(item.toString());
			}
		}
	}
	
	/**
	 * 指定查询的别名
	 */
	@Test
	public void testSimpleQuery3(){
		
		String hql = "from Customer as customer";
		Query createQuery = openSession.createQuery(hql);
		ArrayList<Customer> customeres = (ArrayList<Customer>) createQuery.list();
		if (customeres != null && customeres.size() > 0) {
			for(Customer customer : customeres){
				System.out.println(customer);
			}
		}
	}
}
