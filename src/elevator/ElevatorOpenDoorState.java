package elevator;

import event.*;

/**
 * Class that implements the open door state for the elevator.
 */
public class ElevatorOpenDoorState extends ElevatorState{
	
	public ElevatorOpenDoorState(ElevatorState state) {
		super(state);
	}

	/**
	 * Close door implementation for the handling of elevatorDoorEvent
	 * @param elevatorDoorEvent the event modeling the closing of a door
	 */
	public ElevatorState handleElevatorDoorEvent(ElevatorDoorEvent elevatorDoorEvent) {
		return this;
	}
}
