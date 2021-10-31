package com.dev.withpet.repositories;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.dev.withpet.domain.User;
import com.dev.withpet.exceptions.EtAuthException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;



@Repository
public class UserRepositoryImpl implements UserRepository {
	private static final String SQL_COUNT_BY_PHONE = "SELECT COUNT(*) FROM public.User WHERE phone = ?";
    private static final String SQL_CREATE = 
    		"INSERT INTO public.User(updated, sex, phone , name, img, created, address, password, role) "
    		+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_FIND_BY_ID = 
    		"SELECT updated, sex, phone , name, img, created, address, password, id, role " +
            "FROM public.User WHERE  id = ?";
    private static final String SQL_FIND_BY_PHONE = 
    		"SELECT updated, sex, phone , name, img, created, address, password, id, role " +
            "FROM public.User WHERE phone  = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(Timestamp updated, Boolean sex, String phone, String name, String img, Timestamp created,
			String address, String password, Boolean role) throws EtAuthException {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        try {
        	java.util.Date date = new java.util.Date();
        	Timestamp tt = new Timestamp(date.getTime());
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setTimestamp(1, tt);
                ps.setBoolean(2, true);
                ps.setString(3, phone);
                ps.setString(4, name);
                ps.setString(5, img);
                ps.setTimestamp(6,tt);
                ps.setString(7, address);
                ps.setString(8, hashedPassword);
                ps.setBoolean(9, true);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("id");
        }catch (Exception e) {
            throw new EtAuthException("Invalid details. Failed to create account");
        }
    }

    @Override
    public User findByPhoneAndPassword(String phone, String password) throws EtAuthException {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_PHONE, new Object[]{phone}, userRowMapper);
            if(!BCrypt.checkpw(password, user.getPassword()))
                throw new EtAuthException("Invalid phone/password");
            return user;
        }catch (EmptyResultDataAccessException e) {
            throw new EtAuthException("Invalid phone/password");
        }
    }

    @Override
    public Integer getCountByPhone(String phone) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_PHONE, new Object[]{phone}, Integer.class);
    }

    @Override
    public User findById(Integer id) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{id}, userRowMapper);
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(rs.getInt("ID"),
                        rs.getTimestamp("UPDATED"),
				        rs.getBoolean("SEX"),
				        rs.getString("PHONE"),
				        rs.getString("NAME"),
				        rs.getString("IMG"),
				        rs.getTimestamp("CREATED"),
				        rs.getString("ADDRESS"),
				        rs.getString("PASSWORD"),
				        rs.getBoolean("ROLE"));
    });
}
