package event.toElevator;

import common.*;
import event.*;

public class ElevatorPressButtonEvent extends Event{
	public static final String NAME = "ElevatorPressButtonEvent";
	private LampState lampState;
	private int button;
	public ElevatorPressButtonEvent(int recipientId, int senderId, LampState lampState, int button) {
		super(NAME, recipientId, senderId);
		this.lampState = lampState;
		this.button = button;
	}
	
	public LampState getLampState(){
		return lampState;
	}
	
	public int getButton() {
		return button;
	}
}
