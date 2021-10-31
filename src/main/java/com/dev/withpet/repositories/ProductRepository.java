package com.dev.withpet.repositories;

import java.sql.Timestamp;
import java.util.List;

import com.dev.withpet.domain.Product;

import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;

public interface ProductRepository {

    List<Product> findAll();

    Product findById(Integer id, Integer catid) throws EtResourceNotFoundException;

    Integer create(Integer catid, String name, String brand, String description, Integer discount,String unit,Boolean available, Timestamp created, Timestamp updated, Boolean deleted, Integer uid, Integer quantity, String price, String img) throws EtBadRequestException;;

    void delete(Integer id)  throws EtBadRequestException;

	void update(Integer id,  Product product)  throws EtBadRequestException;

	void removeById(Integer id, Integer catid)  throws EtResourceNotFoundException;
}
