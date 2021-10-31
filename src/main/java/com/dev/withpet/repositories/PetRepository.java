package com.dev.withpet.repositories;

import java.sql.Timestamp;

import java.util.List;

import com.dev.withpet.domain.Pet;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;

public interface PetRepository {

	List<Pet> findAll(Integer uid) throws EtResourceNotFoundException;

    Pet findById(Integer petid, Integer uid) throws EtResourceNotFoundException;

    Integer create(Timestamp added, String breed,String img, String kind, String name, Integer uid,Timestamp updated , String birth) throws EtBadRequestException;

    void update( Integer petid, Pet pet) throws EtBadRequestException;
    void removeById(Integer uid);
}
