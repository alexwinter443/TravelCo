package com.gcu.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

/*
 * Kacey Morris and Alex Vergara
 * Milestone
 * 11/29/2021
 */

public class UserModelMapper implements RowMapper<UserModel>{

	// NOTE CHANGED ALL USER MODEL TO USER ENTITY
	@Override
	public UserModel mapRow(ResultSet resultset, int rowNum) throws SQLException {
		// create new user entity which will be used to set roles
		UserModel user = new UserModel(
				resultset.getLong("ID"),
				resultset.getString("USERNAME"),
				resultset.getString("PASSWORD"),
				resultset.getString("FIRST_NAME"),
				resultset.getString("LAST_NAME"),
				resultset.getString("EMAIL"),
				resultset.getString("PHONE"),
				// roles
				resultset.getString("ROLE")
				);
		
		return user;
	}

}
