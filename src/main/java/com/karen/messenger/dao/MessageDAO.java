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
import com.karen.messenger.model.Message;


public class MessageDAO {
	private Connection conex;

    public MessageDAO() {
        conex = DbConnection.getConnection();
    }
	
	public Message getById(long id) {
		Message message = null;
		try {
			
			String query = "SELECT * FROM message WHERE id = ? ";
			PreparedStatement stmt = conex.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet result = stmt.executeQuery();
			if(result.next()) {
				message = new Message();
				message.setId(result.getLong("id"));
				message.setMessage(result.getString("message"));
				message.setAuthor(result.getString("author"));
				message.setCreated(result.getDate("created"));
				
			}
			stmt.close();
			DbConnection.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return message;
	}
	
	public void add(Message message) {
		try {
			String sql = "INSERT INTO message (author, message, created) VALUES (?,?,?)";
			PreparedStatement stmt = conex.prepareStatement(sql);
			stmt.setString(1, message.getAuthor());
			stmt.setString(2, message.getMessage());
			stmt.setDate(3, new java.sql.Date(message.getCreated().getTime()));
			stmt.executeUpdate();

			stmt.close();
			DbConnection.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void update(Message message) {
		try {
			String sql = "Update message set author = ?, message = ?, created = ? WHERE id = ?";
			PreparedStatement stmt = conex.prepareStatement(sql);
			stmt.setString(1, message.getAuthor());
			stmt.setString(2, message.getMessage());
			stmt.setDate(3, new java.sql.Date(message.getCreated().getTime()));
			stmt.setLong(4, message.getId());
			stmt.executeUpdate();

			stmt.close();
			DbConnection.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void delete(long messageId) {
		try {
			String sql = "Delete from message where id = ? ";
			PreparedStatement stmt = conex.prepareStatement(sql);
			stmt.setLong(1, messageId);
			stmt.executeUpdate();

			stmt.close();
			DbConnection.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public List<Message> getAll() {

		try {
			String query = "select * from message";
			PreparedStatement stmt = conex.prepareStatement(query);
			ResultSet result = stmt.executeQuery(query);
			List<Message> messages = new ArrayList<>();

			while(result.next()) {
				Message message = new Message();
				message.setId(result.getLong("id"));
				message.setAuthor(result.getString("author"));
				message.setMessage(result.getString("message"));
				message.setCreated(result.getDate("created"));
				messages.add(message);

			}

			stmt.close();
			result.close();
			DbConnection.desconectar();
			return messages;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

}
