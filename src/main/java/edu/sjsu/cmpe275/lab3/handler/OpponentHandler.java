package edu.sjsu.cmpe275.lab3.handler;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import edu.sjsu.cmpe275.lab3.resource.Opponent;
import edu.sjsu.cmpe275.lab3.resource.Player;
import edu.sjsu.cmpe275.lab3.util.HibernateUtil;

public class OpponentHandler 
{
	Session session;
	public Opponent newOpponent(Opponent opponent)
	{
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(opponent);
		session.getTransaction().commit();
		return opponent;
	}
	
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

	public boolean checkPlayersExist(long id1, long id2) 
	{
		boolean playersExist = false;
		session = HibernateUtil.getSessionFactory().openSession();
		Player player1 = (Player) session.get(Player.class, id1);
		Player player2 = (Player) session.get(Player.class, id2);
		if(player1 != null || player2 != null)
		{
			playersExist = true;
		}
		session.close();
		return playersExist;
	}

	public Iterator buildOpponentQuery(long id1,long id2)
	{
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Opponent where player1= :p1 and player2= :p2";
		Query query = session.createQuery(hql);
		query.setParameter("p1", id1);
		query.setParameter("p2", id2);
		List list = query.list();
		session.close();
		return list.iterator();
	}
	
	public boolean checkPlayersAsOpponents(long id1, long id2) 
	{
		
		Iterator i = buildOpponentQuery(id1,id2);
		if(i.hasNext())
		{
			return true;
		}
		else return false;
	}
}
