package com.karen.messenger.service;


import java.sql.SQLException;
import java.util.List;

import com.karen.messenger.dao.ProfileDAO;
import com.karen.messenger.model.Profile;

public class ProfileService {
	ProfileDAO dao = new ProfileDAO();
	
	public List<Profile> getAllProfiles() throws SQLException {
		return dao.getAll();
	}
	
	public Profile getProfile(long id) throws SQLException {
		return dao.getById(id);
	}
	
	public Profile getProfileByProfileName(String profileName) throws SQLException {
		return dao.getProfileByProfileName(profileName);
	}
	
	public void addProfile(Profile profile) throws SQLException {
		dao.add(profile);
	}
	
	public void updateProfile(Profile profile) throws SQLException {
		dao.update(profile);
	}
	
	public void removeProfile(long id) throws SQLException {
		dao.delete(id);
	}
}
