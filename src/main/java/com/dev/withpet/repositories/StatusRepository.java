package com.dev.withpet.repositories;

import java.util.List;

import com.dev.withpet.domain.Status;
import com.dev.withpet.exceptions.EtBadRequestException;
import com.dev.withpet.exceptions.EtResourceNotFoundException;

public interface StatusRepository {

    List<Status> findAll() throws EtResourceNotFoundException;

    Status findById(Integer id) throws EtResourceNotFoundException;

    Integer create(String name) throws EtBadRequestException;

    void update(Integer id, String name, Status status) throws EtBadRequestException;

    void removeById(Integer id) throws EtBadRequestException;

	void delete(Integer id) throws EtBadRequestException;

	void update(Integer id,  Status status);

}
