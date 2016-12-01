package cn.wxn.demo.entity;

import java.io.Serializable; 
import java.util.Set;

public class OrderItem  implements Serializable {

	private static final long serialVersionUID = -3624713617381954727L;

	private Long id;
	private Double discount;
	private Double actprice;
	private Double amount;
	
	private OrderForm orderForm;
	private Set<Commodity> commodities;
	
	public Set<Commodity> getCommodities() {
		return commodities;
	}
	public void setCommodities(Set<Commodity> commodities) {
		this.commodities = commodities;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public OrderForm getOrderForm() {
		return orderForm;
	}
	public void setOrderForm(OrderForm orderForm) {
		this.orderForm = orderForm;
	}
 
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getActprice() {
		return actprice;
	}
	public void setActprice(Double actprice) {
		this.actprice = actprice;
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
		return "OrderItem [id=" + id + ", orderForm=" + orderForm + ", comodities=" + commodities + ", discount="
				+ discount + ", actprice=" + actprice + ", amount=" + amount + "]";
	}
	
	
	
}
