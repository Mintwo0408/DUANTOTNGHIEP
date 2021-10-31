package com.dev.withpet.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.dev.withpet.domain.Product;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static final String SQL_FIND_ALL = "SELECT id, catid, name, brand, description,  discount, unit, available, created, deleted, updated, uid, quantity, price, img "
    		+ "FROM public.product";
    private static final String SQL_FIND_BY_ID = "SELECT id, catid, name, brand, description,  discount, unit, available, created, deleted, updated, uid, quantity, price, img  " +
            "FROM public.product WHERE id = ? AND catid = ? ";
    private static final String SQL_CREATE = "INSERT INTO public.Product "
    		+ "(id,catid, name, brand, description, discount, unit, available, created, updated, deleted,  uid, quantity, price, img ) "
    		+ "VALUES(nextval('\"Product_ID_seq\"'::regclass),?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE public.Product SET "
    		+ "id = ?, catid = ?, name = ?, brand = ?, description = ?, discount = ?, unit = ?, available = ?, created = ?, updated = ?, deleted = ?,  uid = ?, quantity = ?,  price = ?, img = ?" 
    		+ " WHERE id = ? ";
    private static final String SQL_DELETE = "DELETE FROM public.product WHERE id = ? ";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{}, productRowMapper);
    }

    @Override
    public Product findById(Integer id, Integer catid) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{id, catid}, productRowMapper);
        }catch (Exception e) {
        	throw e;
//            throw new EtResourceNotFoundException("Product not found");
        }
    }

    @Override
    public Integer create(Integer catid, String name, String brand, String description, Integer discount,String unit, Boolean available,
			Timestamp created, Timestamp updated, Boolean deleted, Integer uid, Integer quantity, String price, String img ) throws EtBadRequestException {
        try {
        	java.util.Date date = new java.util.Date();
        	Timestamp ts = new Timestamp(date.getTime());
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, catid);
                ps.setString(2, name);
                ps.setString(3, brand);
                ps.setString(4, description);
                ps.setInt(5, discount);
                ps.setString(6, unit);
                ps.setBoolean(7, available);
                ps.setTimestamp(8, ts);
                ps.setTimestamp(9, ts);
                ps.setBoolean(10, deleted);
                ps.setInt(11, uid);
                ps.setInt(12, quantity);
                ps.setString(13, price);
                ps.setString(14, img);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("id");
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void update(Integer id, Product product) throws EtBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{product.getName(), product.getBrand(), product.getDescription(), product.getPrice(),product.getDiscount(),product.getUnit(),product.getAvailable(), product.getQuantity(),product.getImg(), product.getUid(), product.getId(), product.getCatid(),id});
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void removeById(Integer id, Integer catid) throws EtResourceNotFoundException {
        int count = jdbcTemplate.update(SQL_DELETE, new Object[]{id, catid});
        if(count == 0)
            throw new EtResourceNotFoundException("Product not found");
    }

    private RowMapper<Product> productRowMapper = ((rs, rowNum) -> {
        return new Product(
        		rs.getInt("ID"),
        		rs.getInt("CATID"),
                rs.getString("NAME"),
                rs.getString("BRAND"),
                rs.getString("DESCRIPTION"),
                rs.getInt("DISCOUNT"),
                rs.getString("UNIT"),
                rs.getBoolean("AVAILABLE"),
                rs.getTimestamp("CREATED"),
                rs.getTimestamp("UPDATED"),
                rs.getBoolean("DELETED"),
                rs.getString("PRICE"),
                rs.getInt("QUANTITY"),
                rs.getInt("UID"),
                rs.getString("IMG")
        		);
    });

	@Override
	public void delete(Integer id) {
		this.delete(id);
        jdbcTemplate.update(SQL_DELETE, new Object[]{id});
	}

	
	}

