package event.toElevator;

import event.*;

public class ElevatorOpenDoorEvent extends Event {
	public static final String NAME = "ElevatorOpenDoorEvent";
	public ElevatorOpenDoorEvent(String name, int recipientId, int senderId) {
		super(name, recipientId, senderId);
	}
}
