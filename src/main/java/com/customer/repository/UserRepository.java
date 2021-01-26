package com.customer.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
	User findByUserName(String userName);

	User findByPhoneNumber(String phoneNumber);

	User findByPhoneNumberAndUserNameAndPassword(String phoneNumber, String name, String password);
	

}
