package event.toElevator;

import event.*;

public class ElevatorCloseDoorEvent extends Event {
	public static final String NAME = "ElevatorCloseDoorEvent";
	
	public ElevatorCloseDoorEvent(int recipientId, int senderId) {
		super(NAME, recipientId, senderId);
	}
}
