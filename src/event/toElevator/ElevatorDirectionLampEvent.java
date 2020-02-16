package event.toElevator;

import event.*;
import common.*;

/**
 * Represents a request to turn a direction lamp in an elevator on or off
 *
 */
public class ElevatorDirectionLampEvent extends Event {
	public static final String NAME = "ElevatorDirectionLampEvent";
	private Direction lampDirection;
	private LampState lampState;
	
	/**
	 * Creates a new ElevatorDirectionLampEvent
	 * @param recipientId id of the recipient
	 * @param senderId id of the sender
	 * @param lampDirection direction of the lamp to change
	 * @param lampState the state to change the lamp to
	 */
	public ElevatorDirectionLampEvent(int recipientId, int senderId, Direction lampDirection, LampState lampState) {
		super(NAME, recipientId, senderId);
		this.lampDirection = lampDirection;
		this.lampState = lampState;
	}
	
	public Direction getLampDirection() {
		return lampDirection;
	}
	
	public LampState getLampState() {
		return lampState;
	}
}
