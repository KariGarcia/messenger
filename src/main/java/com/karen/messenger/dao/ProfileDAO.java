package com.karen.messenger.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.karen.messenger.database.DbConnection;
import com.karen.messenger.model.Message;
import com.karen.messenger.model.Profile;

public class ProfileDAO {

	private Connection conex;

    public ProfileDAO() {
        conex = DbConnection.getConnection();
    }
	
	public Profile getById(long id) throws SQLException {
		Profile profile = null;
		String query = "SELECT * FROM profile WHERE id = ? ";
		PreparedStatement stmt = conex.prepareStatement(query);
		stmt.setLong(1, id);
		ResultSet result = stmt.executeQuery();
		if(result.next()) {
			profile = new Profile();
			profile.setId(result.getLong("id"));
			profile.setFirstName(result.getString("FirstName"));
			profile.setLastName(result.getString("LastName"));
			profile.setProfileName(result.getString("ProfileName"));
			
		}
		stmt.close();
		DbConnection.desconectar();

		return profile;
	}
	
	public Profile getProfileByProfileName(String profileName) throws SQLException {
		Profile profile = null;
		String query = "SELECT * FROM profile WHERE profileName = ? ";
		PreparedStatement stmt = conex.prepareStatement(query);
		stmt.setString(1, profileName);
		ResultSet result = stmt.executeQuery();
		if(result.next()) {
			profile = new Profile();
			profile.setId(result.getLong("id"));
			profile.setFirstName(result.getString("FirstName"));
			profile.setLastName(result.getString("LastName"));
			profile.setProfileName(result.getString("ProfileName"));
		}
		stmt.close();
		DbConnection.desconectar();

		return profile;
	}
	
	public void add(Profile profile) throws SQLException {

		String sql = "INSERT INTO profile (firstName, lastName, profileName) VALUES (?,?,?)";
		PreparedStatement stmt = conex.prepareStatement(sql);
		stmt.setString(1, profile.getFirstName());
		stmt.setString(2, profile.getLastName());
		stmt.setString(3, profile.getProfileName());
		stmt.executeUpdate();

		stmt.close();
		DbConnection.desconectar();

	}

	public void update(Profile profile) throws SQLException {

		String sql = "Update message set firstName = ?, lastName = ?, profileName = ? WHERE id = ?";
		PreparedStatement stmt = conex.prepareStatement(sql);
		stmt.setString(1, profile.getFirstName());
		stmt.setString(2, profile.getLastName());
		stmt.setString(3, profile.getProfileName());
		stmt.setLong(4, profile.getId());
		stmt.executeUpdate();

		stmt.close();
		DbConnection.desconectar();
	}
	
	public void updateByProfileName(Profile profile) throws SQLException {

		String sql = "Update message set firstName = ?, lastName = ? WHERE profileName = ?";
		PreparedStatement stmt = conex.prepareStatement(sql);
		stmt.setString(1, profile.getFirstName());
		stmt.setString(2, profile.getLastName());
		stmt.setString(3, profile.getProfileName());
		stmt.executeUpdate();

		stmt.close();
		DbConnection.desconectar();
	}
	public void delete(long profileId) throws SQLException {
		String sql = "Delete from profile where id = ? ";
		PreparedStatement stmt = conex.prepareStatement(sql);
		stmt.setLong(1, profileId);
		stmt.executeUpdate();

		stmt.close();
		DbConnection.desconectar();

	}

	public List<Profile> getAll() throws SQLException {

		String query = "select * from profile";
		PreparedStatement stmt = conex.prepareStatement(query);
		ResultSet result = stmt.executeQuery(query);
		List<Profile> profiles = new ArrayList<>();

		while(result.next()) {
			Profile profile = new Profile();
			profile.setId(result.getLong("id"));
			profile.setFirstName(result.getString("FirstName"));
			profile.setLastName(result.getString("LastName"));
			profile.setProfileName(result.getString("ProfileName"));
			profiles.add(profile);

		}

		stmt.close();
		result.close();
		DbConnection.desconectar();
		return profiles;

	}

}