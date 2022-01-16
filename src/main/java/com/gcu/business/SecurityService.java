package com.gcu.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;

import com.gcu.data.UserDataService;
import com.gcu.model.ProductModel;
import com.gcu.model.UserEntity;
import com.gcu.model.UserModel;

/*
 * Kacey morris and Alex vergara
 * Milestone
 * 11/7/2021
 */
public class SecurityService implements SecurityServiceInterface{
	
	@Autowired
	UserDataService usersDAO;
	
	@Override
	public boolean isAuthenticated(UserModel loginModel, String username, String password) {
	
		// Check to see if the login matches any of the valid logins
		int result = usersDAO.getUsersByUsername(loginModel.getUsername(), loginModel.getPassword());
		
		System.out.println("in the is authenticated method in the security service.");
		
		// successful login ; ie returns true
		if(result > 0) {
			System.out.println("number of people with this username: " + result);
			return true;
		}
		// checks for registered user using cookies
		else if(loginModel.getUsername().equals(username) && loginModel.getPassword().equals(password)) {
			return true;
		}
		// user not found
		else {
			return false;
		}
	}
	

	@Override
	public void test() {
		System.out.println("We are running and using the SecurityService");
		
	}

	// NOTE CHANGED FROM USERMODEL TO USERENTITY
	// register user
	@Override
	public UserEntity registerUser(UserModel userModel, HttpServletResponse response) {
		
		// call add user method
		usersDAO.addUser(userModel);
		
		UserEntity user = usersDAO.findByUsername(userModel.getUsername());
		
		System.out.println("In the security service registering a user with user entity");
		
		// return user model
		// UserModel usr1 = new UserModel(userModel.getUsername(), userModel.getPassword());
		
		// return usr1;
		return user;
	}
	
	// NOTE CHANGED FROM USER MODEL TO USER ENTITY
	@Override
	public UserEntity getByUsername(UserModel userModel) {
		return usersDAO.findByUsername(userModel.getUsername());
	}

	// get all users from database 
	@Override
	public List<UserModel> getAllUsers() {
		List<UserModel> users = usersDAO.getAllUsers();
		return users;
	}
	
	// delete a user
	@Override
	public boolean deleteOne(Long id) {
		return usersDAO.deleteOne(id);
	}

	// update a user
	@Override
	public UserModel updateOne(Long idToUpdate, UserModel updateUser) {
		return usersDAO.updateOne(idToUpdate, updateUser);
	}
}
