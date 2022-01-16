package com.gcu.controller;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gcu.business.ProductBusinessServiceInterface;
import com.gcu.model.ProductModel;
import com.gcu.model.SearchProductsModel;

import java.util.List;

/*
 * Kacey morris and Alex vergara
 * Milestone
 * 11/7/2021
 */

@Controller
@RequestMapping("/products")
public class ProductsController {
	// uses dependency injection configuration found in the SpringConfig file 
	// to choose which busienssService will be utilized.
	@Autowired
	private ProductBusinessServiceInterface productService;
	
	// to create a product
	@GetMapping("/createProduct") 
	public String displayAddNewForm(Model model){
		// Display new product form
		model.addAttribute("title", "Add New Product");
		model.addAttribute("productModel", new ProductModel());
		return "CreateProduct";
	}
	
	// used in the form action to create a product
	@PostMapping("/createPackage") 
	// process a request from the AddProduct.  Add a new product to the database.
	public String addProduct(@Valid ProductModel newProduct, BindingResult bindingResult, Model model) {
		// add the new product
		productService.addOne(newProduct);
		// get updated list of all the product
		List<ProductModel> products = productService.getProducts(); 
		// display all product
		model.addAttribute("products", products); 
		model.addAttribute("searchModel", new SearchProductsModel()); 
		// return "products";
		return "productsAdmin";
	} 
	
	// controller for search form
	@GetMapping("/searchForm") 
	public String displaySearchForm(Model model){
		// Display Search Form View
		model.addAttribute("title", "Search Products");
		model.addAttribute("searchProductsModel", new SearchProductsModel());
		return "productSearchForm";
	}
	
	// used in the form action to display search results
	@PostMapping("/searchResults") 
	public String showAllOrders(@Valid SearchProductsModel searchModel, BindingResult bindingResult, Model model) {
		System.out.println("Performing search results for " + searchModel.getSearchTerm());
		// Check for validation errors        
		if (bindingResult.hasErrors()){        
			model.addAttribute("title", "Search Products");            
			return "productSearchForm";        
		}
		// search for the products
		List<ProductModel> products = productService.searchProducts(searchModel.getSearchTerm());   
		model.addAttribute("title", "Search for Products");
		model.addAttribute("searchModel", searchModel);
		model.addAttribute("products", products);
		// display products that were found
		return "productsImages";
		// return "products";
	}
	
	// controller mapping for all products
	@GetMapping("/all") 
	public String showAllOrders( Model model) {  
		List<ProductModel> products = productService.getProducts();
		// System.out.println("In Controller ShowallOrders first id " + products.get(0).getID());
		model.addAttribute("title", "Show All Products");
		model.addAttribute("searchModel", new SearchProductsModel());
		model.addAttribute("products", products);
		// show all products
		// return "products";
		return "productsImages";
	}
	
	// controller mapping for admin
	@GetMapping("/admin") 
	public String showProductsForAdmin(Model model) {  
		// display all products with delete and edit buttons
		List<ProductModel> products = productService.getProducts();   
		model.addAttribute("title", "Edit or Delete Products");
		model.addAttribute("searchModel", new SearchProductsModel());
		model.addAttribute("products", products);
		// System.out.println("In Controller admin first id " + products.get(0).getID());
		// ordersAdmin page shows a table of orders including buttons for del and edit.
		return "productsAdmin";
	}
	
	// to delete with controller from admin
	@PostMapping("/delete") 
	public String deleteOrder(@Valid ProductModel product, BindingResult bindingResult, Model model) {
		// delete the product
		productService.deleteOne(product.getId());
		System.out.println("In Controller: product id is " + product.getId() + " product name " + product.getVacationName());
		// get updated list of all the products
		List<ProductModel> products = productService.getProducts(); 
		// display all products
		model.addAttribute("products", products); 
		model.addAttribute("searchModel", new SearchProductsModel()); 
		return "productsAdmin";
	}
	
	// edit form for admin
	@PostMapping("/editForm") 
	public String displayEditForm(ProductModel productModel, Model model){
		// Display edit form
		model.addAttribute("title", "Edit Product");
		model.addAttribute("productModel", productModel);
		System.out.println("In Controller edit id is " + productModel.getId());
		return "productsEditForm";
	}
	
	// used in the edit form action
	@PostMapping("/doUpdate") 
	public String updateOrder(@Valid ProductModel product, BindingResult bindingResult, Model model) {
		// update the existing product
		// business service
		productService.updateOne(product.getId(), product);
		// get updated list of all the products
		List<ProductModel> products = productService.getProducts(); 
		// display all products
		model.addAttribute("products", products); 
		model.addAttribute("searchModel", new SearchProductsModel()); 
		return "productsAdmin";
	}
	
	// showing one product
	@GetMapping("/showOne") 
	public String displaySingleProduct(ProductModel productModel, Model model){
		// Display edit form
		model.addAttribute("title", "One Product");
		model.addAttribute("productModel", productModel);
		System.out.println("Show One Product image = " + productModel.getImage());
		return "singleProductForm";
	}
}
