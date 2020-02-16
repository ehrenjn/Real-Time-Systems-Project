package event.toScheduler;

import event.*;

/**
 * Represents a response to pressing a button
 */
public class ElevatorPressedButtonEvent extends Event {
	public static final String NAME = "ElevatorButtonPressedEvent";
	private int desiredFloor;
	
	/**
	 * 
	 * @param desiredFloor the floor the person is requesting
	 * @param recipientId the id of the recipient of this event
	 * @param senderId the id of the sender of this event
	 */
	public ElevatorPressedButtonEvent(int desiredFloor, int recipientId, int senderId) {
		super(NAME, recipientId, senderId);
		this.desiredFloor = desiredFloor;
	}
	
	public int getDesiredFloor() {
		return desiredFloor;
	}
}
