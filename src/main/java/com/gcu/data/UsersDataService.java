package com.gcu.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.model.UserEntity;

/*
 * Kacey Morris and Alex Vergara
 * Milestone
 * 11/29/2021
 */

/*
 * Three stereotypes are typical for Spring beans
 * @Component is a generic stereotype for any Spring-managed component.
 * @Service annotates classes at the Service layer
 * @Repository annotates classes at the persistence layer, which will act as a database repository
 * @Service and @Repository are special cases of @Component
 * They are technically the same, but we use them for different purposes
 * */

@Service
public class UsersDataService implements UsersDataAccessInterface<UserEntity> {
	// repository uses the crud repository for data manipulation
	@Autowired
	private UsersRepository usersRepository; 
	
	// non default constructor
	public UsersDataService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	// find by username defined in interface
	@Override
	public UserEntity findByUsername(String username) {
		System.out.println("In users data service username is: " + username);
		return usersRepository.findByUsername(username);
	}

}
