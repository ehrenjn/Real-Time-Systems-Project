package event.toScheduler;

import event.*;

/**
 * Represents a response to closing a door
 */
public class ElevatorClosedDoorEvent extends Event {
	public static final String NAME = "ElevatorClosedDoorEvent";
	
	/**
	 * 
	 * @param recipientId the id of the recipient of this event
	 * @param senderId the id of the sender of this event
	 */
	public ElevatorClosedDoorEvent(int recipientId, int senderId) {
		super(NAME, recipientId, senderId);
	}
	
}
