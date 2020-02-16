package event.toElevator;

import event.*;

public class ElevatorStopMovingEvent extends Event {
	public static final String NAME = "ElevatorStopMovingEvent";
	
	public ElevatorStopMovingEvent(int recipientId, int senderId) {
		super(NAME, recipientId, senderId);
	}
}
