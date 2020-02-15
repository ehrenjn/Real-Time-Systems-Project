package event;

public class Event {
	private String name;
	private String recipientId;
	private String senderId;
	
	public Event(String name, String recipientId, String senderId) {
		this.name = name;
		this.recipientId = recipientId;
		this.senderId = senderId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRecipient() {
		return recipientId;
	}
	
	public String getSender() {
		return senderId;
	}
}
