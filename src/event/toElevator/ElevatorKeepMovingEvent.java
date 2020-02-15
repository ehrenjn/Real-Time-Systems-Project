package event.toElevator;

import event.*;

/**
 * Tells the elevator to keep moving
 *
 */
public class ElevatorKeepMovingEvent extends Event {
	public ElevatorKeepMovingEvent(String name, int recipientId, int senderId) {
		super(name, recipientId, senderId);
	}
}
