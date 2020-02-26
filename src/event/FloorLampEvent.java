package event;

import common.*;

/**
 * Represents a request to turn a floor lamp on or off
 *
 */
public class FloorLampEvent extends Event {
	private LampState lampState;
	private Direction direction;
	public static final String NAME = "FloorLampEvent";
	
	/**
	 * Creates a new FloorLampEvent
	 * @param lampState the state that the direction lamp should be in
	 * @param direction the direction lamp you would like to change
	 */
	public FloorLampEvent(LampState lampState, Direction direction, int recipientId, int senderId) {
		super(NAME, recipientId, senderId);
		this.lampState = lampState;
		this.direction = direction;
	}
	
	public LampState getLampState() {
		return lampState;
	}
	
	public Direction getDirection() {
		return direction;
	}
}
