package event;

import common.*;

/**
 * Represents a request to move or stop an elevator
 *
 */
public class ElevatorTransitEvent extends Event {
	private Direction direction;
	public static String NAME = "ElevatorTransitEvent";
	
	/**
	 * Creates a new ElevatorTransitEvent
	 * @param direction the direction for the elevator to go
	 */
	public ElevatorTransitEvent(Direction direction) {
		super(NAME);
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
}
