package com.customer;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.customer.model.User;
import com.customer.repository.RoleRepository;
import com.customer.repository.UserRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerServiceApplication {
	
	@Autowired
    private UserRepository repository;
	@Autowired
	private RoleRepository roleRepository;

    @PostConstruct
    public void initUsers() {
    	List<User> users =  Stream.of(new User(1, "test",new java.util.Date(), 'M', "9876666", "password")).collect(Collectors.toList());
    	//List<Role> role = Stream.of(new Role(1,"Customer", "001"),new Role(2,"Branch Manager", "002")).collect(Collectors.toList());
    	//roleRepository.saveAll(role);
    	repository.saveAll(users);
    	
    }
	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
