package com.dev.withpet.resources;


import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.withpet.domain.Pet;
import com.dev.withpet.domain.Serv;
import com.dev.withpet.services.ServService;

@RestController
@RequestMapping
("/api/serv")
public class ServResource {

	@Autowired
	ServService servService;
	
	@GetMapping("")
	
	public List<Serv> findAll(){
		return servService.findAll();
	}
	@PostMapping("/add")
	public ResponseEntity<Serv> addServ(HttpServletRequest request, 
			@RequestBody Map<String, Object> servMap) throws ParseException {
		Boolean role = (Boolean) request.getAttribute("role");
		String name = (String) servMap.get("name");
		Double price = (Double) servMap.get("price");
		String unit = (String) servMap.get("unit");
		Integer piority = (Integer) servMap.get("piority");
		String description = (String) servMap.get("description");
		Integer catId = (Integer) servMap.get("catId");
		if(role) {
			Serv serv =servService.addServ(catId, name, price, unit, piority, description, catId);
			return new ResponseEntity<>(serv, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Serv>(HttpStatus.FORBIDDEN);
		}
	}

    @PutMapping("/{servid}")
    public ResponseEntity<Map<String, Boolean>> updatePet(HttpServletRequest request,
                                                               @PathVariable("id") Integer id,
                                                               @RequestBody Serv serv) {
        int servid = (Integer) request.getAttribute("id");
       
        servService.updateService(id, serv);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
