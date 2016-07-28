package com.karen.messenger.service;


import java.sql.SQLException;
import java.util.List;

import com.karen.messenger.dao.CommentDAO;
import com.karen.messenger.model.Comment;


public class CommentService {
	CommentDAO dao = new CommentDAO();
	
	public List<Comment> getAllComments(long messageId) throws SQLException {
		return dao.getAll(messageId);
	}
	
	public Comment getComment(long messageId, long id) throws SQLException {
		return dao.getById(messageId, id);
	}
	
	public void addComment(Comment comment) throws SQLException {
		dao.add(comment);
	}
	
	public void updateComment(Comment comment) throws SQLException {
		dao.update(comment);
	}
	
	public void removeComment(long messageId, long id) throws SQLException {
		dao.delete(messageId, id);
	}
}
