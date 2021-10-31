package com.dev.withpet.services;



import java.sql.Timestamp;
import java.util.List;

import com.dev.withpet.domain.Order;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;




public interface OrderService {

	  List<Order> findAll(Integer userId, Integer id);
	Order fetchOrderById(Integer id, Integer uid, Integer staid) throws EtResourceNotFoundException;



    void updateOrder(Integer id, Integer uid, String address, Timestamp created, Timestamp updated, Integer staid, Order order) throws EtBadRequestException;

    void removeById(Integer userId, Integer id, Integer staid) throws EtResourceNotFoundException;
	Order addOrder(int userId, String address, Timestamp created, Timestamp updated, String staid) throws EtBadRequestException;
  



	
	

}
