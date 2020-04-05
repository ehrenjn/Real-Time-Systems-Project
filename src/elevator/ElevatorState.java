package elevator;

import java.util.Arrays;

import common.*;
import event.toElevator.*;
import event.toScheduler.*;
import event.*;
/**
 * Abstract class that implements the base state for the elevator. Implements getters and setters as well as 
 * ensures that all states have some base functionality
 */
public abstract class ElevatorState {
	protected String name;
	protected int currentFloor;
	protected Direction direction;
	protected DoorState doorState;
	protected LampState upLamp;
	protected LampState downLamp;
	protected LampState[] buttonLamps;
	
	
	@Override
	public String toString() {
		return "ElevatorState [name=" + name + ", currentFloor=" + currentFloor + ", direction=" + direction + ", doorState=" + doorState
				+ ", upLamp=" + upLamp + ", downLamp=" + downLamp + ", buttonLamps=" + Arrays.toString(buttonLamps)
				+ "]";
	}

	/**
	 * Default constructor for an ElevatorState. This is considered the default state for an
	 * Elevator state
	 * 
	 * @param numberOfFloors number of floors the elevator must service
	 */
	public ElevatorState(int numberOfFloors) {
		this.name = "ElevatorState";
		this.currentFloor = 0;
		this.doorState = DoorState.OPEN;
		this.direction = Direction.IDLE;
		this.upLamp = LampState.OFF;
		this.downLamp = LampState.OFF;
		this.buttonLamps = new LampState[numberOfFloors];
		for (int i = 0; i < numberOfFloors; i++) {
			buttonLamps[i] = LampState.OFF;
		}
	}
	
	/**
	 * Copy Constructor given a previous state
	 * @param state The state to copy into a new instance
	 */
	public ElevatorState(ElevatorState state) {
		this.currentFloor = state.currentFloor;
		this.doorState = state.doorState;
		this.direction = state.direction;
		this.upLamp = state.upLamp;
		this.downLamp = state.downLamp;
		this.buttonLamps = state.buttonLamps;
	}
	
	/**
	 * Default implementation for the handling of elevatorDirectionLampEvent
	 * @param elevatorDirectionLampEvent the event modeling the on/off of a direction lamp
	 * @return LampState 
	 * 
	 */
	public ElevatorState handleElevatorDirectionLampEvent(ElevatorDirectionLampEvent elevatorDirectionLampEvent) {
		switch(elevatorDirectionLampEvent.getLampDirection()) {
			case UP:
				this.upLamp = elevatorDirectionLampEvent.getLampState();
				break;
			case DOWN:
				this.downLamp = elevatorDirectionLampEvent.getLampState();
				break;
		}
		return this;
	}
	
	/**
	 * Default implementation for the handling of ElevatorButtonLampEvents
	 */
	public ElevatorState handleElevatorButtonLampEvent(ElevatorButtonLampEvent elevatorButtonLampEvent) {
		buttonLamps[elevatorButtonLampEvent.getButton()] = elevatorButtonLampEvent.getLampState();
		return this;
	}
	
	/**
	 * Default implementation for the handling of ElevatorCloseDoorEvent
	 * @param elevatorDoorEvent the event modeling the opening/closing of a door
	 */
	public ElevatorState handleElevatorOpenDoorEvent(ElevatorOpenDoorEvent elevatorOpenDoorEvent) {
		ThreadPrinter.print("State: "  + this.name + ", transition: "+ ElevatorOpenDoorEvent.NAME + ", is illegal.");
		return new ElevatorFailureState(this);
	}
	
	
	/**
	 * Default implementation for the handling of ElevatorCloseDoorEvent
	 * @param elevatorCloseDoorEvent the event modeling the closing of a door
	 * @return elevator failure state
	 */
	public ElevatorState handleElevatorCloseDoorEvent(ElevatorCloseDoorEvent elevatorCloseDoorEvent) {
		ThreadPrinter.print("State: "  + this.name + ", transition: "+ ElevatorCloseDoorEvent.NAME + ", is illegal.");
		return new ElevatorFailureState(this);
	}
	
	
	/**
	 * Default implementation for the handling of elevatorKeepMovingEvent
	 * @param elevatorKeepMovingEvent the event modeling the acceleration/deceleration of an elevator
	 * @return elevator failure state
	 */
	public ElevatorState handleElevatorKeepMovingEvent(ElevatorKeepMovingEvent elevatorKeepMovingEvent) {
		ThreadPrinter.print("State: "  + this.name + ", transition: "+ ElevatorKeepMovingEvent.NAME + ", is illegal.");
		return new ElevatorFailureState(this);
	}
	
	/**
	 * Default implementation for the handling of elevatorStartMovingEvent
	 * @param elevatorStartMovingEvent the event modeling the acceleration of an elevator
	 * @return elevator failure state
	 */
	public ElevatorState handleElevatorStartMovingEvent(ElevatorStartMovingEvent elevatorStartMovingEvent) {
		ThreadPrinter.print("State: "  + this.name + ", transition: "+ ElevatorStartMovingEvent.NAME + ", is illegal.");
		return new ElevatorFailureState(this);
	}
	
	/**
	 * Default implementation for the handling of elevatorStopMovingEvent
	 * @param elevatorStopMovingEvent the event modeling the deceleration of an elevator
	 * @return elevator failure state
	 */
	public ElevatorState handleElevatorStopMovingEvent(ElevatorStopMovingEvent elevatorStopMovingEvent) {
		ThreadPrinter.print("State: "  + this.name + ", transition: "+ ElevatorStopMovingEvent.NAME + ", is illegal.");
		return new ElevatorFailureState(this);
	}
	
	/**
	 * Default implementation for the handling of elevatorPressButtonEvent
	 * @param elevatorPressButtonEvent the event modeling the elevator button being pressed
	 * @return button that is pressed
	 */
	public ElevatorState handleElevatorPressButtonEvent(ElevatorPressButtonEvent elevatorPressButtonEvent) {
		ThreadPrinter.print("State: "  + this.name + ", transition: "+ ElevatorPressButtonEvent.NAME + ", is illegal.");
		return new ElevatorFailureState(this);
	}
	/**
	 * get the current floor of the elevator
	 * @return current floor
	 */
	public int getCurrentFloor() {
		return this.currentFloor;
	}
	
	/**
	 * set the current floor of the elevator
	 * @param currentFloor current floor of the elevator
	 */
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
	 
	/**
	 * set the  direction of the lamp
	 * @param currentFloor current floor of the elevator
	 * @param state the state of the lamp
	 */
	public void setButtonLamp(int button, LampState state) {
		buttonLamps[button] = state;
	}
	
	/**
	 * gets the name of the state
	 * @return the name of the state
	 */
	public String getName() {
		return this.name;
	}
}