package edu.sjsu.cmpe275.lab3.resource;

import java.util.List;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Proxy(lazy=false)
public class Player{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long id;
	@NotEmpty (message = "Please enter firstname") 
	String firstname;
	
	@NotEmpty (message = "Please enter lastname") 
	String lastname;
	
	@NotEmpty (message = "Please enter email id")	//Email should not empty
	String email;
	
	String description;
	
	Sponsor sponsor;//should be a Sponsor object;
	Address address;
	List<Long> playerOpponents;
	
	public Player()
	{
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Sponsor getSponsor() {
		return sponsor;
	}
	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public List<Long> getPlayerOpponents() {
		return playerOpponents;
	}

	public void setPlayerOpponents(List<Long> playerOpponents) {
		this.playerOpponents = playerOpponents;
	}
	
}
