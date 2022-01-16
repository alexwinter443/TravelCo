package com.gcu.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.gcu.model.UserEntity;

/*
 * Kacey Morris and Alex Vergara
 * Milestone
 * 11/29/2021
 */

@Component
public interface UsersRepository extends CrudRepository<UserEntity, Long> {
	// crud repository handles most of the operations
	UserEntity findByUsername(String username);
}
