package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.business.ProductBusinessServiceInterface;
import com.gcu.model.ProductModel;

@RestController
@RequestMapping("/api/v1/products")
public class APIController {
	@Autowired
	private ProductBusinessServiceInterface service;
	
	@GetMapping("/")
	public ResponseEntity<?> showAllProducts(Model model) {
		try {
			List<ProductModel> products = service.getProducts();
			if (products != null) {
				return new ResponseEntity<>(products, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable(name="id") String id) {
		try {
			ProductModel product = service.getOne(Integer.valueOf(id));
			if (product != null) {
				return new ResponseEntity<>(product, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/search/{searchTerm}")
	public ResponseEntity<?> searchProducts(@PathVariable(name="searchTerm") String searchTerm) {
		List<ProductModel> results = null;
		try {
			results = service.searchProducts(searchTerm);
			if (results != null ) {
				return new ResponseEntity<>(results, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<?> addProduct(@RequestBody ProductModel addProduct) {
		String results = null;
		try {
			results = service.addOne(addProduct) + "";
			if (results != "") {
				return new ResponseEntity<>(results, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(name="id") String id) {
		boolean results = false;
		try {
			results = service.deleteOne(Integer.valueOf(id));
			if (results) {
				return new ResponseEntity<>(results, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<?> updateOrder(@RequestBody ProductModel updateProduct) {
		ProductModel results = null;
		try {
			results = service.updateOne(updateProduct.getId(), updateProduct);
			if (results != null) {
				return new ResponseEntity<>(results, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
