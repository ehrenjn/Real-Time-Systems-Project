package elevator;

import event.*;

/**
 * Class that implements the opening door state of the elevator. This is considered the default state of the model.
 */
public class ElevatorOpeningDoorState extends ElevatorState{
	
	public ElevatorOpeningDoorState(ElevatorState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Close door implementation for the handling of elevatorDoorEvent
	 * @param elevatorDoorEvent the event modeling the closing of a door
	 */
	public ElevatorState handleElevatorDoorEvent(ElevatorDoorEvent elevatorDoorEvent) {
		return this;
	}
}
