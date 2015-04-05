package edu.sjsu.cmpe275.lab3.RestPersistenceApplication;

public class Player 
{
	long id;
	String firstname;
	String lastname;
	String email;
	String address;
	String description;
	String sponsor;
	
	public Player()
	{
		
	}
	
	public Player(long id, String firstname,String lastname,
			String email, String address, String description,
			String sponsor)
			
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.id = id;
		this.address = address;
		this.description = description;
		this.sponsor = sponsor;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

}
