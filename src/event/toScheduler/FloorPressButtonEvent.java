package event.toScheduler;

import java.util.Date;

import common.*;
import event.Event;


/**
 * A class representing events caused by a person waiting for an elevator
 * An event consists of a time, a floor that the person wants is on, a destination floor, and a desired direction 
 */
public class FloorPressButtonEvent extends Event{
	
	public static final String NAME = "FloorButtonPressEvent";
	private Date time;
	private Direction direction;
	private int currentFloor;
	private int desiredFloor;
	
	/**
	 * Constructs a new Event
	 * @param time the time the event occurs
	 * @param currentFloor the floor the person is on
	 * @param direction the direction that the person has selected
	 * @param desiredFloor the floor the person would like to travel to
	 */
	public FloorPressButtonEvent(Date time, int currentFloor, Direction direction, int desiredFloor, int recipientId, int senderId) {
		super(NAME, recipientId, senderId);
		this.time = time;
		this.currentFloor = currentFloor;
		this.direction = direction;
		this.desiredFloor = desiredFloor;
	}
	
	public String toString() {
		String formatStr = "Event[time=%tr and %tN ns, currentFloor=%d, direction=%s, desiredFloor=%d]";
		return String.format(formatStr, time, time, currentFloor, direction, desiredFloor);
	}
	
	/**
	 * Returns the time of the event as a Date Object
	 * @return the time of this event
	 */
	public Date getTime() {
		return time;
	}
	
	/**
	 * Returns the integer position of the floor the event originates from
	 * @return integer position of the floor this event originates on
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}
	
	/**
	 * Returns the intended direction of the event request
	 * @return which direction button was pressed in this event
	 */
	public Direction getDirection() {
		return direction;
	}
	
	/**
	 * Returns the integer position o the floor the event desires to go
	 * @return the integer position of the floor the person causing this event would like to go
	 */
	public int getDesiredFloor() {
		return desiredFloor;
	}
	
}