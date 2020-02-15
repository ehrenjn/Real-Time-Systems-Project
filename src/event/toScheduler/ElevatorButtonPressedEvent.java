package event.toScheduler;

import event.*;

/**
 * Represents a response to pressing a button
 */
public class ElevatorButtonPressedEvent extends Event {
	public static final String NAME = "ElevatorButtonPressedEvent";
	private int desiredFloor;
	
	public ElevatorButtonPressedEvent(int desiredFloor, String name, int recipientId, int senderId) {
		super(name, recipientId, senderId);
		this.desiredFloor = desiredFloor;
	}
	
	public int getDesiredFloor() {
		return desiredFloor;
	}
}
