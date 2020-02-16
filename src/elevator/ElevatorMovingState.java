package elevator;

import common.*;
import event.*;
import event.toElevator.*;

/**
 * Class that implements the moving state of the elevator.
 */
public class ElevatorMovingState extends ElevatorState{
	public static final String NAME = "ElevatorMovingState";
	
	/**
	 * Copy constructor given a previous state for the closed door state
	 * @param state The state to copy into a new instance
	 * @param direction The direction to begin moving
	 */
	public ElevatorMovingState(ElevatorState state, Direction direction) {
		super(state);
		this.name = NAME;
		this.direction = direction;
	}
	
	/**
	 * Continued movement implementation for the handling of ElevatorkeepMovingEvent
	 * @param ElevatorKeepMovingEvent the event modeling the continuous moving of an elevator
	 * @return elevator continuing its movement
	 */
	public ElevatorState handleElevatorKeepMovingEvent(ElevatorKeepMovingEvent ElevatorKeepMovingEvent) {
		return this;
	}
	
	/**
	 * Stop movement implementation for the handling of ElevatorStopMovingEvent
	 * @param ElevatorStopMovingEvent the event modeling the stopping of an elevator
	 * @return new state of elevator doors
	 */
	public ElevatorState handleElevatorStopMovingEvent(ElevatorStopMovingEvent ElevatorStopMovingEvent) {
		return new ElevatorCloseDoorState(this);
	}
}
