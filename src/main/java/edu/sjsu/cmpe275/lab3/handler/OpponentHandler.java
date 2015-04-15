package edu.sjsu.cmpe275.lab3.handler;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.sjsu.cmpe275.lab3.resource.Opponent;
import edu.sjsu.cmpe275.lab3.resource.Player;
import edu.sjsu.cmpe275.lab3.util.HibernateUtil;

public class OpponentHandler 
{
	Session session;
	Opponent opponent;
	
	/*
	 * Adding new opponent
	 * returns the added opponent
	 */
	public Opponent newOpponent(long id1, long id2)
	{
		opponent = new Opponent();
		opponent.setPlayer1(id1);
		opponent.setPlayer2(id2);
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(opponent);
		session.getTransaction().commit();
		opponent.setMsg("Opponents added successfully");
		return opponent;
	}
	
	/*
	 * deleting opponent
	 * returns integer to indicate success/failure
	 */
	
	public int deleteOpponent(long id1, long id2)
	{
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "delete from Opponent where player1= :p1 and player2= :p2";
		Query query = session.createQuery(hql);
		query.setParameter("p1", id1);
		query.setParameter("p2", id2);
		int result = query.executeUpdate();
		session.getTransaction().commit();
		return result;
	}

	/*
	 * Checks if both the players already exist
	 * returns a boolean variable 
	 */
	public boolean checkPlayersExist(long id1, long id2) 
	{
		boolean playersExist = false;
		session = HibernateUtil.getSessionFactory().openSession();
		Player player1 = (Player) session.get(Player.class, id1);
		Player player2 = (Player) session.get(Player.class, id2);
		if(player1 != null && player2 != null)
		{
			playersExist = true;
		}
		session.close();
		return playersExist;
	}

	/*
	 * Checks if the two players are already opponents
	 * returns a boolean variable 
	 */
	public boolean checkAlreadyOpponents(long id1, long id2) 
	{
		session = HibernateUtil.getSessionFactory().openSession();
		String hql = "from Opponent o where o.player1= :p1 and o.player2= :p2 OR (o.player1= :p2 and o.player2= :p1)";
		Query query = session.createQuery(hql);
		query.setParameter("p1",id1);
		query.setParameter("p2",id2);
		List i  = query.list();
		System.out.println("fromm checking if they are already opponents"+i.size());
		session.close();
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
