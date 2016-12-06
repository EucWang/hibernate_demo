package cn.wxn.demo.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactoryUtil {
	private SessionFactory sessionFactory;
	private Session openSession;
	private Transaction transaction;

	private SessionFactoryUtil(){
		createSessionFactory();
	}
	
	private static SessionFactoryUtil sessionFactoryUtil;
	
	public static SessionFactoryUtil  getInstance(){
		if (sessionFactoryUtil == null) {
			sessionFactoryUtil = new SessionFactoryUtil();
		}
		return sessionFactoryUtil;
	}
	
	
	public Session getSession() {
		if (sessionFactory == null) {
			createSessionFactory();			
		}
		openSession = sessionFactory.openSession();
		return openSession;
	}

	private void createSessionFactory() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception ex) {
			ex.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
 
	public void destory() {
		transaction.commit();
		openSession.close();
		sessionFactory.close();
	}
}
