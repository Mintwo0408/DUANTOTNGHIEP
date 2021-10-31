package com.dev.withpet.services;

import java.sql.Timestamp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.withpet.domain.Pet;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;
import com.dev.withpet.repositories.PetRepository;



@Service
@Transactional
public class PetServiceImpl implements PetService{

	@Autowired
    PetRepository petRepository;
	
	@Override
	public List<Pet> fetchAllPet(Integer uid) {
		return petRepository.findAll(uid);
	}

	@Override
	public Pet fetchPetById(Integer petid, Integer uid) throws EtResourceNotFoundException {
		return petRepository.findById(petid,uid);
	}

	@Override
	public Pet addPet(Timestamp added, String breed,String img, String kind, String name, Integer uid,Timestamp updated , String birth) throws EtBadRequestException {
		int petid = petRepository.create(added, breed, img, kind, name, uid , updated, birth);
		return petRepository.findById(petid,uid);
	}

	@Override
	public void updatePet(Integer uid, Pet pet) throws EtBadRequestException {
		petRepository.update(uid, pet);
		
	}

	@Override
	public void removePetWithAllProduct(Integer uid) throws EtResourceNotFoundException {

	}


}
