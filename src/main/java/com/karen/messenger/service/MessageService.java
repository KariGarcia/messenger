package com.karen.messenger.service;


import java.util.List;

import com.karen.messenger.dao.MessageDAO;

import com.karen.messenger.model.Message;

public class MessageService {
	MessageDAO msdao = new MessageDAO();
	
	public List<Message> getAllMessages() {
		return msdao.getAll();
	}
	
	public Message getMessage(long id) {
		return msdao.getById(id);
	}
	
	public void addMessage(Message message) {
		msdao.add(message);
	}
	
	public void updateMessage(Message message) {
		msdao.update(message);
	}
	
	public void removeMessage(long id) {
		msdao.delete(id);
	}
}
