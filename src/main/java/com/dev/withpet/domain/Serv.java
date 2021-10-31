package com.dev.withpet.domain;



public class Serv {

	Integer id;
	String name;
	Double price;
	String unit;
	public Serv(Integer id, String name, Double price, String unit, Integer piority, String description,
			Boolean deleted, Integer catId) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.piority = piority;
		this.description = description;
		this.deleted = deleted;
		this.catId = catId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getPiority() {
		return piority;
	}
	public void setPiority(Integer piority) {
		this.piority = piority;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public Integer getCatId() {
		return catId;
	}
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	Integer piority;
	String description;
	Boolean deleted;
	Integer catId;
}
