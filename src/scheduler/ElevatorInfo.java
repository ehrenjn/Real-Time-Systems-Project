package scheduler;

import java.util.LinkedList;

import common.*;

public class ElevatorInfo {
	private int id;
	private int currentFloor;
	private Direction directionOfMovement;
	private LinkedList<Integer> destinationQueue;
	
	public ElevatorInfo(int id) {
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
	
	public void addStop(int floor) {
		destinationQueue.add(floor);
	}
}
