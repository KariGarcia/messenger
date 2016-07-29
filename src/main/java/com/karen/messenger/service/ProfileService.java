package com.karen.messenger.service;


import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.karen.messenger.model.Profile;

public class ProfileService {
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	public List<Profile> getAllProfiles() throws SQLException {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
         
        List<Profile> profiles = (List<Profile>) session.createQuery( "from Profile" ).list();
         
        session.getTransaction().commit();
        session.close();
        return profiles;
	}
	
	public Profile getProfile(long id) throws SQLException {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
         
        Profile profile = (Profile) session.get(Profile.class, id);
         
        session.getTransaction().commit();
        session.close();
        return profile;
	}
	
	public Profile getProfileByProfileName(String profileName) throws SQLException {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        
      	Profile profile = (Profile) session.createQuery("from Profile where profileName = '" + profileName + "'").uniqueResult();
         
        session.getTransaction().commit();
        session.close();
        
        return profile;
	}
	
	public void addProfile(Profile profile) throws SQLException {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
         
      	session.save(profile);
         
        session.getTransaction().commit();
        session.close();
	}
	
	public void updateProfile(Profile profile) throws SQLException {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
         
      	session.update(profile);
         
        session.getTransaction().commit();
        session.close();
	}
	
	public void removeProfile(long id) throws SQLException {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        Profile profile = session.get(Profile.class, id);
      	session.delete(profile);
         
        session.getTransaction().commit();
        session.close();
	}

}
