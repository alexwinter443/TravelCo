package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.gcu.data.UsersDataService;
import com.gcu.model.UserEntity;

/*
 * Kacey Morris and Alex Vergara
 * Milestone
 * 11/29/2021
 */

@Service
public class UsersBusinessService implements UserDetailsService {
	
	// the new users data service that uses a repository
	@Autowired
	private UsersDataService service;
	
	// non default constructor
	public UsersBusinessService(UsersDataService service) {
		this.service = service;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("User business service is searching for " + username);
		// try to find the user in the database. if not found throw a username not found exception else return a spring security user
		UserEntity user = service.findByUsername(username);
		System.out.println("User found: " + user);
		
		// if the user is not null
		if (user != null) {
			System.out.println("In Business Service user not null " + user);
			// add authorities associated with user
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			System.out.println("Role ========= " + user.getRole());
			authorities.add(new SimpleGrantedAuthority(user.getRole()));
			// make sure admin can also access user pages
			if (user.getRole().equals("ADMIN")) {
				authorities.add(new SimpleGrantedAuthority("USER"));
			}
			// ensure this is working
			System.out.println("Authorities ========== " + authorities.get(0) + " " + authorities);
			User newUser = new User(user.getUsername(), user.getPassword(), authorities);
			System.out.println("The user created with user details service in BS " + newUser);
			return newUser;
			// return new User(user.getUsername(), user.getPassword(), authorities);
		}
		else {
			System.out.println("User Not Found");
			throw new UsernameNotFoundException("username not found");
		}
	}
	
}
