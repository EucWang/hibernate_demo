package cn.wxn.demo.entity;

import java.io.Serializable;
import java.util.Set;

public class Seller implements Serializable {

	private static final long serialVersionUID = -5334590898437622525L;

	private Long id;  //主键
	
	private String name; //名称
	private String tel;  //电话
	private String address; //地址
	private String wesite; //商家网址
	private String star;   //商家星级
	
	private String business; //经营范围
	
	private Set<Commodity> commodities;

	public Set<Commodity> getCommodities() {
		return commodities;
	}

	public void setCommodities(Set<Commodity> commodities) {
		this.commodities = commodities;
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", name=" + name + ", tel=" + tel + ", address=" + address + ", wesite=" + wesite
				+ ", star=" + star + ", business=" + business + "]";
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

	public String getWesite() {
		return wesite;
	}

	public void setWesite(String wesite) {
		this.wesite = wesite;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
