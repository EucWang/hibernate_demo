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

public class DemoTest5_orderby {

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

	
	/**
	 * 单个排序规则
	 */
	@Test
	public void test1(){
		
		String hql = "from Commodity c order by c.price asc";
		
		Query query = openSession.createQuery(hql);
		
		ArrayList<Commodity> commodities = (ArrayList<Commodity>) query.list();
		if (commodities != null && commodities.size() > 0)  {
			for(Commodity commodity : commodities) {
				System.out.println(commodity.getName() + " : " + commodity.getPrice());
			}
		}
	}
	
	/**
	 * 多个排序规则
	 */
	@Test
	public void test2(){
		String hql = "from Commodity c order by c.seller.id asc, c.price desc";
		Query query = openSession.createQuery(hql);
		ArrayList<Commodity> commodities = (ArrayList<Commodity>) query.list();
		if (commodities != null && commodities.size() > 0) {
			for(Commodity commodity : commodities) {
				System.out.println(commodity.getName() + " : " + commodity.getSeller().getId() + " : " + commodity.getPrice());
			}
		}
		
	}
	
	
}
