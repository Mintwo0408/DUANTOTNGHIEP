package com.dev.withpet.services;

import java.util.List;

import com.dev.withpet.domain.Category;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;

public interface CategoryService {

    List<Category> findAll();

    Category fetchCategoryById(Integer id) throws EtResourceNotFoundException;

    Category addCategory(String name) throws EtBadRequestException;

    void updateCategory(Integer id, Category category) throws EtBadRequestException;
    void delete(Integer id)throws EtBadRequestException;
		
	}


