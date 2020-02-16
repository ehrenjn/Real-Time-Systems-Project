package event.toScheduler;

import event.*;

/**
 * Represents a response to stopping an elevator
 */
public class ElevatorStoppedEvent extends Event {
	public static final String NAME = "ElevatorStoppedEvent";
	
	/**
	 * 
	 * @param recipientId the id of the recipient of this event
	 * @param senderId the id of the sender of this event
	 */
	public ElevatorStoppedEvent(int recipientId, int senderId) {
		super(NAME, recipientId, senderId);
	}
}
