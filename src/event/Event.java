package event;

import java.io.Serializable;
import java.net.InetAddress;

public class Event implements Serializable {
	private String name;
	private int recipientId;
	private int senderId;
	private int senderPort;
	private InetAddress senderIp;
	private int toPort; // super ugly having all these to and sender variables, should refactor if we have time...
	private InetAddress toIp;
	
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
	
	public InetAddress getSenderIp() {
		return senderIp;
	}
	
	public void setSenderIp(InetAddress ip) {
		senderIp = ip;
	}
	
	public int getSenderPort() {
		return senderPort;
	}
	
	public void setSenderPort(int port) {
		senderPort = port;
	}
	
	public InetAddress getToIp() {
		return toIp;
	}
	
	public void setToIp(InetAddress ip) {
		toIp = ip;
	}
	
	public int getToPort() {
		return toPort;
	}
	
	public void setToPort(int port) {
		toPort = port;
	}
}
