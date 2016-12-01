package cn.wxn.demo.entity;

import java.io.Serializable;
import java.util.Set;

public class Commodity implements Serializable {

	private static final long serialVersionUID = -5275085437560811823L;

	private Long  id;
	private String name;
	private Double price;
	private String util;
	private String category;
	private String description;

	private Seller seller;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getUtil() {
		return util;
	}
	public void setUtil(String util) {
		this.util = util;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Commodity [id=" + id + ", name=" + name + ", price=" + price + ", util=" + util + ", category="
				+ category + ", description=" + description + ", seller=" + seller + "]";
	}
	
	
	
}
