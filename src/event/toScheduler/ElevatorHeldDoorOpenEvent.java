package event.toScheduler;

import event.*;

/**
 * Represents a response to the elevator holding its door open for enough time for people to get on and off
 */
public class ElevatorHeldDoorOpenEvent extends Event{
	public static final String NAME = "ElevatorHeldDoorOpenEvent";

	public ElevatorHeldDoorOpenEvent(String name, int recipientId, int senderId) {
		super(name, recipientId, senderId);
	}

}
