package com.karen.messenger.service;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.karen.messenger.model.Message;

public class MessageService {
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	public List<Message> getAllMessages() {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
         
        List<Message> messages = (List<Message>) session.createQuery( "from Message" ).list();
         
        session.getTransaction().commit();
        session.close();
        return messages;
	}
	
	public Message getMessage(long id) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
         
        Message message = (Message) session.get(Message.class, id);
         
        session.getTransaction().commit();
        session.close();
        return message;
	}
	
	public void addMessage(Message message) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
         
      	session.save(message);
         
        session.getTransaction().commit();
        session.close();
	}
	
	public void updateMessage(Message message) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
         
      	session.update(message);
         
        session.getTransaction().commit();
        session.close();
	}
	
	public void removeMessage(long id) {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Message message =  session.get(Message.class, id);
        session.delete(message);
         
        session.getTransaction().commit();
        session.close();
	}
}
