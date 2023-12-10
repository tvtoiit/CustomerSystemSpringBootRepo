package com.example.CustomerSystemSpringboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.CustomerSystemSpringboot.entitys.MstUser;

@Repository
public interface LoginRepository extends CrudRepository<MstUser, Integer>{
	MstUser findByUserIdAndPasswordAndDeleteYmdIsNull(String userId, String password);
}