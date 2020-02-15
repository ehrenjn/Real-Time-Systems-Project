package event.toScheduler;

import event.*;

/**
 * Represents a response to closing a door
 */
public class ElevatorClosedDoorEvent extends Event {
	public static final String NAME = "ElevatorClosedDoorEvent";
	
	public ElevatorClosedDoorEvent(String name, int recipientId, int senderId) {
		super(name, recipientId, senderId);
	}
	
}
