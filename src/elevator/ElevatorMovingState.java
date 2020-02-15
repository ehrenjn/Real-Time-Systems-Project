package elevator;

import event.*;

/**
 * Class that implements the moving state of the elevator.
 */
public class ElevatorMovingState extends ElevatorState{
	public ElevatorMovingState(ElevatorState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Default implementation for the handling of elevatorArrivalEvent
	 * @param elevatorArrivalEvent the event modeling the arrival sensor of an elevator
	 */
	public ElevatorState handleElevatorArrivalEvent(ElevatorArrivalEvent elevatorArrivalEvent) {
		return this;
	}
	
	/**
	 * Stop movement implementation for the handling of elevatorTransitEvent
	 * @param elevatorTransitEvent the event modeling the stopping of an elevator
	 */
	public ElevatorState handleElevatorTransitEvent(ElevatorTransitEvent elevatorTransitEvent) {
		return this;
	}
}
