package com.dev.withpet.repositories;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.dev.withpet.domain.Order;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;




@Repository
public class OrderReponsitoryImpl implements OrderReponsitory{

	private static final String SQL_FIND_BY_ID ="SELECT id, uid, address, created, updated, staid " +
            "FROM  public.order WHERE id = ?";
	private static final String SQL_CREATE = "INSERT INTO public.order (id,uid,address,created,updated,staid) VALUES(NEXTIVAL('ORDER_SEQ'),?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE public.order SET id = ?, uid = ?, address = ?, created = ? AND updated = ? AND staid = ?";
	private static final String SQL_DELETE = "DELETE FROM public.order WHERE id = ? AND uid = ? AND staid = ?";
	private static final String SQL_FIND_ALL = "SELECT id, uid, address, created, updated, staid " +
            "FROM  public.order WHERE id = ? and uid = ?";
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	public Integer create(Integer id, Integer uid, String address, Timestamp created, Timestamp updated, Integer staid)
			throws EtBadRequestException {
		        try {
		            KeyHolder keyHolder = new GeneratedKeyHolder();
		            jdbcTemplate.update(connection -> {
		                PreparedStatement ps = connection.prepareStatement(SQL_CREATE,Statement.RETURN_GENERATED_KEYS);
		                ps.setInt(1, id);
		                ps.setInt(2,uid);
		                ps.setString(3, address);
		                ps.setTimestamp(4, created);
		                ps.setTimestamp(5, updated);
		                ps.setInt(6, staid);
		                return ps;
		            }, keyHolder);
		            return (Integer) keyHolder.getKeys().get("id");
		        }catch (Exception e) {
		            throw new EtBadRequestException("Invalid request");
		        }
		    }

	@Override
	public Order findById(Integer id, Integer uid) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{id}, orderRowMapper1);
        }catch (Exception e) {
            throw new EtResourceNotFoundException("Order not found");
        }
    }

	public void update(Integer id, Integer uid, String address, Timestamp created, Timestamp updated, Integer staid, Order order)
			throws EtBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{order.getAddress(), order.getCreated(),order.getUpdated(), id, uid , staid});
        }catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }
	
	private RowMapper<Order> orderRowMapper1 = ((rs, rowNum) -> {
        return new Order(
        		rs.getInt("ID"),
        		rs.getInt("UID"),
                rs.getString("ADDRESS"),
                rs.getTimestamp("CREATED"),
                rs.getTimestamp("UPDATED"),
                rs.getInt("STAID"));
    });



	@Override
	public void removeById(Integer uid, Integer id, Integer staid) throws EtResourceNotFoundException {
		int count = jdbcTemplate.update(SQL_DELETE, new Object[]{id, uid, staid});
        if(count == 0)
            throw new EtResourceNotFoundException("Transaction not found");
    }

    private RowMapper<Order> orderRowMapper = ((rs, rowNum) -> {
        return new Order(
        		rs.getInt("ID"),
                rs.getInt("UID"),
                rs.getString("ADDRESS"),
                rs.getTimestamp("CREATED"),
                rs.getTimestamp("UPDATED"),
                rs.getInt("STAID"));
    });



	@Override
	public List<Order> findAll(Integer userId, Integer id) {
		// TODO Auto-generated method stub
		 return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{userId, id}, orderRowMapper);
	}
		
}

