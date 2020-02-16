package event.toScheduler;

import event.*;

/**
 * Represents a response to the elevator holding its door open for enough time for people to get on and off
 */
public class ElevatorOpenedDoorEvent extends Event {
	public static final String NAME = "ElevatorHeldDoorOpenEvent";

	/**
	 * 
	 * @param recipientId the id of the recipient of this event
	 * @param senderId the id of the sender of this event
	 */
	public ElevatorOpenedDoorEvent(int recipientId, int senderId) {
		super(NAME, recipientId, senderId);
	}

}
