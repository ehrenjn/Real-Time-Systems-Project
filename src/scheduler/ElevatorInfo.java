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
	 * Adds a stop to this elevator
	 * @param floor the stop to add
	 */
	public void addStop(int floor) {
		if (destinationQueue.size() <= 1) {
			destinationQueue.add(floor);
		} 
		else { // figure out where the stop can be squeezed in
			for (int stop = 1; stop < destinationQueue.size(); stop++) {
				int previousStop = destinationQueue.get(stop - 1);
				int nextStop = destinationQueue.get(stop);
				if ((floor > previousStop && floor < nextStop) ||
				(floor < previousStop && floor > nextStop)) {
					destinationQueue.add(stop, floor);
					ThreadPrinter.print("stop inserted at: " + stop);
					break;
				}
			}
		}
	}
	
	
	public String toString() {
		return String.format(
				"ElevatorInfo[id=%d, currentFloor=%d, direction=%s, nextDest=%d, totalDests=%d]", 
				id, currentFloor, directionOfMovement, destinationQueue.peek(), destinationQueue.size()
		);
	}
}
