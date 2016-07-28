package com.karen.messenger.model;

import java.util.Date;

public class Comment {

	private long id;
	private long messageId;
    private String message;
    private Date created;
    private String author;
    
    public Comment() {
    	this.created = new Date();
    }
    
    public Comment(long id, String message, String author) {
    	this.id = id;
    	this.message = message;
    	this.author = author;
    	this.created = new Date();
    }
    
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
    
    

}
