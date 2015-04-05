package edu.sjsu.cmpe275.lab3.RestPersistenceApplication;
import org.hibernate.Session;

public class HibernateHandler 
{
	public Player createPlayer(Player player)
	//public static void main(String[] args) throws Exception 
	{
		System.out.println("create player");
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
//		Player player = new Player();
//		player.setFirstname("Rach");
//		player.setLastname("Kashyap");
//		player.setEmail("sindhu");
//		player.setId(124);
//		
		session.save(player);
		session.getTransaction().commit();
		System.out.println(player.getId());
		return player;
	}

}
