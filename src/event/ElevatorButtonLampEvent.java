package event;

import common.*;

/**
 * Represents a request to change the state of an elevator's floor button lamp
 *
 */
public class ElevatorButtonLampEvent extends Event {
	private LampState lampState;
	public static final String NAME = "ElevatorFloorLampEvent";
	
	/**
	 * Creates a new ElevatorFloorLampEvent
	 * @param lampState
	 */
	public ElevatorButtonLampEvent(LampState lampState) {
		super(NAME);
		this.lampState = lampState;
	}
	
	public LampState getLampState() {
		return lampState;
	}
}
