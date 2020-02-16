package event.toElevator;

import common.Direction;
import event.*;

/**
 * Tells the elevator to keep moving
 *
 */
public class ElevatorKeepMovingEvent extends Event {
	public static final String NAME = "ElevatorKeepMovingEvent";
	private Direction direction;
	public ElevatorKeepMovingEvent(int recipientId, int senderId, Direction direction) {
		super(NAME, recipientId, senderId);
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
}
