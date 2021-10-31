package com.dev.withpet.services;

import java.util.List;

import com.dev.withpet.domain.Status;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;

public interface StatusService {

    List<Status> findAll();

    Status fetchStatusById(Integer id) throws EtResourceNotFoundException;

    Status addStatus(String name) throws EtBadRequestException;

   
    void delete(Integer id)throws EtBadRequestException;



	void updateStatus(Integer id, Status status) throws EtBadRequestException;
		
	}


