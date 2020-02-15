package elevator;

import event.*;

/**
 * Class that implements the closing door state of the elevator.
 */
public class ElevatorClosingDoorState extends ElevatorState{
	public ElevatorClosingDoorState(ElevatorState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Open door implementation for the handling of elevatorDoorEvent
	 * @param elevatorDoorEvent the event modeling the opening of a door
	 */
	public ElevatorState handleElevatorDoorEvent(ElevatorDoorEvent elevatorDoorEvent) {
		return this;
	}
}
