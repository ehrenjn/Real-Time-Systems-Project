package event.toElevator;

import event.*;

public class ElevatorOpenDoorEvent extends Event {
	public static final String NAME = "ElevatorOpenDoorEvent";
	public ElevatorOpenDoorEvent(int recipientId, int senderId) {
		super(NAME, recipientId, senderId);
	}
}
