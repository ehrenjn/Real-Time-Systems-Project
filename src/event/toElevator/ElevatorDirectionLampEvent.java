package event.toElevator;

import event.*;
import common.*;

public class ElevatorDirectionLampEvent extends Event{
	public static final String NAME = "ElevatorDirectionLampEvent";
	private Direction lampDirection;
	private LampState lampState;
	public ElevatorDirectionLampEvent(String name, int recipientId, int senderId, Direction lampDirection, LampState lampState) {
		super(name, recipientId, senderId);
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
