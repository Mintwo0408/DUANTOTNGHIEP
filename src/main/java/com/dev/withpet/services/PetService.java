package com.dev.withpet.services;

import java.sql.Timestamp;

import java.util.List;

import com.dev.withpet.domain.Pet;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;

public interface PetService {
	List<Pet> fetchAllPet(Integer uid);

    Pet fetchPetById(Integer petid, Integer uid) throws EtResourceNotFoundException;

    Pet addPet(Timestamp added, String breed,String img, String kind, String name, Integer uid,Timestamp updated , String birth) throws EtBadRequestException;

    void updatePet(Integer uid,Pet pet) throws EtBadRequestException;
    
    void removePetWithAllProduct(Integer uid) throws EtResourceNotFoundException;
}
