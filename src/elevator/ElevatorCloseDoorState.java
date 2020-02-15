package elevator;

import event.*;

/**
 * Class that implements the close door state of the elevator.
 */
public class ElevatorCloseDoorState extends ElevatorState{
	public ElevatorCloseDoorState(ElevatorState state) {
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
	
	/**
	 * Start movement implementation for the handling of elevatorTransitEvent
	 * @param elevatorTransitEvent the event modeling the starting of an elevator
	 */
	public ElevatorState handleElevatorTransitEvent(ElevatorTransitEvent elevatorTransitEvent) {
		return this;
	}
}
