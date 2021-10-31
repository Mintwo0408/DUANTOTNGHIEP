package com.dev.withpet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.withpet.domain.Product;
import com.dev.withpet.exceptions.EtAuthException;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;
import com.dev.withpet.repositories.ProductRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> fetchAllProduct(Integer catid) {
        return productRepository.findAll();
    }

    @Override
    public Product fetchProductById(Integer id, Integer catId) throws EtResourceNotFoundException {
        return productRepository.findById(id, catId);
    }

    @Override
	public Product addProduct(Integer catid, String name, String brand, String description, 
			Integer discount, String unit, Boolean available, Timestamp created, Timestamp updated, Boolean deleted, Integer uid, Integer quantity, String price, String img)
			throws EtAuthException {
		Integer id = productRepository.create(catid, name, brand, description,discount,unit, available,created,updated,deleted,uid,quantity,price,img);
        return productRepository.findById(id, catid);
	}


	@Override
    public void updateProduct(Integer id, Product product) throws EtBadRequestException {
    	productRepository.update(id, product);
    }

    @Override
    public void removeProduct(Integer id, Integer catid ) throws EtResourceNotFoundException {
    	productRepository.removeById(id,  catid);
    }

	@Override
	public List<Product> findAll() {
	
		return productRepository.findAll();
	}

	
	
	}




