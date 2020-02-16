package elevator;

import common.*;
import event.*;
import event.toElevator.*;

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
	 * Stop movement implementation for the handling of ElevatorStopMovingEvent
	 * @param elevatorTransitEvent the event modeling the continuous moving of an elevator
	 */
	public ElevatorState handleElevatorKeepMovingEvent(ElevatorKeepMovingEvent ElevatorStopMovingEvent) {
		return this;
	}
	
	/**
	 * Stop movement implementation for the handling of ElevatorStopMovingEvent
	 * @param elevatorTransitEvent the event modeling the stopping of an elevator
	 */
	public ElevatorState handleElevatorStopMovingEvent(ElevatorStopMovingEvent ElevatorStopMovingEvent) {
		return new ElevatorCloseDoorState(this);
	}
}
