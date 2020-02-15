package elevator;

import event.*;

public abstract class ElevatorState {
	private int currentFloor;
	private int direction; //-1 get down, 0 get idle, 1 get up
	private boolean inTransit;
	private boolean upLamp;
	private boolean downLamp;
	private boolean[] lamps;

	public ElevatorState handleElevatorButtonEvent(Event elevatorButtonEvent) {
		return this;
	}
	
	public ElevatorState handleElevatorLampEvent(Event elevatorLampEvent) {
		return this;
	}
	
	public ElevatorState handleElevatorDoorEvent(Event elevatorDoorEvent) {
		return this;
	}
	
	public ElevatorState handleElevatorTransitEvent(Event elevatorTransitEvent) {
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