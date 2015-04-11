package edu.sjsu.cmpe275.lab3.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.sjsu.cmpe275.lab3.resource.Opponent;
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
		long player1 = player.getId();
		List <Long> opp = getOpponentList(player1);
		if(opp != null)
		{
			player.setPlayerOpponents(opp);
		}
		Player returnObject = null;
		if(null!=player)
		{
			session.close();
			returnObject= player;
		}
		return returnObject;
	}

	private List<Long> getOpponentList(long id) 
	{
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Long> oppList = new ArrayList<Long>();
		String hql1 = "from Opponent where player1= :player1";
		//String hql2 = "from Opponent where player2= :player1";
		Query query = session.createQuery(hql1);
		query.setParameter("player1", id);
		List list = query.list();
		if(list != null)
		{
			for(int i = 0;i<list.size(); i++)
			{
				Opponent opps = (Opponent)list.get(i);
				oppList.add(opps.getPlayer2());
			}
		}
		return oppList;
	}

	public Player updatePlayer(Player player) 
	{
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		long player1 = player.getId();
		List <Long> opp = getOpponentList(player1);
		player.setPlayerOpponents(opp);
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
