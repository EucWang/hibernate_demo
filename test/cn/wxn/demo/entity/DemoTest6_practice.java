package cn.wxn.demo.entity;

import java.util.ArrayList;
import java.util.Map;

import javax.persistence.criteria.Order;

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

public class DemoTest6_practice {
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
	///////////////// 练习1：**************************************////////////////
	///////////////// 1.查询商品的名称，价格，所属商家名称， 类别**************////////////////
	///////////////// 2.查询范围限定在类别与“书”相关，价格不低于10元的商品****////////////////
	///////////////// 3.查询结果按照商品的所属商家名称排序，价格降序和商品名称升序排序****////////////////
	///////////////// *****************************************////////////////
	///////////////// *****************************************////////////////
	///////////////// *****************************************////////////////
	//////////////////////////////////////////////////////////////////////////

	/**
	 * 1.查询商品的名称，价格，所属商家名称， 类别
	 */
	@Test
	public void test1() {
		String hql = "select new map(c.name as NAME, c.price as PRICE, c.seller.name as SELLERNAME, c.category as CATEGORY) from Commodity c";
		Query query = openSession.createQuery(hql);
		ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) query.list();

		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				if (map != null && map.size() > 0) {
					for (Map.Entry<String, Object> entry : map.entrySet()) {
						System.out.print("\t(" + entry.getKey() + ":" + entry.getValue() + ")");
					}
					System.out.println();
				}
			}
		}
	}

	/**
	 * 2.查询范围限定在类别与“书”相关，价格不低于10元的商品
	 */
	@Test
	public void test2() {
		String hql = "from Commodity c where c.category like '%书%' and c.price >= 30";
		Query query = openSession.createQuery(hql);
		ArrayList<Commodity> list = (ArrayList<Commodity>) query.list();

		if (list != null && list.size() > 0) {
			for (Commodity commodity : list) {
				System.out.println(commodity);
			}
		}
	}

	/**
	 * 3.查询结果按照商品的所属商家名称排序，价格降序和商品名称升序排序
	 */
	@Test
	public void test3() {
		String hql = "from Commodity c order by c.seller.name asc, c.price desc , c.name asc";
		Query query = openSession.createQuery(hql);
		ArrayList<Commodity> commodities = (ArrayList<Commodity>) query.list();
		if (commodities != null && commodities.size() > 0) {
			for (Commodity commodity : commodities) {
				System.out.println("\t(" + commodity.getSeller().getName() + ", " + commodity.getPrice() + ","
						+ commodity.getName() + ")");
			}
		}
	}

	/**
	 * 练习2： 1.查询订单的客户姓名，交易日期，订单状态，订单金额
	 * 2.查询范围限定在交易日期为2015-05-01到2015-06-01之间。订单状态为已发货或者已付款，订单金额大于1000元的订单信息
	 * 3.查询结果按照订单状态升序，交易日期降序，订单金额升序排序
	 */

	/**
	 * 1.查询订单的客户姓名，交易日期，订单状态，订单金额
	 */
	@Test
	public void test4() {
		String hql = "select o.customer.name, o.tradeDate, o.status, o.amount from OrderForm o";
		Query query = openSession.createQuery(hql);
		ArrayList<Object[]> objects = (ArrayList<Object[]>) query.list();
		if (objects != null && objects.size() > 0) {
			for (Object[] objs : objects) {
				if (objs != null && objs.length > 0) {
					System.out.print("\t(NAME:" + objs[0] + ")");
					System.out.print("\t(TRADEDATE:" + objs[1] + ")");
					System.out.print("\t(STATUS:" + objs[2] + ")");
					System.out.print("\t(AMOUNT:" + objs[3] + ")");
				}
				System.out.println();
			}

		}
	}

	/**
	 * 2.查询范围限定在交易日期为2015-05-01到2015-06-01之间。订单状态为已发货或者已付款，订单金额大于1000元的订单信息
	 */
	@Test
	public void test5() {
		String hql = "from OrderForm orderForm where "
				+ " orderForm.tradeDate between ('2016-12-01 00:00:00')"
				+ " and ('2016-12-04 00:00:00') "
				+ " and orderForm.amount > 1000 "
				+ " and orderForm.status='已收货' or orderForm.status='已付款'";
		Query query = openSession.createQuery(hql);
		ArrayList<OrderForm> orderForms = (ArrayList<OrderForm>) query.list();
		if (orderForms != null && orderForms.size() > 0) {
			for (OrderForm orderForm : orderForms) {
				System.out.println(orderForm.getId() + ",\t" + orderForm.getTradeDate() + ",\t" + orderForm.getStatus()
						+ ",\t" + orderForm.getAmount());
			}
		}
	}
	
	/**
	 *  3.查询结果按照订单状态升序，交易日期降序，订单金额升序排序
	 */
	@Test
	public void test6(){
		String hql = "from OrderForm o order by o.status asc, o.tradeDate desc, o.amount asc";
		Query query = openSession.createQuery(hql);
		ArrayList<OrderForm> list = (ArrayList<OrderForm>) query.list();
		if (list != null && list.size() > 0) {
			for(OrderForm orderForm : list) {
				System.out.println(orderForm.getId() + "\t" + orderForm.getStatus() + "\t" + orderForm.getTradeDate() +"\t" + orderForm.getAmount());
			}
		}
	}

}
