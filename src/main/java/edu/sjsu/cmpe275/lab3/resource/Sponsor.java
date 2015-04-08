package edu.sjsu.cmpe275.lab3.resource;

import org.hibernate.validator.constraints.NotEmpty;

public class Sponsor 

{
	private long id;
	@NotEmpty (message = "Sponsor name is mandatory")
    private String name;
    private String description;
    private String address;
    //private Address address;
    
	/*public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}*/
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
