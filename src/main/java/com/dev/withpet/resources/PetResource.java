package com.dev.withpet.resources;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.withpet.domain.Pet;
import com.dev.withpet.services.PetService;

@RestController
@RequestMapping("/api/pet")
public class PetResource {

	@Autowired
    PetService petService;
	
	@GetMapping("")
    public ResponseEntity<List<Pet>> getAllCategories(HttpServletRequest request) {
        int uid = (Integer) request.getAttribute("uid");
        List<Pet> pet = petService.fetchAllPet(uid);
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @GetMapping("/{petid}")
    public ResponseEntity<Pet> getCategoryById(HttpServletRequest request,
                                                    @PathVariable("petid") Integer petid) {
        int uid = (Integer) request.getAttribute("uid");
        Pet pet = petService.fetchPetById(petid, uid);
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }
    
    @PostMapping("")			
    public ResponseEntity<Pet> addPet(HttpServletRequest request,
                                                @RequestBody Map<String, Object> petMap) throws ParseException {
      
        Timestamp added = (Timestamp) petMap.get("added");
        String breed = (String) petMap.get("breed");
        String img = (String) petMap.get("img");
        String kind = (String) petMap.get("kind");
        String name = (String) petMap.get("name"); 
        int userId = (Integer) request.getAttribute("uid");
        Timestamp updated = (Timestamp) petMap.get("updated");
       String birth = (String) petMap.get("birth");
        Pet pet = petService.addPet(added, breed, img, kind, name, userId, updated, birth);
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @PutMapping("/{petid}")
    public ResponseEntity<Map<String, Boolean>> updatePet(HttpServletRequest request,
                                                               @PathVariable("petid") Integer petid,
                                                               @RequestBody Pet pet) {
        int uid = (Integer) request.getAttribute("uid");
        petService.updatePet(uid, pet);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
 
    @DeleteMapping("/{petid}")
    public ResponseEntity<Map<String, Boolean>> deletePet(HttpServletRequest request,
                                                               @PathVariable("petid") Integer petid) {
        int uid = (Integer) request.getAttribute("uid");
        petService.removePetWithAllProduct(uid);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
