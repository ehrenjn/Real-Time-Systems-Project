package event;

import common.*;

/**
 * Represents a request to change the state of an elevator's floor button lamp
 *
 */
public class ElevatorFloorLampEvent extends Event {
	private LampState lampState;
	public static String NAME = "ElevatorFloorLampEvent";
	
	/**
	 * Creates a new ElevatorFloorLampEvent
	 * @param lampState
	 */
	public ElevatorFloorLampEvent(LampState lampState) {
		super(NAME);
		this.lampState = lampState;
	}
	
	public LampState getLampState() {
		return lampState;
	}
}
