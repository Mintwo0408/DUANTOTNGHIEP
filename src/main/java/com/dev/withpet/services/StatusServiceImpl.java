package com.dev.withpet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.withpet.domain.Status;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;
import com.dev.withpet.repositories.StatusRepository;

import java.util.List;

@Service
@Transactional
public class StatusServiceImpl implements StatusService {

    @Autowired
    StatusRepository statusRepository;

    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }



//    Lỗi fix chưa xong
    @Override
    public Status addStatus(String name) throws EtBadRequestException {
		int id  = statusRepository.create(name);
        return statusRepository.findById(id);
    }
 
	@Override
	public void updateStatus(Integer id, Status status) throws EtBadRequestException {
    	statusRepository.update(id, status);
    }

    public void removeCategoryWithAllTransactions(Integer id) throws EtResourceNotFoundException {
        this.fetchStatusById(id);
        statusRepository.removeById(id);
    }

	public void delete(Integer id) throws EtResourceNotFoundException {
	        this.delete(id);
	        statusRepository.delete(id);
		
	}

	@Override
	public Status fetchStatusById(Integer id) throws EtResourceNotFoundException {
		// TODO Auto-generated method stub
		return statusRepository.findById(id);
	}




}
