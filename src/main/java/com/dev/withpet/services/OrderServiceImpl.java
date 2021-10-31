package com.dev.withpet.services;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.withpet.domain.Order;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;
import com.dev.withpet.repositories.OrderReponsitory;



@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	@Autowired
    OrderReponsitory orderRepository;
	
	@Override
	public Order fetchOrderById(Integer id, Integer uid, Integer staid) throws EtResourceNotFoundException {
        return orderRepository.findById(id,uid);
	}

	public Order addOrder(Integer id, Integer uid, String address, Timestamp created, Timestamp updated, Integer staid)
			throws EtBadRequestException {
		@SuppressWarnings("unused")
		int categoryId  = orderRepository.create(id,uid,address,created,updated,staid);
        return orderRepository.findById(id,uid);
	}



	public void updatOrder(Integer id, Integer uid, String address, Timestamp created, Timestamp updated, Integer staid,
			Order order) throws EtBadRequestException {
	
		orderRepository.update(id, uid, address,created,updated,staid, order);
	}


	public void removeOrder(Integer userId, Integer id, Integer staid) throws EtResourceNotFoundException {
		
		
		orderRepository.removeById(userId, id, staid);
	}

	@Override
	public List<Order> findAll(Integer userId, Integer id) {
	
		return orderRepository.findAll(userId, id );
	}



	@Override
	public void removeById(Integer userId, Integer id, Integer staid) throws EtResourceNotFoundException {
	
		orderRepository.removeById(userId, id, staid);
	}

	@Override
	public void updateOrder(Integer id, Integer uid, String address, Timestamp created, Timestamp updated,
			Integer staid, Order order) throws EtBadRequestException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order addOrder(int userId, String address, Timestamp created, Timestamp updated, String staid)
			throws EtBadRequestException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
