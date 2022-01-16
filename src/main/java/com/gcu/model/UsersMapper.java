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

public class UsersMapper implements RowMapper<UserEntity>{

	// NOTE CHANGED ALL USER MODEL TO USER ENTITY
	@Override
	public UserEntity mapRow(ResultSet resultset, int rowNum) throws SQLException {
		// create new user entity which will be used to set roles
		UserEntity user = new UserEntity(
				resultset.getLong("ID"),
				resultset.getString("USERNAME"),
				resultset.getString("PASSWORD"),
				// roles
				resultset.getString("ROLE")
				);
		
		return user;
	}

}
