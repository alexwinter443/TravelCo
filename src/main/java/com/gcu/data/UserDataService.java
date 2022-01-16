package com.gcu.data;

import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcu.model.ProductMapper;
import com.gcu.model.ProductModel;
import com.gcu.model.UserEntity;
import com.gcu.model.UserModel;
import com.gcu.model.UserModelMapper;
import com.gcu.model.UsersMapper;

@Repository
public class UserDataService {
	
	@Autowired
	DataSource datasource;
	JdbcTemplate jdbcTemplate;
	
	public UserDataService(DataSource datasource)
	{
		this.datasource = datasource;
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	//@SuppressWarnings("deprecation")
	@SuppressWarnings("deprecation")
	public int getUsersByUsername(String username, String password)
	{
		System.out.println("OLD user data service getting user by username");
		String sql = "SELECT COUNT(*) FROM users where username = ?";
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users where username = ? and password = ?", new Object[]{username, password}, Integer.class);

	}
	
	// NOTE CHANGED FROM USERMODEL TO USERENTITY
	public UserEntity findByUsername(String username) {
		System.out.println("OLD user data service find by username");
		// query but easier with jdbc
		UserEntity result = jdbcTemplate.queryForObject("SELECT ID, USERNAME, PASSWORD, ROLE FROM users WHERE USERNAME = ?", 
				new UsersMapper(),
				new Object[] {username});
		// return the user
		return result;
	}

	public int addUser(UserModel user)
	{
		System.out.println("OLD user data service add user method");
		return jdbcTemplate.update("insert into users (ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, PHONE) VALUES(?,?,?,?,?,?,?)",
				0,
				user.getUsername(),
				user.getPassword(),
				user.getFirstname(),
				user.getLastname(),
				user.getEmail(),
				user.getPhone()
				);
	}
	
	// NOTE CHANGED FROM USERMODEL TO USERENTITY
	public List<UserModel> getAllUsers() {
		System.out.println("OLD user data service get all users");
		// query but easier with jdbc
		List<UserModel> result = jdbcTemplate.query("SELECT * FROM users", new UserModelMapper());
		// return the user
		return result;
	}
	
	public boolean deleteOne(Long id) {
		int updateResult = jdbcTemplate.update(
				"DELETE FROM users WHERE ID = ?",
				new Object[] {id});
		System.out.println("user data service: Trying to delete" + id);
		System.out.println("user data service: result: " + updateResult);
		return (updateResult > 0);
	}

	// update a user by id with new user details
	public UserModel updateOne(Long idToUpdate, UserModel updateUser) {
		// sql query with injection protection
		int result = jdbcTemplate.update(
				"UPDATE users SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, PHONE = ?, ROLE = ? WHERE ID = ?",
				updateUser.getFirstname(),
				updateUser.getLastname(),
				updateUser.getEmail(),
				updateUser.getPhone(),
				updateUser.getRoles(),
				idToUpdate);
		if (result > 0) {
			return updateUser;
		}
		else {
			return null;
		}
	}
}
