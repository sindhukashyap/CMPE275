package edu.sjsu.cmpe275.lab3.handler;

import org.hibernate.Session;

import edu.sjsu.cmpe275.lab3.resource.Address;
import edu.sjsu.cmpe275.lab3.resource.Player;
import edu.sjsu.cmpe275.lab3.util.HibernateUtil;

public class PlayerHandler 
{
	Session session;
	
	/*
	 * Method to create a player using hibernate
	 * param attribute player
	 * returns player
	 */
	public Player createPlayer(Player player)
	 
	{
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
		session = HibernateUtil.getSessionFactory().openSession();
		Player player=(Player)session.get(Player.class,id);
		Player returnObject = null;
		if(null!=player)
		{
			session.close();
			returnObject= player;
		}
		return returnObject;
	}

	public Player updatePlayer(Player player) 
	{
		session = HibernateUtil.getSessionFactory().openSession();
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
		session.close();
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
	
}
