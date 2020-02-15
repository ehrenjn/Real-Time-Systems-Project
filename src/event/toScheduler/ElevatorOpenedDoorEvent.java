package event.toScheduler;

import event.*;

/**
 * Represents a response to opening a door
 */
public class ElevatorOpenedDoorEvent extends Event{

	public ElevatorOpenedDoorEvent(String name, int recipientId, int senderId) {
		super(name, recipientId, senderId);
	}

}
