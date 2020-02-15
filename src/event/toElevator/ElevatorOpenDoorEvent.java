package event.toElevator;

import event.*;

public class ElevatorOpenDoorEvent extends Event {
	public ElevatorOpenDoorEvent(String name, int recipientId, int senderId) {
		super(name, recipientId, senderId);
	}
}
