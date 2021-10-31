package com.dev.withpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.withpet.domain.Serv;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;
import com.dev.withpet.repositories.ServRepository;

@Service
@Transactional
public class ServServiceImlp implements ServService{

	@Autowired
	ServRepository servRepository;
	@Override
	public Serv addServ(Integer id, String name, Double price, String unit, Integer piority, String description, Integer catId) throws EtBadRequestException {
		int servId = servRepository.create(id, name, price, unit, piority, description, catId);
		return servRepository.findById(servId);
	}
	@Override
	public Serv fetchServiceById(Integer id) throws EtResourceNotFoundException {
		// TODO Auto-generated method stub
		return  servRepository.findById(id);
	}
	@Override
	public List<Serv> findAll() {
		return servRepository.findAll();
	}
	@Override
	public void updateService(Integer id, Serv serv) throws EtBadRequestException {
     
		servRepository.update(id , serv);
		
	}

}
