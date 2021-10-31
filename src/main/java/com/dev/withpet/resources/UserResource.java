package com.dev.withpet.resources;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.withpet.Constants;
import com.dev.withpet.domain.User;
import com.dev.withpet.services.UserService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserResource {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap) {
        String phone = (String) userMap.get("phone");
        String password = (String) userMap.get("password");
        User user = userService.validateUser(phone, password);
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap) {
    	Timestamp updated =  (Timestamp) userMap.get("updated");
        Boolean sex = (Boolean) userMap.get("sex");
        String phone = (String) userMap.get("phone");
        String name = (String) userMap.get("name");
        String img = (String) userMap.get("img");
        Timestamp created = (Timestamp) userMap.get("created");
        String address = (String) userMap.get("address");
        String password = (String) userMap.get("password");
        Boolean role = (Boolean) userMap.get("role");
        User user = userService.registerUser(updated, sex, phone, name,  img, created, address, password,role);
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    private Map<String, String> generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("updated", user.getUpdated())
                .claim("sex", user.getSex())
                .claim("phone", user.getPhone())
                .claim("name", user.getName())
                .claim("img", user.getImg())
                .claim("created", user.getCreated())
                .claim("address", user.getAddress())
                .claim("id", user.getId())
                .claim("role", user.getRole())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
