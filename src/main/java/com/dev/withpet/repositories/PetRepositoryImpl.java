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

import com.dev.withpet.domain.Pet;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;

@Repository
public class PetRepositoryImpl implements PetRepository{

	private static final String SQL_FIND_ALL =
    		"SELECT petid,uid, name, birth , breed, kind, added, updated, img " +
            "FROM public.Pet WHERE uid = ?";
    private static final String SQL_CREATE =
    		"INSERT INTO public.Pet(petid, added, breed, img, kind, name, uid, updated , birth ) "
    		+ "VALUES(nextval('\"pet_petid_seq\"'::regclass),?, ?, ?, ?, ?, ?, ?, ?)";
//    "INSERT INTO public.pet ( petid, added, birth, breed, img, kind, name, uid, updated)"
//    		+"VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_FIND_BY_ID =
    		"SELECT * " +
            "FROM public.Pet WHERE petid = ? AND uid = ? ";
    private static final String SQL_UPDATE =
    		"UPDATE Pet SET name = ?, birth = ?,breed = ?, kind = ?, added = ?,updated = ?, img = ? WHERE petid = ?";
    
    private static final String SQL_DELETE_ALL_TRANSACTIONS = "DELETE FROM ET_TRANSACTIONS WHERE CATEGORY_ID = ?"; // chua sua
     
    @Autowired
    JdbcTemplate jdbcTemplate;

	@Override
	public List<Pet> findAll(Integer uid) {
		return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{uid},   petRowMapper);
	}

	@Override
	public Pet findById(Integer petid,Integer uid) throws EtResourceNotFoundException {
		try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{petid,uid}, petRowMapper);
        }catch (Exception e) {
        	throw e;
//            throw new EtResourceNotFoundException("Pet not found");
        }
	}

	@Override
	public Integer create(Timestamp added, String breed,String img, String kind, String name, Integer uid,Timestamp updated, String birth ) throws EtBadRequestException {
		try {
		 	java.util.Date date = new java.util.Date();
			Timestamp ts = new Timestamp(date.getTime());
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setTimestamp(1, ts);
                ps.setString(2, breed);
                ps.setString(3, img);
                ps.setString(4, kind);
                ps.setString(5, name);
                ps.setInt(6,uid);
                ps.setTimestamp(7, ts);
                ps.setString(8, birth);
                return ps;
            }, keyHolder);
			return (Integer) keyHolder.getKeys().get("petid");
        }catch (Exception e) {
        	throw e;
//            throw new EtBadRequestException("Invalid request");
        }
	}

	@Override
	public void update( Integer petid, Pet pet) throws EtBadRequestException {
		try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{pet.getName(), pet.getBreed(), pet.getKind(),pet.getAdded(),pet.getUpdated(),pet.getImg(),pet.getBirth(),petid});
        }catch (Exception e) {
        	throw e;
//            throw new EtBadRequestException("Invalid request");
        }
		
	}
	
	 private RowMapper<Pet> petRowMapper = ((rs, rowNum) -> {
	        return new Pet(
	        		rs.getInt("petid"),
	        		rs.getTimestamp("added"),
	                rs.getString("breed"),
	                rs.getString("img"),
	                rs.getString("kind"),
	                rs.getString("name"),
	        		rs.getInt("uid"),
	                rs.getTimestamp("updated"),
	                rs.getString("birth")
	            
	        		);
	    });

	@Override
	public void removeById(Integer uid) {
		jdbcTemplate.update(SQL_DELETE_ALL_TRANSACTIONS, new Object[]{uid});
		
	}
}
