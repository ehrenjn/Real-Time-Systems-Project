package event;

import common.*;

/**
 * Represents a request to change the state of an elevator's floor button lamp
 *
 */
public class ElevatorButtonLampEvent extends Event {
	private LampState lampState;
	private int floor;
	public static String NAME = "ElevatorFloorLampEvent";

	
	/**
	 * Creates a new ElevatorFloorLampEvent
	 * @param lampState
	 */
	public ElevatorButtonLampEvent(LampState lampState, int floor, String recipientId, String senderId) {
		super(NAME, recipientId, senderId);
		this.lampState = lampState;
		this.floor = floor;
	}
	
	public LampState getLampState() {
		return lampState;
	}
	
	public int getFloor() {
		return floor;
	}
}
