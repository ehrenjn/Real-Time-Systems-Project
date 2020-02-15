package elevator;

import common.*;
import event.*;

/**
 * Class that implements the moving state of the elevator.
 */
public class ElevatorMovingState extends ElevatorState{
	
	/**
	 * Copy constructor given a previous state for the closed door state
	 * @param state The state to copy into a new instance
	 * @param direction The direction to begin moving
	 */
	public ElevatorMovingState(ElevatorState state, Direction direction) {
		super(state);
		this.direction = direction;
	}
	/**
	 * Default implementation for the handling of elevatorArrivalEvent
	 * @param elevatorArrivalEvent the event modeling the arrival sensor of an elevator
	 */
	public ElevatorState handleElevatorArrivalEvent(ElevatorArrivalEvent elevatorArrivalEvent) {
		this.currentFloor = elevatorArrivalEvent.getFloor();
		return this;
	}
	
	/**
	 * Stop movement implementation for the handling of elevatorTransitEvent
	 * @param elevatorTransitEvent the event modeling the stopping of an elevator
	 */
	public ElevatorState handleElevatorTransitEvent(ElevatorTransitEvent elevatorTransitEvent) {
		switch(elevatorTransitEvent.getDirection()) {
			case IDLE:
				return new ElevatorCloseDoorState(this);
			default:
				return this;
		}	
	}
}
