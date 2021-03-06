package elevator;

import common.*;
import event.*;
import event.toElevator.*;

/**
 * Class that implements the opening door state of the elevator. This is considered the default state of the model.
 */
public class ElevatorOpeningDoorState extends ElevatorState{
	public static final String NAME = "ElevatorOpeningDoorState";
	
	/**
	 * Copy constructor given a previous state for the door opening state. The door opening state must always
	 * have an idle direction and doorState opening
	 * @param state The state to copy into a new instance
	 */
	public ElevatorOpeningDoorState(ElevatorState state) {
		super(state);
		this.name = NAME;
		this.doorState = DoorState.OPENING;
		this.direction = Direction.IDLE;
	}

	/**
	 * Open door implementation for the handling of elevatorDoorEvent
	 * @param elevatorOpenDoorEvent the event modeling the opening of a door
	 */
	public ElevatorState handleElevatorOpenDoorEvent(ElevatorOpenDoorEvent elevatorOpenDoorEvent) {
		return new ElevatorOpenDoorState(this);
	} 
}
