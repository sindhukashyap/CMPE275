package edu.sjsu.cmpe275.lab3.handler;
import org.hibernate.Session;
import edu.sjsu.cmpe275.lab3.resource.Player;
import edu.sjsu.cmpe275.lab3.util.HibernateUtil;

public class HibernateHandler 
{
	public Player createPlayer(Player player)
	 
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(player);
		session.getTransaction().commit();
		
		return player;
	}
	
	public boolean checkPlayerExists(long id)
	{
		boolean idExists = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
//		Query query = session.createQuery("from player where id = :idDb ");
//		query.setLong("idDb",id);
		Player player = (Player) session.get(Player.class, id);
		if(player != null)
		{
			if(player.getId() != 0L)
			{
				if(player.getId() == id)
				{
					idExists = true;
				}
			}
		}
		session.close();
		return idExists;
		
	}

	public Player updatePlayer(Player player) 
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.merge(player);
		session.getTransaction().commit();
		session.close();
		return player;
	}

}
