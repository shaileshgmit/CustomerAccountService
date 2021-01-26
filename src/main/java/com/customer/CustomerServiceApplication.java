package com.customer;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.customer.model.Role;
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
	
	@Autowired
	@PersistenceContext
	EntityManager entityManager;

    @PostConstruct
    public void initUsers() {
    	Role defaultRecord = new Role(1,"Branch Manager", "002");
    	Role r  = roleRepository.saveAndFlush(defaultRecord);
		Role role = entityManager.getReference(Role.class, r.getId());
    	User user =  new User(1, "test",new java.util.Date(), 'M', "9876666", "password",defaultRecord);
    	user.setRole(role);
    	repository.save(user);
    	
    }
    @Bean
    public ModelMapper getModelMapper() {
    	return new ModelMapper();
    }
	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
