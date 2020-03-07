package event.toScheduler;

import event.Event;

/**
 * Creates an event that represents a request from elevator subsystem to receive a new event from the scheduler
 *
 */

public class RequestForElevatorMessageEvent extends Event {
	public static final String NAME = "RequestForElevatorMessageEvent";
	
	/**
	 * Creates a new RequestForElevatorMessageEvent
	 * @param recipientId the id of the recipient
	 * @param senderId the id of the sender
	 */
	public RequestForElevatorMessageEvent(int recipientId, int senderId) {
		super(NAME, recipientId, senderId);
	}
}
