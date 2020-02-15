package event.toScheduler;

import event.*;

/**
 * Represents a response from an arrival sensor
 */
public class ElevatorArrivalSensorEvent extends Event {
	public static final String NAME = "ElevatorArrivalSensorEvent";
	private int arrivingFloor;
	
	public ElevatorArrivalSensorEvent(int arrivingFloor, String name, int recipientId, int senderId) {
		super(name, recipientId, senderId);
		this.arrivingFloor = arrivingFloor;
	}
	
	public int getArrivingFloor() {
		return arrivingFloor;
	}
	
}
