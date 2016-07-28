package com.karen.messenger.resources;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.karen.messenger.model.Profile;
import com.karen.messenger.service.ProfileService;

@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	ProfileService service = new ProfileService();
	
	@GET
	public List<Profile> getProfilees() throws SQLException {
		return service.getAllProfiles();
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfiles(@PathParam("profileName") String profileName) throws SQLException {
		Profile prof = service.getProfileByProfileName(profileName);
		return service.getProfile(prof.getId());
	}
	
	@POST
	public void addProfile(Profile profile) throws SQLException {
		service.addProfile(profile);
	}
	
	@PUT
	@Path("/{profileName}")
	public void updateProfile(@PathParam("profileName") String profileName, Profile profile) throws SQLException {
		profile.setProfileName(profileName);
		service.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName) throws SQLException {
		Profile perfil = service.getProfileByProfileName(profileName);
		service.removeProfile(perfil.getId());
	}
}
