package com.dev.withpet.repositories;


import java.sql.Timestamp;
import java.util.List;

import com.dev.withpet.domain.Order;
import com.dev.withpet.exceptions.EtAuthException;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;




public interface OrderReponsitory {

	Integer create(Integer id, Integer uid, String address, Timestamp created, Timestamp updated, Integer staid) throws EtAuthException;

    Order findById(Integer id, Integer uid) throws EtAuthException;

    void update(Integer id, Integer uid, String address, Timestamp created, Timestamp updated, Integer staid, Order order) throws EtBadRequestException;
    void removeById(Integer uid, Integer id, Integer staid) throws EtResourceNotFoundException;

	List<Order> findAll(Integer userId, Integer id);

}
