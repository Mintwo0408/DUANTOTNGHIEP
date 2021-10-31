package com.dev.withpet.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product implements Serializable{
	@Id
    Integer id;
	Integer catid;
	String name;
	String brand;
	String description;
	Integer discount;
	String unit;
	Boolean available;
	Timestamp created;
	Timestamp updated;
	Boolean deleted;
	String img;
	Integer uid;
	Integer quantity;
    String price;
}
