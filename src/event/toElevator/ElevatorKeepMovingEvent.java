package event.toElevator;

import common.Direction;
import event.*;
import common.*;

/**
 * Tells the elevator to keep moving
 *
 */
public class ElevatorKeepMovingEvent extends Event {
	public static final String NAME = "ElevatorKeepMovingEvent";
	private Direction direction;
	
	/**
	 * Creates a new ElevatorKeepMovingEvent
	 * @param direction the direction to keep moving in
	 * @param recipientId the id of the recipient 
	 * @param senderId id of the sender
	 */
	public ElevatorKeepMovingEvent(Direction direction, int recipientId, int senderId) {
		super(NAME, recipientId, senderId);
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
}
