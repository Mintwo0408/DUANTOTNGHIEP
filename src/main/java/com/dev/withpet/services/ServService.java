package com.dev.withpet.services;

import java.util.List;

import com.dev.withpet.domain.Serv;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;

public interface ServService {

	Serv addServ(Integer id, String name, Double price, String 
			unit, Integer piority, String description, Integer catId) throws EtBadRequestException;
	
	Serv fetchServiceById(Integer id)throws EtResourceNotFoundException;

	List<Serv> findAll();
	
	void updateService(Integer id, Serv serv) throws EtBadRequestException;
}
