package edu.sjsu.cmpe275.lab3.handler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javassist.expr.Instanceof;

import org.hibernate.Session;

import edu.sjsu.cmpe275.lab3.resource.*;
import edu.sjsu.cmpe275.lab3.util.HibernateUtil;

public class SponsorHandler {
	
	Session session;
	Sponsor sponsor;
	
	/*
	 * Method to create a new sponsor
	 * returns sponsor object
	 */
	public Sponsor createSponsor(String name,String description,String state,String street,String city,String zip)
	{
		sponsor = new Sponsor();
		TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        df.setTimeZone(tz);
		sponsor.setId(Long.parseLong(df.format(new Date())));
		sponsor=populateSponsorValues(name,description,state,street,city,zip,sponsor);
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(sponsor);
		session.getTransaction().commit();
		session.close();
		return sponsor;
	}

	
	/*
	 * Populate value to sponsor bean and returns sponsor object
	 * called from create sponsor
	 */
	private Sponsor populateSponsorValues(String name, String description,
			String state, String street, String city, String zip,Sponsor sponsor) {
		
		sponsor.setName(name);
		Address address = new Address();
		if(description!=null)
		{
		sponsor.setDescription(description);
		}
		if(city!=null)
		{
		address.setCity(city);
		}
		if(street!=null)
		{
		address.setStreet(street);
		}
		if(zip!=null)
		{
		address.setZip(zip);
		}
		if(state!=null)
		{
		address.setState(state);
		}
		if(address!=null)
		{
		sponsor.setAddress("temp value");
		}
		return sponsor;
	}

	/*
	 * Method to get Sponsor from database
	 * id as input parameter
	 * returns palyer object
	 */
	public Sponsor getSponsor(long id)
	{
		session = HibernateUtil.getSessionFactory().openSession();
		Sponsor sponsor=(Sponsor)session.get(Sponsor.class,id);
		Sponsor returnObject = null;
		if(null!=sponsor)
		{
			session.close();
			returnObject= sponsor;
		}
		return returnObject;
	}
	
	/*
	 * Mthod to delete a Sponsor based on id
	 * id as input parameter
	 * returns deleted Sponsor object
	 */
	public Sponsor delete(long id)
	{
		Sponsor returnObject=null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Sponsor sponsor=(Sponsor)session.get(Sponsor.class,id);
		returnObject = sponsor;
		session.delete(sponsor);
		session.getTransaction().commit();
		session.close();
		return returnObject;
	}
	
	public Sponsor updateSponsor(long id,String name,String description,String state,String street,String city,String zip) 
	{
		sponsor = new Sponsor();
		sponsor.setId(id);
		sponsor=populateSponsorValues(name,description,state,street,city,zip,sponsor);
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.merge(sponsor);
		session.getTransaction().commit();
		session.close();
		return sponsor;
	}
	
	/*
	 * Method to check if user already exists.
	 * player id as input parameter
	 * returns a boolean value based on result
	 */
	public boolean checksponsorExists(long id)
	{
		session = HibernateUtil.getSessionFactory().openSession();
		Sponsor sponsor=(Sponsor)session.get(Sponsor.class,id);
		if(sponsor==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
}
