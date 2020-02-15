package event;

import common.*;

/**
 * Represents a request to open or close an elevator door
 *
 */
public class ElevatorDoorEvent extends Event {
	private DoorState direction;
	public static final String NAME = "ElevatorDoorEvent";
	
	/**
	 * Creates a new ElevatorDoorEvent
	 * @param direction whether to open or close the doors
	 */
	public ElevatorDoorEvent(DoorState direction, int recipientId, int senderId) {
		super(NAME, recipientId, senderId);
		this.direction = direction;
	}
	
	public DoorState getDoorState() {
		return direction;
	}
}
