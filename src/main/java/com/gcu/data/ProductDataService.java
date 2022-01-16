package com.gcu.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcu.model.ProductMapper;
import com.gcu.model.ProductModel;

/*
 * Kacey morris and Alex vergara
 * Milestone
 * 11/7/2021
 */

@Repository
public class ProductDataService implements ProductDataAccessInterface {
	// jdbctemplate is a Spring class used to shorten the amount of code needed to interact with a SQL database
	// dataSource is defined in the application.properties file (src/main/resources)
	@Autowired
	DataSource dataSource;
	JdbcTemplate jdbcTemplate;
	
	// constructor
	public ProductDataService(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	// get single product by id 
	@Override
	public ProductModel getById(int id) {
		// query but easier with jdbc
		ProductModel result = jdbcTemplate.queryForObject("SELECT * FROM products WHERE id = ?", 
				new ProductMapper(),
				new Object[] {id});
		// return the product
		return result;
	}

	// get all products
	@Override
	public List<ProductModel> getProducts() {
		// query for all properties of all products
		List<ProductModel> products = jdbcTemplate.query("SELECT * FROM products", new ProductMapper());
		// System.out.println("In data service first product id " + products.get(0).getID());
		return products;
	}

	// search by a given term
	@Override
	public List<ProductModel> searchProducts(String searchTerm) {
		// SQL query with jdbc for searching product names
		return jdbcTemplate.query("SELECT * FROM products WHERE NAME LIKE ?", 
				new ProductMapper(), 
				new Object[] {"%" + searchTerm + "%"});
	}

	// adding a new product to the database
	@Override
	public int addOne(ProductModel newProduct) {
		// insert statement with protection against SQL injections
		return jdbcTemplate.update("INSERT INTO products (NAME, PRICE, LOCATION, DETAILS, AVAILABILITY, CONTACT, IMAGE) VALUES (?,?,?,?,?,?,?)",
				newProduct.getVacationName(),
				newProduct.getPrice(),
				newProduct.getLocation(),
				newProduct.getDetails(),
				newProduct.getAvailability(),
				newProduct.getContact(),
				newProduct.getImage());
	}

	// delete a product from database by id
	@Override
	public boolean deleteOne(int id) {
		int updateResult = jdbcTemplate.update(
				"DELETE FROM products WHERE id = ?",
				new Object[] {id});
		System.out.println("data service: Trying to delete" + id);
		System.out.println("data service: result: " + updateResult);
		return (updateResult > 0);
	}

	// update a product by id with new product details
	@Override
	public ProductModel updateOne(int idToUpdate, ProductModel updateProduct) {
		// sql query with injection protection
		int result = jdbcTemplate.update(
				"UPDATE products SET NAME = ?, PRICE = ?, LOCATION = ?, DETAILS = ?, AVAILABILITY = ?, CONTACT = ?, IMAGE = ? WHERE id = ?",
				updateProduct.getVacationName(),
				updateProduct.getPrice(),
				updateProduct.getLocation(),
				updateProduct.getDetails(),
				updateProduct.getAvailability(),
				updateProduct.getContact(),
				updateProduct.getImage(),
				idToUpdate);
		if (result > 0) {
			return updateProduct;
		}
		else {
			return null;
		}
	}
}
