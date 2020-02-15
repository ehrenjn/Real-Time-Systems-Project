package elevator;

import common.*;
import event.*;

/**
 * Class that implements the closing door state of the elevator.
 */
public class ElevatorClosingDoorState extends ElevatorState{
	
	/**
	 * Copy constructor given a previous state for the door closing state. The door open state must always
	 * have an idle direction and doorState closing
	 * @param state The state to copy into a new instance
	 */
	public ElevatorClosingDoorState(ElevatorState state) {
		super(state);
		this.doorState = DoorState.CLOSING;
		this.direction = Direction.IDLE;
	}

	/**
	 * Open door implementation for the handling of elevatorDoorEvent
	 * @param elevatorDoorEvent the event modeling the opening of a door
	 */
	public ElevatorState handleElevatorDoorEvent(ElevatorDoorEvent elevatorDoorEvent) {
		return new ElevatorCloseDoorState(this);
	}
}
