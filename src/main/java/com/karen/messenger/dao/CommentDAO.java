package com.karen.messenger.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.karen.messenger.database.DbConnection;
import com.karen.messenger.model.Comment;
import com.karen.messenger.model.Message;


public class CommentDAO {
	private Connection conex;

    public CommentDAO() {
        conex = DbConnection.getConnection();
    }
	
	public Comment getById(long messageId, long id) throws SQLException {
		Comment comment = null;
		String query = "SELECT * FROM comment WHERE id = ? AND messageId = ?";
		PreparedStatement stmt = conex.prepareStatement(query);
		stmt.setLong(1, id);
		stmt.setLong(2, messageId);
		ResultSet result = stmt.executeQuery();
		if(result.next()) {
			comment = new Comment();
			comment.setId(result.getLong("id"));
			comment.setMessageId(result.getLong("messageId"));
			comment.setMessage(result.getString("message"));
			comment.setAuthor(result.getString("author"));
			comment.setCreated(result.getDate("created"));
			
		}
		stmt.close();
		DbConnection.desconectar();
		return comment;
	}
	
	public void add(Comment comment) throws SQLException {
		String sql = "INSERT INTO comment (messageId, author, message, created) VALUES (?,?,?,?)";
		PreparedStatement stmt = conex.prepareStatement(sql);
		stmt.setLong(1, comment.getMessageId());
		stmt.setString(2, comment.getAuthor());
		stmt.setString(3, comment.getMessage());
		stmt.setDate(4, new java.sql.Date(comment.getCreated().getTime()));
		stmt.executeUpdate();

		stmt.close();
		DbConnection.desconectar();

	}

	public void update(Comment comment) throws SQLException {
		String sql = "Update comment set message = ? WHERE id = ? AND messageId = ?";
		PreparedStatement stmt = conex.prepareStatement(sql);
		stmt.setString(1, comment.getMessage());
		stmt.setLong(2, comment.getId());
		stmt.setLong(3, comment.getMessageId());
		stmt.executeUpdate();

		stmt.close();
		DbConnection.desconectar();
	}

	public void delete(long messageId, long commentId) throws SQLException {
		System.out.println(messageId +" - "+commentId);
		String sql = "Delete from comment where id = ? AND messageId = ? ";
		PreparedStatement stmt = conex.prepareStatement(sql);
		stmt.setLong(1, commentId);
		stmt.setLong(2, messageId);
		stmt.executeUpdate();

		stmt.close();
		DbConnection.desconectar();

	}

	public List<Comment> getAll(long messageId) throws SQLException {
		String query = "select * from comment where messageId = ?";
		PreparedStatement stmt = conex.prepareStatement(query);
		stmt.setLong(1, messageId);
		ResultSet result = stmt.executeQuery();
		List<Comment> comments = new ArrayList<>();

		while(result.next()) {
			Comment comment = new Comment();
			comment.setId(result.getLong("id"));
			comment.setMessageId(result.getLong("messageId"));
			comment.setAuthor(result.getString("author"));
			comment.setMessage(result.getString("message"));
			comment.setCreated(result.getDate("created"));
			comments.add(comment);

		}

		stmt.close();
		result.close();
		DbConnection.desconectar();
		return comments;

	}

}
