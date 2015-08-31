package edu.sjsu.cmpe275.lab3.handler;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import edu.sjsu.cmpe275.lab3.resource.Address;
import edu.sjsu.cmpe275.lab3.resource.Opponent;
import edu.sjsu.cmpe275.lab3.resource.Player;
import edu.sjsu.cmpe275.lab3.resource.Sponsor;
import edu.sjsu.cmpe275.lab3.util.HibernateUtil;

public class PlayerHandler 
{
	Session session;
	Player player;
	Address address;
	
	/*
	 * Method to create a player using hibernate
	 * param attribute player
	 * returns player
	 */
	public Player createPlayer(String firstname,String lastname,String email,String description,String street,
			String city,String state,String zip,Long sponsor)
	{
		player = new Player();
		player=populatePlayerValues(firstname,lastname,email,description,street,city,state,zip);			
		if(null!=sponsor)
		{
			Sponsor s = getSponsorDetails(sponsor);
			if(s!=null)
			{
				player.setSponsor(s);
			}
		}
		
		System.out.println("player is:::"+player);
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(player);
		session.getTransaction().commit();
		System.out.println("id is :"+ player.getId());
		return player;
	}
	
	/*
	 * Method to get player
	 * id as input parameter
	 * returns palyer object
	 */
	public Player getPlayer(long id)
	{
		Player returnObject = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Player player=(Player)session.get(Player.class,id);
		if(null!=player)
		{
			Long player1 = player.getId();
			List <Long> opp = getOpponentList(player1);
			if(opp.size()>0)
			{
				player.setPlayerOpponents(opp);
			}
			returnObject= player;
		}
		return returnObject;
	}

	
	/*
	 * method to get the list of opponents for the player id
	 * return list of Opponent ids
	 */
	private List<Long> getOpponentList(long id) 
	{
		session = HibernateUtil.getSessionFactory().openSession();
		List<Long> oppList = new ArrayList<Long>();
		String hql1 = "from Opponent where player1= :player1";
		Query query = session.createQuery(hql1);
		query.setParameter("player1", id);
		List list = query.list();
		
		String hql2 = "from Opponent where player2= :player2";
		Query query2 = session.createQuery(hql2);
		query2.setParameter("player2", id);
		List list2 = query2.list();
		
		//session.close();
		if(list != null)
		{
			for(int i = 0;i<list.size(); i++)
			{
				Opponent opps = (Opponent)list.get(i);
				System.out.println("Opponent(player2) is "+opps.getPlayer2());
				oppList.add(opps.getPlayer2());
			}
		}
		if(list2 != null)
		{
			for(int i = 0;i<list2.size(); i++)
			{
				Opponent opp2 = (Opponent)list2.get(i);
				System.out.println("Opponent(player1) is "+opp2.getPlayer1());
				if(!oppList.contains(opp2.getPlayer1()))
				{
					oppList.add(opp2.getPlayer1());
				}
			}
		}
		return oppList;
	}
	/*
	 * method to update a player
	 * return the updated player object
	 */

	public Player updatePlayer(long id,String firstname,String lastname,String email,String description,String street,
			String city,String state,String zip,Long sponsor) 
	{
		player = new Player();
		player.setId(id);
		player=populatePlayerValues(firstname,lastname,email,description,street,city,state,zip);
		if(null != sponsor)
		{
			Sponsor s = getSponsorDetails(sponsor);
			if(s != null)
			{
				player.setSponsor(s);
			}
		}
		session = HibernateUtil.getSessionFactory().openSession();
		
		long player1 = player.getId();
		List <Long> opp = getOpponentList(player1);
		if(opp.size()>0)
		{
		player.setPlayerOpponents(opp);
		}
		session.beginTransaction();
		session.merge(player);
		session.getTransaction().commit();
		session.close();
		return player;
	}

	
	/*
	 * Mthod to delete a player based on id
	 * id as input parameter
	 * returns player object
	 */
	public Player delete(long id)
	{
		Player returnObject=null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Player player=(Player)session.get(Player.class,id);
		returnObject = player;
		session.delete(player);
		session.getTransaction().commit();
		//session.close();
		return returnObject;
	}
	
	/*
	 * Method to check if user already exists.
	 * player id as input parameter
	 * returns a boolean value based on result
	 */
	public boolean checkPlayerExists(long id)
	{
		session = HibernateUtil.getSessionFactory().openSession();
		Player player=(Player)session.get(Player.class,id);
		if(player==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	/*
	 * method to get the sponsor details to add it to the the player object
	 * returns a Sponsor object
	 */
	public Sponsor getSponsorDetails(long sponsor) 
	{
		session = HibernateUtil.getSessionFactory().openSession();
		Sponsor s = (Sponsor)session.get(Sponsor.class,sponsor);
		//session.close();
		return s;
	}
	
	/*
	 * Method to populate player bean and address bean
	 * returns player object
	 */
	public Player populatePlayerValues(String firstname,String lastname,String email,String description,
			String street,String city,String state,String zip)
	{
		player.setFirstname(firstname);
		player.setLastname(lastname);
		player.setEmail(email);
		if(description!=null)
		{
			player.setDescription(description);
		}
		if(street!=null || city!=null || state!=null || zip!=null)
		{
			address = new Address(street,city,state,zip);
			player.setAddress(address);
		}
		return player;	
	}
	
	
	/*
	 * Method to check if the email address is unique in the players table
	 * returns a boolean variable
	 */
	public boolean checkIfEmailAlreadyRegistered(String email)
	{
		session = HibernateUtil.getSessionFactory().openSession();
		String hql = "from Player where email= :emailid";
		Query query = session.createQuery(hql);
		query.setParameter("emailid",email);
		List i  = query.list();	
		if(i.size()>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
