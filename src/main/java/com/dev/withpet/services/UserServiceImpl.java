package com.dev.withpet.services;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.withpet.domain.User;
import com.dev.withpet.exceptions.EtAuthException;
import com.dev.withpet.repositories.UserRepository;



@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String phone, String password) throws EtAuthException {
        if(phone != null) phone = phone.toString();
        return userRepository.findByPhoneAndPassword(phone, password);
    }

    @Override
    public User registerUser(Timestamp updated, Boolean sex, String phone, String name, String img, Timestamp created,
			String address, String password, Boolean role) throws EtAuthException {
        if(phone != null) phone = phone.toString();
        Integer count = userRepository.getCountByPhone(phone);
        if(count > 0)
            throw new EtAuthException("Phone already in use");
        Integer id = userRepository.create(updated,sex,phone,name,img,created,address,password,role);
        return userRepository.findById(id);
    }
}
