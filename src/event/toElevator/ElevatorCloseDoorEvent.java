package event.toElevator;

import event.*;

public class ElevatorCloseDoorEvent extends Event {
	public ElevatorCloseDoorEvent(String name, int recipientId, int senderId) {
		super(name, recipientId, senderId);
	}
}
