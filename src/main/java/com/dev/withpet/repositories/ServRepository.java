package com.dev.withpet.repositories;


import java.util.List;

import com.dev.withpet.domain.Serv;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;

public interface ServRepository {

	Integer create(Integer id, String name, Double price, String unit, Integer piority, String description, Integer catId) throws EtBadRequestException;
	Serv findById(Integer id) throws EtResourceNotFoundException;
	List<Serv> findAll();
	void update(Integer id , Serv services) throws EtBadRequestException;
	
	
}
