package cn.wxn.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Customer implements Serializable {

	private static final long serialVersionUID = -7897455159781398736L;

	
	private Long id;
	
	private String name;
	private String tel;
	private String address;
	private String email;
	private String gender;
	private String description;
	private Integer age;
	private Date birthday;
	
	private Set<OrderForm> orderForms;
	
	public Set<OrderForm> getOrderForms() {
		return orderForms;
	}
	public void setOrderForms(Set<OrderForm> orderForms) {
		this.orderForms = orderForms;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", tel=" + tel + ", address=" + address + ", email=" + email
				+ ", gender=" + gender + ", description=" + description + ", age=" + age + ", birthday=" + birthday
				+ "]";
	}
	
	
	
}
