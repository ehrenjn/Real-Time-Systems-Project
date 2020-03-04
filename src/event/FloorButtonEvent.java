package event;

import common.*;

/**
 * Represents a button pressed on a floor
 *
 */
public class FloorButtonEvent extends Event {
	private Direction direction;
	private int floor;
	public static final String NAME = "FloorButtonEvent";
	
	/**
	 * Creates a new FloorButtonEvent
	 * @param direction the direction the person would like to go
	 * @param floor the floor the person is on
	 */
	public FloorButtonEvent(int recipientId, int senderId, Direction direction, int floor) {
		super(NAME, recipientId, senderId);
		this.direction = direction;
		this.floor = floor;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public int getFloor() {
		return floor;
	}
}
