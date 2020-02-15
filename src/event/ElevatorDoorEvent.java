package event;

import common.*;

public class ElevatorDoorEvent extends Event {
	private DoorState direction;
	
	public ElevatorDoorEvent(DoorState direction) {
		this.direction = direction;
	}
	
	public DoorState getDirection() {
		return direction;
	}
}
