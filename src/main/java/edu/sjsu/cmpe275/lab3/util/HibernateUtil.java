package edu.sjsu.cmpe275.lab3.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class HibernateUtil {
 
    private static final SessionFactory sessionFactory = buildSessionFactory();
 
    /*
     * Singleton method get the hibernate session
     * returns the created SessionFactory object
     */
    private static SessionFactory buildSessionFactory() 
    {
        try 
        {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) 
        {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 /*
  * method that Closes caches and connection pools
  */
    public static void shutdown() 
    {
    	
    	getSessionFactory().close();
    }
 
}