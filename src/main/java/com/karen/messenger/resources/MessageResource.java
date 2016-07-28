package com.karen.messenger.resources;

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

import com.karen.messenger.model.Message;
import com.karen.messenger.service.MessageService;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService ms = new MessageService();
	
	@GET
	public List<Message> getMessages() {
		System.out.println(new Date());
		return ms.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessages(@PathParam("messageId") long id) {
		return ms.getMessage(id);
	}
	
	@POST
	public void addMessage(Message message) {
		System.out.println(message.getMessage());
		ms.addMessage(message);
	}
	
	@PUT
	@Path("/{messageId}")
	public void getMessages(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		ms.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		ms.removeMessage(id);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
