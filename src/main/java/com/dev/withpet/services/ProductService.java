package com.dev.withpet.services;


import java.sql.Timestamp;
import java.util.List;

import com.dev.withpet.domain.Product;

import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;

public interface ProductService {

	List<Product> findAll();
    List<Product> fetchAllProduct(Integer catid);

    Product fetchProductById(Integer id, Integer catid) throws EtResourceNotFoundException;

    Product addProduct(Integer catid, String name, String brand, String description, Integer discount,String unit, Boolean available, Timestamp created, Timestamp updated, Boolean deleted,  Integer uid, Integer quantity, String price, String img) throws EtBadRequestException;

    void updateProduct(Integer id, Product product) throws EtBadRequestException;

    void removeProduct(Integer id, Integer catid ) throws EtResourceNotFoundException;



	
	
		
	}
		
		
	


	

