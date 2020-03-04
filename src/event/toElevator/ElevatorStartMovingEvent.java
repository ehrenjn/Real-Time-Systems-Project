package event.toElevator;

import event.*;
import common.*;

public class ElevatorStartMovingEvent extends Event{
	public static final String NAME = "ElevatorStartMovingEvent";
	private Direction direction;
	
	/**
	 * Creates a new ElevatorStartMovingEvent
	 * @param recipientId the id of the recipient 
	 * @param senderId id of the sender
	 * @param direction the direction the elevator should move
	 */
	public ElevatorStartMovingEvent(int recipientId, int senderId, Direction direction) {
		super(NAME, recipientId, senderId);
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
}
