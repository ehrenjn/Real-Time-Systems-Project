package event;

import common.*;

/**
 * Represents a request to change the state of an elevator's direction lamp
 *
 */
public class ElevatorDirectionLampEvent extends Event {
	private DirectionLampState lampState;
	public static String NAME = "ElevatorDirectionLampEvent";
	
	/**
	 * Creates a new ElevatorFloorLampEvent
	 * @param lampState the state that the direction lamp should be in
	 */
	public ElevatorDirectionLampEvent(DirectionLampState lampState) {
		super(NAME);
		this.lampState = lampState;
	}
	
	public DirectionLampState getLampState() {
		return lampState;
	}
}

