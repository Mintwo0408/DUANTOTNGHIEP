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
@Table(name="pet")
public class Pet implements Serializable{
	@Id
	Integer petid;
	Timestamp added;
	String breed;
	String img;
	String kind;
	String name;
	Integer uid;
	Timestamp updated;
	String birth;
}
