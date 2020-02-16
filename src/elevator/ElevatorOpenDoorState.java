package elevator;

import common.*;
import event.*;
import event.toElevator.*;

/**
 * Class that implements the open door state for the elevator.
 */
public class ElevatorOpenDoorState extends ElevatorState{
	
	/**
	 * Default constructor for the Elevator Open door state. This is considered the default state for an
	 * Elevator state
	 * 
	 * @param numberOfFloors number of floors the elevator must service
	 */
	public ElevatorOpenDoorState(int numberOfFloors) {
		super(numberOfFloors);
	}
	
	/**
	 * Copy constructor given a previous state for the door open state. The door open state must always
	 * have an idle direction and doorState open
	 * @param state The state to copy into a new instance
	 */
	public ElevatorOpenDoorState(ElevatorState state) {
		super(state);
		this.doorState = DoorState.OPEN;
		this.direction = Direction.IDLE;
	}

	/**
	 * Close door implementation for the handling of elevatorDoorEvent
	 * @param elevatorCloseDoorEvent the event modeling the closing of a door
	 */
	public ElevatorState handleElevatorCloseDoorEvent(ElevatorCloseDoorEvent elevatorCloseDoorEvent) {
		return new ElevatorClosingDoorState(this);
	}
	
	/**
	 * Default implementation for the handling of elevatorButtonEvent
	 * @param elevatorPressButtonEvent the event modeling the press of a button
	 */
	public ElevatorState handleElevatorButtonEvent(ElevatorPressButtonEvent elevatorPressButtonEvent) {
		return this;
	}
}
