package event.toScheduler;

import event.*;

/**
 * Represents a response to stopping an elevator
 */
public class ElevatorStoppedEvent extends Event{
	
	public ElevatorStoppedEvent(String name, int recipientId, int senderId) {
		super(name, recipientId, senderId);
	}
}
