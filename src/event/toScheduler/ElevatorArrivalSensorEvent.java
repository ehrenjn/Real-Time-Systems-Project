package event.toScheduler;

import event.*;

/**
 * Represents a response from an arrival sensor
 */
public class ElevatorArrivalSensorEvent extends Event {
	public static final String NAME = "ElevatorArrivalSensorEvent";
	private int arrivingFloor;
	
	/**
	 * Creates a new ElevatorArrivalSensorEvent
	 * @param arrivingFloor the floor we are arriving at
	 * @param recipientId the id of the recipient of this event
	 * @param senderId the id of the sender of this event
	 */
	public ElevatorArrivalSensorEvent(int arrivingFloor, int recipientId, int senderId) {
		super(NAME, recipientId, senderId);
		this.arrivingFloor = arrivingFloor;
	}
	
	public int getArrivingFloor() {
		return arrivingFloor;
	}
	
}
