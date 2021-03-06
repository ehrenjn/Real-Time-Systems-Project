package elevator;

import common.*;
import event.*;
import event.toElevator.*;

/**
 * Class that implements the close door state of the elevator.
 */
public class ElevatorCloseDoorState extends ElevatorState{
	public static final String NAME = "ElevatorCloseDoorState";
	
	/**
	 * Copy constructor given a previous state for the door closed state. The door open state must always
	 * have an idle direction and doorState closed
	 * @param state The state to copy into a new instance
	 */
	public ElevatorCloseDoorState(ElevatorState state) {
		super(state);
		this.name = NAME;
		this.doorState = DoorState.CLOSE;
		this.direction = Direction.IDLE;
	}

	/**
	 * Open door implementation for the handling of elevatorDoorEvent
	 * @param elevatorOpenDoorEvent the event modeling the opening of a door
	 * @return new state of the elevator Doors
	 */
	public ElevatorState handleElevatorOpenDoorEvent(ElevatorOpenDoorEvent elevatorOpenDoorEvent) {
		return new ElevatorOpeningDoorState(this);
	}
	
	/**
	 * Start movement implementation for the handling of elevatorStartMovingEvent
	 * @param elevatorStartMovingEvent the event modeling the starting of an elevator
	 * @return new state of the elevator
	 */
	public ElevatorState handleElevatorStartMovingEvent(ElevatorStartMovingEvent elevatorStartMovingEvent) {
		return new ElevatorMovingState(this, elevatorStartMovingEvent.getDirection());
	}
}
