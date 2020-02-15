package event.toElevator;

import common.*;
import event.*;

public class ElevatorPressButtonEvent extends Event{
	private LampState lampState;
	private int button;
	public ElevatorPressButtonEvent(String name, int recipientId, int senderId, LampState lampState, int button) {
		super(name, recipientId, senderId);
		this.lampState = lampState;
		this.button = button;
	}
	
	public LampState getLampState(){
		return lampState;
	}
	
	public int button() {
		return button;
	}
}
