package event.toScheduler;

import event.*;

/**
 * Represents a response from an arrival  sensor
 */
public class ElevatorArrivalSensorEvent extends Event{
	
	private int arrivingFloor;

	public ElevatorArrivalSensorEvent(String name, int recipientId, int senderId) {
		super(name, recipientId, senderId);
	}
	
}
