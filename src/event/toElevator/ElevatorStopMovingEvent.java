package event.toElevator;

import event.*;

public class ElevatorStopMovingEvent extends Event {
	public ElevatorStopMovingEvent(String name, int recipientId, int senderId) {
		super(name, recipientId, senderId);
	}
}
