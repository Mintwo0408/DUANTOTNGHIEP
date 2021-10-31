package com.dev.withpet.services;



import java.sql.Timestamp;

import com.dev.withpet.domain.User;
import com.dev.withpet.exceptions.EtAuthException;

public interface UserService {

    User validateUser(String phone, String password) throws EtAuthException;

    User registerUser(Timestamp updated, Boolean sex, String phone, String name, String img, Timestamp created,
			String address, String password, Boolean role) throws EtAuthException;

}
