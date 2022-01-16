package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.ProductDataService;
import com.gcu.model.ProductModel;

/*
 * Kacey Morris and Alex Vergara
 * Milestone
 * 11/7/2021
 */

public class ProductBusinessService implements ProductBusinessServiceInterface {
	
	// dependency injection
	// mysql database 
	@Autowired
	ProductDataService productsDAO;

	// get all products using the data service
	@Override
	public List<ProductModel> getProducts() {		
		return productsDAO.getProducts();
	}

	@Override
	public void init() {
		System.out.println("Init method of Product Business Service appears to be working.");
	}

	@Override
	public void destroy() {
		System.out.println("Destroy method of the Product Business Service was just called.");
	}

	// get a single product by id
	@Override
	public ProductModel getOne(int id) {
		return productsDAO.getById(id);
	}

	// search products by a search term
	@Override
	public List<ProductModel> searchProducts(String searchTerm) {
		return productsDAO.searchProducts(searchTerm);
	}

	// add a new product
	@Override
	public int addOne(ProductModel newProduct) {
		return productsDAO.addOne(newProduct);
	}

	// delete a product
	@Override
	public boolean deleteOne(int id) {
		return productsDAO.deleteOne(id);
	}

	// update a product
	@Override
	public ProductModel updateOne(int idToUpdate, ProductModel updateProduct) {
		return productsDAO.updateOne(idToUpdate, updateProduct);
	}
}
