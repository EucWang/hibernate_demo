package cn.wxn.demo.entitu;

import java.util.Date;
import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.mapping.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.wxn.demo.entity.Commodity;
import cn.wxn.demo.entity.Customer;
import cn.wxn.demo.entity.OrderForm;
import cn.wxn.demo.entity.Seller;

public class DemoTest {

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
	
	
	@Test
	public void addSeller(){
		Seller seller1 = createSeller("A服装店", "中国北京xx区", "经营各式服装", "5", "13000000000", "www.a.com");
		Seller seller2 = createSeller("B服装店", "中国浙江杭州xx区", "经营各类数码电子产品", "4", "15800000000", "www.b.com");
		Seller seller3 = createSeller("C电器店", "中国广东深圳市xx区", "经营各类家电", "4", "13012341234", "www.c.com");
		Seller seller4 = createSeller("D书店", "中国陕西西安市xx区", "经营各类实体书与电子产品", "5", "18600000000", "www.d.com");
		
		Customer customer1 = createCustomer("张三", "中国上海xx区xx路", 25, "热爱open", "zhangsan@feezu.cn", "男", "13715275211");
		Customer customer2 = createCustomer("李四", "中国北京xx区xx路", 20, "酷爱网购", "lisi@126.com", "女", "15715275211");
		Customer customer3 = createCustomer("王五", "中国广东深圳xx区xx路", 35, "很懒,啥也没", "wangwu@qq.com", "女", "18615275211");
		
		openSession.save(seller1);
		openSession.save(seller2);
		openSession.save(seller3);
		openSession.save(seller4);
		
	}
	
	private Seller createSeller(String name, String address, String business, String star, String tel, String wesite){
		Seller seller = new Seller();
		seller.setName(name);
		seller.setAddress(address);
		seller.setBusiness(business);
		seller.setCommodities(new HashSet<Commodity>());
		seller.setStar(star);
		seller.setTel(tel);
		seller.setWesite(wesite);
		return seller;
	}
	
	
	private Customer createCustomer(String name, String address, Integer age, String description, String email, String gender, String tel){
		Customer customer = new Customer();
		customer.setName(name);
		customer.setAddress(address);
		customer.setAge(age);
		customer.setBirthday(new Date());
		customer.setDescription(description);
		customer.setEmail(email);
		customer.setGender(gender);
		customer.setOrderForms(new HashSet<OrderForm>());
		customer.setTel(tel);
		return customer;
	}
}
