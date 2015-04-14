package edu.sjsu.cmpe275.lab3.handler;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.sjsu.cmpe275.lab3.resource.Address;
import edu.sjsu.cmpe275.lab3.resource.Sponsor;
import edu.sjsu.cmpe275.lab3.util.HibernateUtil;

public class SponsorHandler {
	
	Session session;
	Sponsor sponsor;
	Address address ;
	
	/*
	 * Method to create a new sponsor
	 * returns sponsor object
	 */
	public Sponsor createSponsor(String name,String description,String state,String street,String city,String zip)
	{
		sponsor = new Sponsor();
		sponsor=populateSponsorValues(name,description,state,street,city,zip);
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
		String state, String street, String city, String zip) {
		sponsor.setName(name);
		
		if(description!=null)
		{
			sponsor.setDescription(description);
		}
		if(street!=null || city!=null || state!=null || zip!=null)
		{
			address = new Address(street,city,state,zip);
			sponsor.setAddress(address);
			System.out.println("populatedSponsor's Address ::city is "+address.getCity());
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
	
	/*
	 * Method to check if the player has a sponsor based on sponsor id
	 * returns true if it has he sponsor
	 * if true sponsor will not be deleted
	 */
	public boolean PlayerHasSponsorToDelete(long id)
	{
		session = HibernateUtil.getSessionFactory().openSession();
		String hql = "from Player where sponsor_id = :sponsor_id";
		Query query = session.createQuery(hql);
		query.setParameter("sponsor_id",id);
		List results = query.list();
		session.close();
		if(results.size()>0)
		{
			System.out.println("size is greater so im giving trueee");
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Sponsor updateSponsor(long id,String name,String description,String state,String street,String city,String zip) 
	{
		sponsor = new Sponsor();
		sponsor.setId(id);
		sponsor=populateSponsorValues(name,description,state,street,city,zip);
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
