package com.karen.messenger.service;


import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.karen.messenger.model.Comment;
import com.karen.messenger.model.Message;


public class CommentService {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    
	
	public List<Comment> getAllComments(long messageId) throws SQLException {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
         
        List<Comment> comments = (List<Comment>) session.createQuery( "from Comment where messageId = " + messageId ).list();
         
        session.getTransaction().commit();
        session.close();
        return comments;
	}
	
	public Comment getComment(long messageId, long id) throws SQLException {
		//return dao.getById(messageId, id);
		Session session = sessionFactory.openSession();
        session.beginTransaction();
         
        Comment comment = (Comment) session.createQuery("from Comment where messageId = "+ messageId + " and id = " + id).uniqueResult();
         
        session.getTransaction().commit();
        session.close();
        return comment;
	}
	
	public void addComment(Comment comment) throws SQLException {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
         
      	session.save(comment);
         
        session.getTransaction().commit();
        session.close();
	}
	
	public void updateComment(Comment comment) throws SQLException {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
         
      	session.update(comment);
         
        session.getTransaction().commit();
        session.close();
	}
	
	public void removeComment(long messageId, long id) throws SQLException {
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        Comment comment =  session.get(Comment.class, id);
        session.delete(comment);
        session.getTransaction().commit();
        session.close();
	}
}
