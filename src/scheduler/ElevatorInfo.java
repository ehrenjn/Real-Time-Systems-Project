package scheduler;

import java.awt.JobAttributes.DestinationType;
import java.util.LinkedList;

import common.*;

public class ElevatorInfo {
	private int id;
	private int currentFloor;
	private Direction directionOfMovement;
	private LinkedList<Integer> destinationQueue;
	
	public ElevatorInfo(int id) {
		this.id = id;
		currentFloor = 0;
		directionOfMovement = Direction.IDLE;
		destinationQueue = new LinkedList<Integer>();
	}
	
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the integer position of the floor the elevator was most recently at.
	 * This can be either the floor that the elevator is currently stopped at,
	 * or the floor that the elevator most recently passed while in transit. 
	 * 
	 * @return integer position of the floor the elevator was most recently at
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}
	
	public void setCurrentFloor(int floor) {
		currentFloor = floor;
	}
	
	public Direction getDirectionOfMovement() {
		return directionOfMovement;
	}
	
	public void setDirectionOfMovement(Direction direction) {
		directionOfMovement = direction;
	}
	
	public int getNextStop() {
		return destinationQueue.peek();
	}
	
	public int popNextStop() {
		return destinationQueue.pop();
	}
	
	public boolean hasStops() {
		return ! destinationQueue.isEmpty();
	}
	
	/**
	 * Adds a stop to this elevator thats before the final stop
	 * @param floor the stop to add
	 */
	public void addIntermediateStop(int floor) {
		if (destinationQueue.size() == 0) {
			throw new IllegalArgumentException(String.format(
					"Invalid intermediate stop (%d) for elevator: %s (can't add an intermediate stop on an elevator with no stops)",
					floor, this)
			);
		}
		if (destinationQueue.size() == 1) {
			destinationQueue.push(floor);
		}
		else { // figure out where the stop can be squeezed in
			for (int stop = 1; stop < destinationQueue.size(); stop++) {
				int previousStop = destinationQueue.get(stop - 1);
				int nextStop = destinationQueue.get(stop);
				if ((floor > previousStop && floor < nextStop) ||
				(floor < previousStop && floor > nextStop)) {
					destinationQueue.add(stop, floor);
					ThreadPrinter.print("stop inserted at: " + stop);
					return;
				}
			}
			throw new IllegalArgumentException(String.format("Invalid intermediate stop (%d) for elevator: %s", floor, this));
		}
	}
	
	
	/**
	 * Adds a stop to the end of this elevator's travels
	 * @param floor the floor to stop at
	 */
	public void appendStop(int floor) {
		destinationQueue.add(floor);
	}
	
	
	public String toString() {
		return String.format(
				"ElevatorInfo[id=%d, currentFloor=%d, direction=%s, destinationQueue=%s, totalDests=%d]", 
				id, currentFloor, directionOfMovement, queueToString(), destinationQueue.size()
		);
	}
	
	private String queueToString() {
		String result = "{ ";
		for (Integer floor: destinationQueue) {
			result += floor.toString();
			result += " ";
		}
		result += "}";
		return result;
	}
}
