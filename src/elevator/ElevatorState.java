package elevator;

import common.*;
import event.*;
/**
 * Abstract class that implements the base state for the elevator. Implements getters and setters as well as 
 * ensures that all states have some base functionality
 */
public abstract class ElevatorState {
	protected int currentFloor;
	protected Direction direction;
	protected LampState upLamp;
	protected LampState downLamp;
	protected LampState[] lamps;
	
	/**
	 * Default constructor for the Elevator Open door state. This is considered the default state for an
	 * Elevator state
	 */
	public ElevatorState() {
		this.currentFloor = 0;
		this.direction = Direction.IDLE;
		this.upLamp = LampState.OFF;
		this.downLamp = LampState.OFF;
		this.lamps = lamps;
	}
	
	/**
	 * Copy Constructor given a previous state
	 * @param state The state to copy into a new instance
	 */
	public ElevatorState(ElevatorState state) {
		this.currentFloor = state.currentFloor;
		this.direction = state.direction;
		this.upLamp = state.upLamp;
		this.downLamp = state.downLamp;
		this.lamps = this.lamps;
	}

	/**
	 * Default implementation for the handling of elevatorButtonEvent
	 * @param elevatorButtonEvent the event modeling the press of a button
	 */
	public ElevatorState handleElevatorButtonEvent(ElevatorButtonEvent elevatorButtonEvent) {
		return this;
	}
	
	/**
	 * Default implementation for the handling of elevatorDirectionLampEvent
	 * @param elevatorDirectionLampEvent the event modeling the on/off of a direction lamp
	 */
	public ElevatorState handleElevatorDirectionLampEvent(ElevatorDirectionLampEvent elevatorDirectionLampEvent) {
		return this;
	}
	
	/**
	 * Default implementation for the handling of elevatorButtonLampEvent
	 * @param elevatorButtonLampEvent the event modeling the on/off of a button lamp
	 */
	public ElevatorState handleElevatorButtonLampEvent(ElevatorButtonLampEvent elevatorButtonLampEvent) {
		return this;
	}
		
	/**
	 * Default implementation for the handling of elevatorDoorEvent
	 * @param elevatorDoorEvent the event modeling the opening/closing of a door
	 */
	public ElevatorState handleElevatorDoorEvent(ElevatorDoorEvent elevatorDoorEvent) {
		return this;
	}
	
	/**
	 * Default implementation for the handling of elevatorTransitEvent
	 * @param elevatorTransitEvent the event modeling the acceleration/deceleration of an elevator
	 */
	public ElevatorState handleElevatorTransitEvent(ElevatorTransitEvent elevatorTransitEvent) {
		return this;
	}
	
	/**
	 * Default implementation for the handling of elevatorArrivalEvent
	 * @param elevatorArrivalEvent the event modeling the arrival sensor of an elevator
	 */
	public ElevatorState handleElevatorArrivalEvent(ElevatorArrivalEvent elevatorArrivalEvent) {
		return this;
	}
}