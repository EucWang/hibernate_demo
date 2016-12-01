package cn.wxn.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class OrderForm implements Serializable {

	private static final long serialVersionUID = -8370813382942139439L;

	private Long id;
	private Date tradeDate;
	private String status;
	private Double amount;
	
	private Customer customer;
	private Set<OrderItem> orderItems;
		
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "OrderForm [id=" + id + ", customer=" + customer + ", tradeDate=" + tradeDate + ", status=" + status
				+ ", amount=" + amount + "]";
	}
	
	
}
