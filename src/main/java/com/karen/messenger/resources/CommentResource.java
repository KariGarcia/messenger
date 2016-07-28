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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.karen.messenger.model.Comment;
import com.karen.messenger.service.CommentService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	CommentService service = new CommentService();
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId) throws SQLException {
			return service.getAllComments(messageId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long id) throws SQLException {
		return service.getComment(messageId, id);
	}
	
	@POST
	public void addComment(@PathParam("messageId") long messageId, Comment comment) throws SQLException {
		comment.setMessageId(messageId);
		service.addComment(comment);
	}
	
	@PUT
	@Path("/{commentId}")
	public void updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long id, Comment comment) throws SQLException {
		comment.setId(id);
		comment.setMessageId(messageId);
		service.updateComment(comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long id) throws SQLException {
		service.removeComment(messageId, id);
	}
}
