package com.dev.withpet.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.dev.withpet.domain.Category;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private static final String SQL_FIND_ALL = "SELECT id, name " + "FROM public.categories";
    private static final String SQL_FIND_BY_ID ="SELECT id, name " +
            "FROM  public.categories WHERE ID = ?";
    private static final String SQL_CREATE = "INSERT INTO categories (name) VALUES(?)";
    private static final String SQL_UPDATE = "UPDATE categories SET NAME = ? " +
            "WHERE ID = ? ";

    private static final String SQL_DELETE_ALL_CATEGORY = "DELETE FROM public.categories WHERE id = ?";
	private static final String SQL_DELETE_CATEGORY = "DELETE FROM public.categories ";

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Category> findAll() throws EtResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{}, categoryRowMapper);
    }

    @Override
    public Category findById(Integer id) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{id}, categoryRowMapper);
        }catch (Exception e) {
        	throw e;
//            throw new EtResourceNotFoundException("Category not found");
        }
    }

    @Override
    public Integer create(String name) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE,Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("id");
        }catch (Exception e) {
        	throw e;
//            throw new EtBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer id, String name , Category category ) throws EtBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{category.getId(), category.getName()});
        }catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }

    public void removeById(Integer id) {
        this.removeAllCatTransactions(id);
        jdbcTemplate.update(SQL_DELETE_CATEGORY, new Object[]{id});
    }

    private void removeAllCatTransactions(Integer id) {
        jdbcTemplate.update(SQL_DELETE_ALL_CATEGORY, new Object[]{id});
    }

    private RowMapper<Category> categoryRowMapper = ((rs, rowNum) -> {
        return new Category
        		(rs.getInt("id"),
                rs.getString("name"));
               
    });

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}



}

