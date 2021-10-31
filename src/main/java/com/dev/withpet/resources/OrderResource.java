package com.dev.withpet.resources;

import java.sql.Timestamp;
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

import com.dev.withpet.domain.Order;
import com.dev.withpet.services.OrderService;


@RestController
@RequestMapping("/api/order")
public class OrderResource {

    @Autowired
    OrderService orderService;

   

    	    @PostMapping("")
    	    public ResponseEntity<Order> addTransaction(HttpServletRequest request,
    	                                                      @PathVariable("id") Integer id,
    	                                                      @RequestBody Map<String, Object> orderMap) {
    	        int userId = (Integer) request.getAttribute("userId");

    	        String address = (String) orderMap.get("address");
    	        Timestamp created = (Timestamp) orderMap.get("added");
    	        Timestamp updated = (Timestamp) orderMap.get("updated");
    	        String staid = (String) orderMap.get("staid");
    	        Order order =orderService.addOrder(userId, address, created, updated, staid);
    	        return new ResponseEntity<>(order, HttpStatus.CREATED);
    	    }

    	    @PutMapping("/{transactionId}")
    	    public ResponseEntity<Map<String, Boolean>> updateTransaction(HttpServletRequest request,
    	                                                                  @PathVariable("categoryId") Integer id,
    	                                                                  @PathVariable("transactionId") Integer staid,
    	                                                                  @RequestBody Order order) {
    	        int userId = (Integer) request.getAttribute("userId");
    	        orderService.updateOrder(userId, id,  null, null, null, staid, order);
    	        Map<String, Boolean> map = new HashMap<>();
    	        map.put("success", true);
    	        return new ResponseEntity<>(map, HttpStatus.OK);
    	    }

    	    @DeleteMapping("/{transactionId}")
    	    public ResponseEntity<Map<String, Boolean>> deleteTransaction(HttpServletRequest request,
    	                                                                  @PathVariable("categoryId") Integer id,
    	                                                                  @PathVariable("transactionId") Integer staid) {
    	        int userId = (Integer) request.getAttribute("userId");
    	        orderService.removeById(userId, id, staid);
    	        Map<String, Boolean> map = new HashMap<>();
    	        map.put("success", true);
    	        return new ResponseEntity<>(map, HttpStatus.OK);
    	    }
}
