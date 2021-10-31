package com.dev.withpet.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dev.withpet.domain.Category;
import com.dev.withpet.services.CategoryService;
import com.dev.withpet.services.ProductService;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public List<Category> findAll() {
//        List<Category> categories = categoryService.findAll(id);
        return categoryService.findAll();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(HttpServletRequest request,
                                                    @PathVariable("id") Integer id) {
      
        Category category = categoryService.fetchCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Category> addCategory(HttpServletRequest request,
                                                @RequestBody Map<String, Object> categoryMap) {
    
        
        String name = (String) categoryMap.get("name");
        Category category = categoryService.addCategory(name);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Map<String, Boolean>> updateCategory(HttpServletRequest request,
	  
	  @PathVariable("id") Integer id,
	  
	  @RequestBody Category category) { 
		  int catid = (Integer)request.getAttribute("id"); 
		  categoryService.updateCategory(catid, category);
	  Map<String, Boolean> map = new HashMap<>(); map.put("success", true); 
	  return new ResponseEntity<>(map, HttpStatus.OK); 
	  }
	 

}
