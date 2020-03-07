package event.toScheduler;

import event.Event;

/**
 * Creates an event that represents a request from floor subsystem to receive a new event from the scheduler
 *
 */

public class RequestForFloorMessageEvent extends Event {
	public static final String NAME = "RequestForFloorMessageEvent";
	
	/**
	 * Creates a new RequestForFloorMessageEvent
	 * @param recipientId the id of the recipient
	 * @param senderId the id of the sender
	 */
	public RequestForFloorMessageEvent(int recipientId, int senderId) {
		super(NAME, recipientId, senderId);
	}
}
