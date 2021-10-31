package com.dev.withpet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.withpet.domain.Category;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;
import com.dev.withpet.repositories.CategoryRepository;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category fetchCategoryById(Integer id) throws EtResourceNotFoundException {
        return categoryRepository.findById(id);
    }

//    Lỗi fix chưa xong
    @Override
    public Category addCategory(String name) throws EtBadRequestException {
		int id  = categoryRepository.create(name);
        return categoryRepository.findById(id);
    }

    @Override
    public void updateCategory(Integer id,Category category) throws EtBadRequestException {
        categoryRepository.update(id,null, category);
    }

    public void removeCategoryWithAllTransactions(Integer id) throws EtResourceNotFoundException {
        this.fetchCategoryById(id);
        categoryRepository.removeById(id);
    }

	public void delete(Integer id) throws EtResourceNotFoundException {
	        this.delete(id);
	        categoryRepository.delete(id);
		
	}
}
