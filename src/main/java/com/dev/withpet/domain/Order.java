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
@Table(name="order")
public class Order implements Serializable{
	@Id
	Integer id;
	Integer uid;
	String address;
	Timestamp created;
	Timestamp updated;
	Integer staid;
}