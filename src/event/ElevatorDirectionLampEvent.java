package event;

import common.*;

/**
 * Represents a request to change the state of an elevator's direction lamp
 *
 */
public class ElevatorDirectionLampEvent extends Event {
	private LampState lampState;
	private Direction lampDirection;
	public static final String NAME = "ElevatorDirectionLampEvent";
	
	/**
	 * Creates a new ElevatorFloorLampEvent
	 * @param lampState the state that the direction lamp should be in
	 * @param lampDirection the direction of the lamp to turn on or off
	 */
	public ElevatorDirectionLampEvent(LampState lampState, Direction lampDirection, String recipientId, String senderId) {
		super(NAME, recipientId, senderId);
		this.lampState = lampState;
		this.lampDirection = lampDirection;
	}
	
	public LampState getLampState() {
		return lampState;
	}

	public void setLampState(LampState lampState) {
		this.lampState = lampState;
	}

	public Direction getLampDirection() {
		return lampDirection;
	}

	public void setLampDirection(Direction lampDirection) {
		this.lampDirection = lampDirection;
	}
}

