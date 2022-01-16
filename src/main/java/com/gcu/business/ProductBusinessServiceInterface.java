package com.gcu.business;

import java.util.List;

import com.gcu.model.ProductModel;

/*
 * Kacey morris and Alex vergara
 * Milestone
 * 11/7/2021
 */

public interface ProductBusinessServiceInterface {
	// interface methods for dependency injection smoothness
	public List<ProductModel> getProducts();
	public ProductModel getOne(int id);
	public List<ProductModel> searchProducts(String searchTerm);
	public int addOne(ProductModel newOrder); 
	public boolean deleteOne(int l);
	public ProductModel updateOne(int idToUpdate, ProductModel updateProduct);
	public void init();
	public void destroy();
}