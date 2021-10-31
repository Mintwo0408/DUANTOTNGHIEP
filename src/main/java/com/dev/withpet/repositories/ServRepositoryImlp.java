package com.dev.withpet.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.dev.withpet.domain.Product;
import com.dev.withpet.domain.Serv;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;

@Repository
public class ServRepositoryImlp implements ServRepository{
	
	private static final String SQL_CREATE =
    		"INSERT INTO public.services(\r\n"
    		+ "	id, name, price, unit, piority, description, deleted, catid)\r\n"
    		+ "	VALUES (nextval('\"Services_ID_seq\"'::regclass),?,?,?,?,?,false,?)";
	
	private static final String SQL_FIND_BY_ID =
			"SELECT id,name,price,unit,piority,description,deleted,catid FROM public.services WHERE id = ? ";
    private static final String SQL_FIND_ALL = "SELECT id,name,price,unit,piority,description,deleted,catid "
    		+ "FROM public.services";
    private static final String SQL_UPDATE = "UPDATE public services SET name = ? , price = ? , unit = ?, piority = ?, description = ?, deleted = ? WHERE id = ?";
	private RowMapper<Serv> servRowMapper = ((rs, rowNum) -> {
        return new Serv(
        		rs.getInt(1),
        		rs.getString(2),
        		rs.getDouble(3),
        		rs.getString(4),
        		rs.getInt(5),
        		rs.getString(6),
        		rs.getBoolean(7),
        		rs.getInt(8)
        		);
    });
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	@Override
	public List<Serv> findAll() {
	        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{}, servRowMapper);
	    }
	@Override
	public Integer create(Integer id, String name, Double price, String unit, Integer piority, String description, Integer catId) throws EtBadRequestException {
		try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                ps.setDouble(2, price);
                ps.setString(3, unit);
                ps.setInt(4, piority);
                ps.setString(5, description);
//                ps.setBoolean(6, false);
                ps.setInt(6, catId);
                return ps;
            }, keyHolder);
			return (Integer) keyHolder.getKeys().get("id");
        }catch (Exception e) {
        	throw e;
//            throw new EtBadRequestException("Invalid request");
        }
	}

	@Override
	public Serv findById(Integer id) throws EtResourceNotFoundException {
		try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{id}, servRowMapper);
        }catch (Exception e) {
        	throw e;
//            throw new EtResourceNotFoundException("Pet not found");
        }
		
	}
	@Override
	public void update(Integer id, Serv services) throws EtBadRequestException {
		try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{services.getName(), services.getPrice(), services.getUnit(), services.getPiority(),services.getDescription(),id});
        }catch (Exception e) {
        	throw e;
//            throw new EtBadRequestException("Invalid request");
        }
		
	}
	
}
