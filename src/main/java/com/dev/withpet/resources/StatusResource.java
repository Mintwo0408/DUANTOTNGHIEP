package com.dev.withpet.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dev.withpet.domain.Status;
import com.dev.withpet.services.StatusService;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/status")
public class StatusResource {

    @Autowired
    StatusService statusService;

    @GetMapping("")
    public List<Status> findAll() {
//        List<Category> categories = categoryService.findAll(id);
        return statusService.findAll();
    }

    @GetMapping("/{statusId}")
    public ResponseEntity<Status> getCategoryById(HttpServletRequest request,
                                                    @PathVariable("id") Integer id) {
      Status status = statusService.fetchStatusById(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Status> addStatus(HttpServletRequest request,
                                                @RequestBody Map<String, Object> statusMap) {
    
        
        String name = (String) statusMap.get("name");
        Status status = statusService.addStatus(name);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @PutMapping("/{statusId}")
    public ResponseEntity<Map<String, Boolean>> updateCategory(HttpServletRequest request,
	  
	  @PathVariable("id") Integer id,
	  
	  @RequestBody Status status) { 
		  int stuid = (Integer)request.getAttribute("id"); 
		  statusService.updateStatus(stuid, status);
	  Map<String, Boolean> map = new HashMap<>(); map.put("success", true); 
	  return new ResponseEntity<>(map, HttpStatus.OK); 
	  }
	 

}
