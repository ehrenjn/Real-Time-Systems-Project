package event.toElevator;

import event.*;
import common.*;

/**
 * Represents a request to turn a button lamp in an elevator on or off
 *
 */
public class ElevatorButtonLampEvent extends Event {
	public static final String NAME = "ElevatorButtonLampEvent";
	private int button;
	private LampState lampState;
	
	/**
	 * Creates a new ElevatorButtonLampEvent
	 * @param recipientId id of the recipient
	 * @param senderId id of the sender
	 * @param button the button to change
	 * @param lampState the state to change the lamp to
	 */
	public ElevatorButtonLampEvent(int recipientId, int senderId, int button, LampState lampState) {
		super(NAME, recipientId, senderId);
		this.button = button;
		this.lampState = lampState;
	}
	
	public int getButton() {
		return button;
	}
	
	public LampState getLampState() {
		return lampState;
	}
}
