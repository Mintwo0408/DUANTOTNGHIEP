package com.dev.withpet.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import com.dev.withpet.domain.Status;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class StatusRepositoryImpl implements StatusRepository {

    private static final String SQL_FIND_ALL = "SELECT id, name " + "FROM public.status";
    private static final String SQL_FIND_BY_ID ="SELECT id, name " +
            "FROM  public.status WHERE id = ?";
    private static final String SQL_CREATE = "INSERT INTO public.status ( name) VALUES(?)";
    private static final String SQL_UPDATE = "UPDATE public.status SET NAME = ? " +
            "WHERE ID = ? ";

    private static final String SQL_DELETE_ALL= "DELETE FROM public.status WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM public.status ";

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Status> findAll() throws EtResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{}, statusRowMapper);
    }

    @Override
    public Status findById(Integer id) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{id}, statusRowMapper);
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

    public void update(Integer id, String name , Status status ) throws EtBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{status.getId(), status.getName()});
        }catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }

    public void removeById(Integer id) {
        this.removeAllCatTransactions(id);
        jdbcTemplate.update(SQL_DELETE, new Object[]{id});
    }

    private void removeAllCatTransactions(Integer id) {
        jdbcTemplate.update(SQL_DELETE_ALL, new Object[]{id});
    }

    private RowMapper<Status> statusRowMapper = ((rs, rowNum) -> {
        return new Status
        		(rs.getInt("id"),
                rs.getString("name"));
               
    });

	@Override
	public void delete(Integer id) throws EtBadRequestException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Integer id, Status status) {
		// TODO Auto-generated method stub
		
	}

	



}

