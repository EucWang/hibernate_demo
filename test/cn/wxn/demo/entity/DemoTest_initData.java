package cn.wxn.demo.entity;

import java.util.Date;
import java.util.HashSet;

import javax.net.ssl.SSLEngineResult.Status;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.wxn.demo.entity.Commodity;
import cn.wxn.demo.entity.Customer;
import cn.wxn.demo.entity.OrderForm;
import cn.wxn.demo.entity.OrderItem;
import cn.wxn.demo.entity.Seller;

public class DemoTest_initData {

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
	 * 基本数据库数据导入
	 */
	@Test
	public void initData(){
		Seller seller1 = createSeller("A服装店", "中国北京xx区", "经营各式服装", "5", "13000000000", "www.a.com");
		Seller seller2 = createSeller("B服装店", "中国浙江杭州xx区", "经营各类数码电子产品", "4", "15800000000", "www.b.com");
		Seller seller3 = createSeller("C电器店", "中国广东深圳市xx区", "经营各类家电", "4", "13012341234", "www.c.com");
		Seller seller4 = createSeller("D书店", "中国陕西西安市xx区", "经营各类实体书与电子产品", "5", "18600000000", "www.d.com");
		
		Customer customer1 = createCustomer("张三", "中国上海xx区xx路", 25, "热爱open", "zhangsan@feezu.cn", "男", "13715275211");
		Customer customer2 = createCustomer("李四", "中国北京xx区xx路", 20, "酷爱网购", "lisi@126.com", "女", "15715275211");
		Customer customer3 = createCustomer("王五", "中国广东深圳xx区xx路", 35, "很懒,啥也没", "wangwu@qq.com", "女", "18615275211");
		
		Commodity commodity1 = createCommodity("童装", "中式设计儿童服装", "中式童装", 120D, seller1, "套");
		Commodity commodity2 = createCommodity("女装", "女士职业套装", "女士套装", 200D, seller1, "套");
		Commodity commodity3 = createCommodity("男装", "男士西服套装", "男士西服", 200D, seller1, "套");
		Commodity commodity4 = createCommodity("电脑", "双核笔记本电脑", "笔记本电脑", 4000D, seller2, "台");
		Commodity commodity5 = createCommodity("电脑周边", "1t移动硬盘", "移动硬盘", 400D, seller2, "块");
		Commodity commodity6 = createCommodity("电视", "4k高清液晶电视", "液晶电视", 5000D, seller3, "台");
		Commodity commodity7 = createCommodity("洗衣机", "滚筒洗衣机", "滚筒洗衣机", 4000D, seller3, "台");
		Commodity commodity8 = createCommodity("实体书", "介绍hibernate编程", "<<hibernate编程>>", 30D, seller4, "本");
		Commodity commodity9 = createCommodity("实体书", "介绍java编程核心", "<<java核心>>", 50D, seller4, "本");
		Commodity commodity10 = createCommodity("实体书", "经典文学", "<<基地与帝国>>", 20D, seller4, "本");
		
		OrderForm createOrderForm1 = createOrderForm(4400D, customer1, "已收货");
		OrderForm createOrderForm2 = createOrderForm(520D, customer2, "已收货");
		OrderForm createOrderForm3 = createOrderForm(9120D, customer3, "已付款");
		
		OrderItem orderItem1 = createOrderItem(4000D, 1D, 1D, createOrderForm1);
		OrderItem orderItem2 = createOrderItem(400D, 1D, 1D, createOrderForm1);
		OrderItem orderItem3 = createOrderItem(120D, 1D, 1D, createOrderForm2);
		OrderItem orderItem4 = createOrderItem(200D, 1D, 1D, createOrderForm2);
		OrderItem orderItem5 = createOrderItem(200D, 1D, 1D, createOrderForm2);
		OrderItem orderItem6 = createOrderItem(5000D, 1D, 1D, createOrderForm3);
		OrderItem orderItem7 = createOrderItem(4000D, 1D, 1D, createOrderForm3);
		OrderItem orderItem8 = createOrderItem(30D, 1D, 1D, createOrderForm3);
		OrderItem orderItem9 = createOrderItem(50D, 1D, 1D, createOrderForm3);
		OrderItem orderItem10 = createOrderItem(40D, 1D, 1D, createOrderForm3);
		
		orderItem1.getCommodities().add(commodity4);
		orderItem2.getCommodities().add(commodity5);
		orderItem3.getCommodities().add(commodity1);
		orderItem4.getCommodities().add(commodity2);
		orderItem5.getCommodities().add(commodity3);
		orderItem6.getCommodities().add(commodity6);
		orderItem7.getCommodities().add(commodity7);
		orderItem8.getCommodities().add(commodity8);
		orderItem9.getCommodities().add(commodity9);
		orderItem10.getCommodities().add(commodity10);
		
		openSession.save(seller1);
		openSession.save(seller2);
		openSession.save(seller3);
		openSession.save(seller4);

		openSession.save(customer1);
		openSession.save(customer2);
		openSession.save(customer3);

		openSession.save(commodity1);
		openSession.save(commodity2);
		openSession.save(commodity3);
		openSession.save(commodity4);
		openSession.save(commodity5);
		openSession.save(commodity6);
		openSession.save(commodity7);
		openSession.save(commodity8);
		openSession.save(commodity9);
		openSession.save(commodity10);
		
		openSession.save(createOrderForm1);
		openSession.save(createOrderForm2);
		openSession.save(createOrderForm3);

		openSession.save(orderItem1);
		openSession.save(orderItem2);
		openSession.save(orderItem3);
		openSession.save(orderItem4);
		openSession.save(orderItem5);
		openSession.save(orderItem6);
		openSession.save(orderItem7);
		openSession.save(orderItem8);
		openSession.save(orderItem9);
		openSession.save(orderItem10);
		
	}
	
	private OrderItem createOrderItem(Double actprice, Double amount, Double discount, OrderForm orderForm){
		OrderItem orderItem = new OrderItem();
		orderItem.setActprice(actprice);
		orderItem.setAmount(amount);
		orderItem.setCommodities(new HashSet<Commodity>());
		orderItem.setDiscount(discount);
		orderItem.setOrderForm(orderForm);
		return orderItem;
	}
	
	private OrderForm createOrderForm(Double amount, Customer customer, String status){
		OrderForm orderForm = new OrderForm();
		orderForm.setAmount(amount);
		orderForm.setCustomer(customer);
		orderForm.setOrderItems(new HashSet<OrderItem>());
		orderForm.setStatus(status);
		orderForm.setTradeDate(new Date());
		return orderForm;
	}
	
	private Commodity createCommodity(String category, String description, String name, Double price, Seller seller, String util){
		Commodity commodity = new Commodity();
		
		commodity.setCategory(category);
		commodity.setDescription(description);
		commodity.setName(name);
		commodity.setOrderItems(new HashSet<OrderItem>());
		commodity.setPrice(price);
		commodity.setSeller(seller);
		commodity.setUtil(util);
		
		return commodity;
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
