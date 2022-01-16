package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * Kacey morris and Alex vergara
 * Milestone
 * 10/22/2021
 */
public class ProductModel {
	
	// properties
	public int id;
	
	@NotNull(message="Vacation Name is a required field")
	@Size(min=3, max=32, message="Vacation Name must be between 3 and 32 characters")
	public String vacationName;

	@NotNull(message="Price is a required field")
	public int price;
	
	@NotNull(message="Location is a required field")
	@Size(min=2, max=50, message="Location must be between 2 and 50 characters")
	public String location;
	
	@Size(min=2, max=50, message="Description must be between 2 and 50 characters")
	public String details;
	
	public int availability;
	
	@NotNull(message="Contact is a required field")
	@Size(min=2, max=50, message="Description must be between 2 and 50 characters")
	public String contact;
	
	public String image;
	
	public ProductModel()
	{
		
	}
	
	// data constructor
	public ProductModel(int id2, String vacationName2, int price2, String location2, String details2, int availability2, String contact2, String image) {
		id = id2;
		vacationName = vacationName2;
		price = price2;
		location = location2;
		details = details2;
		availability = availability2;
		contact = contact2;
		this.image = image;
		// System.out.println("In model constructor id is " + this.id + " " + this.getID());
	}
	
	// to string method
	public String toString() {
		return "Product Model [Vacation Name = " + this.vacationName + " price = " + this.price + " , this.location = " + this.location + "]";
	}
	
	// getters and setters
	public String getVacationName() {
		return vacationName;
	}

	public void setVacationName(String vacationName) {
		this.vacationName = vacationName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int newID) {
		this.id = newID;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
}