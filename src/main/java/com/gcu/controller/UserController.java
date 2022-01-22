package com.gcu.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.ProductBusinessServiceInterface;
import com.gcu.business.SecurityServiceInterface;
import com.gcu.model.ProductModel;
import com.gcu.model.SearchProductsModel;
import com.gcu.model.UserEntity;
import com.gcu.model.UserModel;

/*
 * Kacey Morris and Alex Vergara
 * Milestone
 * 11/29/2021
 */

@Controller
public class UserController {
	
	// DEPENDENCY INJECTION
	@Autowired
	SecurityServiceInterface securityService;
	
	@Autowired
	ProductBusinessServiceInterface productService;
	
	// home route
	@GetMapping("/")
	public String homePage(Model model) {
		// Display Login Form View
		model.addAttribute("title", "Vacation Site");
		model.addAttribute("userModel", new UserEntity());
		// model.addAttribute("userModel", new UserModel());
		
		int users=1;
		model.addAttribute("users", users);
		return "homePage";
	}
	
	// login route takes us to login page
	@GetMapping("/login")
	public String display(Model model) {
		// Display Login Form View
		System.out.println("Showing login page in user controller");
		model.addAttribute("userModel", new UserEntity());
		// model.addAttribute("userModel", new UserModel());
		return "login";
	}
	
	@PostMapping("/doLogin")
	public String doLogin(@Valid UserEntity loginModel, BindingResult bindingResult, Model model) {
		// check for errors
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Login Form");
			return "login";
		}
		// authenticate the user
		UserModel uModel = new UserModel(loginModel.getUsername(), loginModel.getPassword());
		boolean success = securityService.isAuthenticated(uModel, uModel.getUsername(), uModel.getPassword());
		
		// login succeeded, send to success page
		if (success) {
			// send a user entity for roles
			model.addAttribute("userModel", loginModel);
			return "LoginSuccess";
		}
		else {
			// login failed, return login form
			return "login";
		}
	}	
	
	// this takes us to the register page
	@GetMapping("/register")
	public String showRegister(Model model) {
		// Display Register Form View
		System.out.println("Showing register page in user controller");
		model.addAttribute("userModel", new UserModel());
	
		return "register";
	}
	
	// register form post	
	@PostMapping("/doRegister")
	public String doRegister(@Valid UserModel userModel, BindingResult bindingResult, Model model, HttpServletResponse response) {
		// check for errors
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Registration Form");
			return "register";
		}
		
		// encode password before registering user
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String ecodedPsw = passwordEncoder.encode(userModel.getPassword());
		userModel.setPassword(ecodedPsw);
		
		// NOTE CHANGED USERMODEL TO USERENTITY
		// register user and track them with cookies
		UserEntity usr1 = securityService.registerUser(userModel, response);
		System.out.println(usr1);
		
		// add user model
		UserEntity user = securityService.getByUsername(userModel);
		model.addAttribute("userModel", user);
		
		return "RegisterSuccess";
	}
	
	// controller mapping for users admin
	@GetMapping("/admin") 
	public String showUsersForAdmin(Model model) {
		// display all products with delete and edit buttons
		List<UserModel> users = securityService.getAllUsers();   
		model.addAttribute("title", "Users Admin");
		model.addAttribute("searchModel", new SearchProductsModel());
		model.addAttribute("users", users);
		System.out.println(users);
		// usersAdmin page shows a table of users including buttons for del and edit.
		return "usersAdmin";
	}
	
	// to delete with controller from admin
	@PostMapping("/delete") 
	public String deleteOrder(@Valid UserModel user, BindingResult bindingResult, Model model) {
		// delete the user
		securityService.deleteOne(user.getId());
		System.out.println("In Controller: user id is " + user.getId() + " username " + user.getUsername());
		// get updated list of all the users
		List<UserModel> users = securityService.getAllUsers();
		// display all users
		model.addAttribute("users", users); 
		model.addAttribute("searchModel", new SearchProductsModel()); 
		return "usersAdmin";
	}
	
	// used in the admin form for each user
	@PostMapping("/update") 
	public String updateUser(@Valid UserModel user, BindingResult bindingResult, Model model) {
		// update the existing user
		// business service
		securityService.updateOne(user.getId(), user);
		System.out.println("Just updated user with ID" + user.getId());
		// get updated list of all the users
		List<UserModel> users = securityService.getAllUsers();
		// display all users
		model.addAttribute("users", users); 
		model.addAttribute("searchModel", new SearchProductsModel()); 
		return "usersAdmin";
	}
}