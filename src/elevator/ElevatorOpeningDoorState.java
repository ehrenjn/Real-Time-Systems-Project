package elevator;

import common.*;
import event.*;

/**
 * Class that implements the opening door state of the elevator. This is considered the default state of the model.
 */
public class ElevatorOpeningDoorState extends ElevatorState{
	
	/**
	 * Copy constructor given a previous state for the door opening state. The door opening state must always
	 * have an idle direction and doorState opening
	 * @param state The state to copy into a new instance
	 */
	public ElevatorOpeningDoorState(ElevatorState state) {
		super(state);
		this.doorState = DoorState.OPENING;
		this.direction = Direction.IDLE;
	}

	/**
	 * Close door implementation for the handling of elevatorDoorEvent
	 * @param elevatorDoorEvent the event modeling the closing of a door
	 */
	public ElevatorState handleElevatorDoorEvent(ElevatorDoorEvent elevatorDoorEvent) {
		return new ElevatorOpenDoorState(this);
	} 
}
