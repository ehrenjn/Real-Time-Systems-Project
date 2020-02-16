package event.toElevator;

import event.*;
import common.*;

public class ElevatorStartMovingEvent extends Event{
	public static final String NAME = "ElevatorStartMovingEvent";
	private Direction direction;
	public ElevatorStartMovingEvent(int recipientId, int senderId, Direction direction) {
		super(NAME, recipientId, senderId);
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
}
