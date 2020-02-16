package event.toElevator;

import event.*;
import common.*;

public class ElevatorStartMovingEvent extends Event{
	public static final String NAME = "ElevatorStartMovingEvent";
	private Direction direction;
	
	/**
	 * Creates a new ElevatorStartMovingEvent
	 * @param direction the direction the elevator should move
	 * @param recipientId the id of the recipient 
	 * @param senderId id of the sender
	 */
	public ElevatorStartMovingEvent(Direction direction, int recipientId, int senderId) {
		super(NAME, recipientId, senderId);
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
}
