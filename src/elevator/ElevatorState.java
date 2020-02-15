package elevator;

import event.*;
/**
 * Abstract class that implements the base state for the elevator. Implements getters and setters as well as 
 * ensures that all states have some base functionality
 */
public abstract class ElevatorState {
	private int currentFloor;
	private int direction; //-1 get down, 0 get idle, 1 get up
	private boolean inTransit;
	private boolean upLamp;
	private boolean downLamp;
	private boolean[] lamps;

	/**
	 * Default implementation for the handling of elevatorButtonEvent
	 * @param elevatorButtonEvent the event modeling the press of a button
	 */
	public ElevatorState handleElevatorButtonEvent(Event elevatorButtonEvent) {
		return this;
	}
	
	/**
	 * Default implementation for the handling of elevatorDirectionLampEvent
	 * @param elevatorDirectionLampEvent the event modeling the on/off of a direction lamp
	 */
	public ElevatorState handleElevatorDirectionLampEvent(Event elevatorDirectionLampEvent) {
		return this;
	}
	
	/**
	 * Default implementation for the handling of elevatorButtonLampEvent
	 * @param elevatorButtonLampEvent the event modeling the on/off of a button lamp
	 */
	public ElevatorState handleElevatorButtonLampEvent(Event elevatorButtonLampEvent) {
		return this;
	}
		
	/**
	 * Default implementation for the handling of elevatorDoorEvent
	 * @param elevatorDoorEvent the event modeling the opening/closing of a door
	 */
	public ElevatorState handleElevatorDoorEvent(Event elevatorDoorEvent) {
		return this;
	}
	
	/**
	 * Default implementation for the handling of elevatorTransitEvent
	 * @param elevatorTransitEvent the event modeling the acceleration/deceleration of an elevator
	 */
	public ElevatorState handleElevatorTransitEvent(Event elevatorTransitEvent) {
		return this;
	}
	
	/**
	 * Default implementation for the handling of elevatorArrivalEvent
	 * @param elevatorArrivalEvent the event modeling the arrival sensor of an elevator
	 */
	public ElevatorState handleElevatorArrivalEvent(Event elevatorArrivalEvent) {
		return this;
	}
	
	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean getInTransit() {
		return inTransit;
	}

	public void setInTransit(boolean inTransit) {
		this.inTransit = inTransit;
	}

	public boolean getUpLamp() {
		return upLamp;
	}

	public void setUpLamp(boolean upLamp) {
		this.upLamp = upLamp;
	}

	public boolean getDownLamp() {
		return downLamp;
	}

	public void setDownLamp(boolean downLamp) {
		this.downLamp = downLamp;
	}

	public boolean[] getLamps() {
		return lamps;
	}

	public void setLamps(boolean[] lamps) {
		this.lamps = lamps;
	}
	
	public boolean getLamp(int i) {
		return lamps[i];
	}

	public void setLamp(int i, boolean lamp) {
		this.lamps[i] = lamp;
	}
}