package event;

import java.io.*;

public class Event implements java.io.Serializable {
	private String name;
	private int recipientId;
	private int senderId;
	
	public Event(String name, int recipientId, int senderId) {
		this.name = name;
		this.recipientId = recipientId;
		this.senderId = senderId;
	}
	
	public String getName() {
		return name;
	}
	
	public int getRecipient() {
		return recipientId;
	}
	
	public int getSender() {
		return senderId;
	}

	@Override
	public String toString() {
		return "Event [name=" + name + ", recipientId=" + recipientId + ", senderId=" + senderId + "]";
	}
}
