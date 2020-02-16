package event.toElevator;

import common.*;
import event.*;

public class ElevatorPressButtonEvent extends Event{
	public static final String NAME = "ElevatorPressButtonEvent";
	private int button;
	public ElevatorPressButtonEvent(int recipientId, int senderId, int button) {
		super(NAME, recipientId, senderId);
		this.button = button;
	}
	
	public int getButton() {
		return button;
	}
}
