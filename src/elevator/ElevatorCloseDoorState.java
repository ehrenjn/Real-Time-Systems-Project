package elevator;

import common.*;
import event.*;
import event.toElevator.*;

/**
 * Class that implements the close door state of the elevator.
 */
public class ElevatorCloseDoorState extends ElevatorState{
	
	/**
	 * Copy constructor given a previous state for the door closed state. The door open state must always
	 * have an idle direction and doorState closed
	 * @param state The state to copy into a new instance
	 */
	public ElevatorCloseDoorState(ElevatorState state) {
		super(state);
		this.doorState = DoorState.CLOSE;
		this.direction = Direction.IDLE;
	}

	/**
	 * Open door implementation for the handling of elevatorDoorEvent
	 * @param elevatorOpenDoorEvent the event modeling the opening of a door
	 */
	public ElevatorState handleElevatorOpenDoorEvent(ElevatorOpenDoorEvent elevatorOpenDoorEvent) {
		return new ElevatorOpeningDoorState(this);
	}
	
	/**
	 * Start movement implementation for the handling of elevatorTransitEvent
	 * @param elevatorTransitEvent the event modeling the starting of an elevator
	 */
	public ElevatorState handleElevatorStartMovingEvent(ElevatorStartMovingEvent elevatorStartMovingEvent) {
		return new ElevatorMovingState(this, elevatorStartMovingEvent.getDirection());
	}
}
