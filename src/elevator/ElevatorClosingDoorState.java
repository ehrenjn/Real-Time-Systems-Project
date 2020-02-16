package elevator;

import common.*;
import event.*;
import event.toElevator.*;

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
	/**
	 * Close door implementation for the handling of elevatorDoorEvent
	 * @param elevatorCloseDoorEvent the event modeling the closing of a door
	 */
	public ElevatorState handleElevatorCloseDoorEvent(ElevatorCloseDoorEvent elevatorCloseDoorEvent) {
		return new ElevatorCloseDoorState(this);
	} 
}
