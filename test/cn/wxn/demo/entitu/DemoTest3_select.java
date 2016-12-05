package cn.wxn.demo.entitu;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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

import antlr.collections.List;
import cn.wxn.demo.entity.Seller;

public class DemoTest3_select {
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
	 * 查询多个属性, 默认返回Object[]
	 */
	@Test
	public void test1() {
		String hql = "select s.name, s.address, s.tel from Seller s";
		Query query = openSession.createQuery(hql);
		ArrayList<Object[]> obs = (ArrayList<Object[]>) query.list();
		if (obs != null && obs.size() > 0) {
			for (Object[] ob : obs) {
				for (int i = 0; i < ob.length; i++) {
					System.out.println("==== " + ob[i]);
				}
				System.out.println("===========");
			}
		}
	}

	/**
	 * 只查找一个属性, 那么返回的只是一个Object,而不是默认的Object[]
	 */
	@Test
	public void test2() {
		String hql = "select s.name from Seller s";
		Query query = openSession.createQuery(hql);
		ArrayList<Object> obs = (ArrayList<Object>) query.list();
		if (obs != null && obs.size() > 0) {
			Object object = obs.get(0);
			System.out.println("==== " + object);
		}
	}
	
	/**
	 * 返回一个map,用别名指定map的索引
	 * 如果不指定别名, 那么map的默认索引值就是 0,1,2,3,4..等字符串
	 */
	@Test
	public void test3() {
		String hql = "select new map(s.name as name, s.tel as tel, s.address as address) from Seller s";
		Query query = openSession.createQuery(hql);
		ArrayList<Map<String, String>> list = (ArrayList<Map<String, String>>) query.list();
		for(Map<String, String> map : list) {
			Set<Entry<String,String>> entrySet = map.entrySet();
			for(Entry<String, String> entry: entrySet) {
				System.out.print(entry.getKey() + ":");
				System.out.print(entry.getValue());
				System.out.println();
			}
			System.out.println("..............");
		}
	}
	
	/**
	 * 返回自定义的对象
	 * 自定义的对象需要生成对应参数的构造方法才能使用如下方式查询
	 */
	@Test
	public void test4(){
		String hql = "select new Seller(s.name, s.tel, s.address) from Seller s";
		Query query = openSession.createQuery(hql);
		ArrayList<Seller> list = (ArrayList<Seller>) query.list();
		for(Seller seller : list) {
			System.out.println("seller.............." + seller.toString());
		}
	}

	/**
	 * 使用distinct关键字去掉查询结果中的重复元素
	 */
	@Test
	public void test5(){
		String hql = "select distinct c.gender from Customer c";
		Query createQuery = openSession.createQuery(hql);
		ArrayList<Object> list = (ArrayList<Object>) createQuery.list();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object object = list.get(i);
				System.out.println(object.toString());
			}
		}
	}
}
