package com.dev.withpet.resources;

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


import com.dev.withpet.domain.Product;
import com.dev.withpet.services.ProductService;

import javax.servlet.http.HttpServletRequest;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductResource {

    @Autowired
    ProductService ProductService;

    @GetMapping("")

    public List<Product> findAll() {
      return ProductService.findAll();
    }
 
    @GetMapping("/{proId}")
    public ResponseEntity<Product> getTransactionById(HttpServletRequest request,
                                                          @PathVariable("id") Integer id){
                                                           
        int catid = (Integer) request.getAttribute("catid");
        Product product = ProductService.fetchProductById(id, catid);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Product> addProduct(HttpServletRequest request,
    		@RequestBody Map<String, Object> productMap) throws ParseException {
    	Integer catid = Integer.valueOf(productMap.get("catid").toString());
        String name = (String) productMap.get("name");
        String brand =   (String) productMap.get("brand");
        String description =  (String) productMap.get("description");
        Integer discount = Integer.valueOf(productMap.get("discount").toString());
        String unit = (String) productMap.get("unit");
        Boolean available = (Boolean) productMap.get("available");
        Timestamp created = (Timestamp) productMap.get("created");
        Timestamp updated = (Timestamp) productMap.get("updated");
        Boolean deleted = (Boolean) productMap.get("deleted");
        Integer uid = Integer.valueOf(productMap.get("uid").toString());
        Integer quantity = Integer.valueOf(productMap.get("quantity").toString());
        String price = (String) productMap.get("price");
        String img = (String) productMap.get("img");
        Product product = ProductService.addProduct(catid, name, brand, description, discount, unit, available,created, updated,deleted, uid, quantity, price, img );
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{proId}")
    public ResponseEntity<Map<String, Boolean>> updatePoduct(HttpServletRequest request,
                                                                  @PathVariable("id") Integer id,
                                                                  @RequestBody Product product) {
        int catid = (Integer) request.getAttribute("catid");
      
        ProductService.updateProduct(id, product);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{proId}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(HttpServletRequest request,
                                                                  @PathVariable("id") Integer id
                                                                ) {
        int catid = (Integer) request.getAttribute("catid");
        ProductService.removeProduct(id, catid);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
