package scheduler;

import java.util.LinkedList;

import common.CommunicationSocket;
import event.toScheduler.*;
import event.toElevator.*;
import event.*;
import common.*;

public class Scheduler {
	private CommunicationSocket elevatorSocket;
	private CommunicationSocket floorSocket;
	public static final int SCHEDULER_ID = 0;
	public static final int ELEVATOR_ID = 0;
	
	//elevator state variables
	private LinkedList<Integer> destinationQueue;
	private boolean elevatorIsIdle;
	private Direction elevatorDirection;
	private int elevatorCurrentFloor;
	
	/**
	 * Constructs a new Scheduler
	 * @param elevatorSocket represents a communicationSocket which communicates the elevator events
	 * @param floorSocket represents the communicationSocket which communicates the floor events
	 */
	public Scheduler(CommunicationSocket elevatorSocket, CommunicationSocket floorSocket) {
		this.elevatorSocket = elevatorSocket;
		this.floorSocket = floorSocket;
		this.destinationQueue = new LinkedList<Integer>();
		this.elevatorCurrentFloor = 0;
	}
	
	
	/**
	 * sends the floor event to the client
	 */
	public void sendFloorEventIn(Event event) {
		this.floorSocket.sendEventIn(event);
	}
	
	
	/**
	 * Receives the floor event from a client
	 * @return the event received
	 */
	
	public Event recieveFloorEventOut() {
		return this.floorSocket.recieveEventOut();
	}
	
	/**
	 * sends the elevator event to the client
	 */
	public void sendElevatorEventIn(Event event) {
		this.elevatorSocket.sendEventIn(event);
	}
	
	/**
	 * Receives the elevator event from a client
	 * @return the event received
	 */
	public Event recieveElevatorEventOut() {
		return this.elevatorSocket.recieveEventOut();
	}
	
	
	
	private void scheduleElevator(int floor) {
		destinationQueue.add(floor);
		if (elevatorIsIdle) {
			elevatorIsIdle = false;
			closeElevatorDoors();
		}
	}
	
	private void closeElevatorDoors() {
		sendElevatorEventIn(new ElevatorCloseDoorEvent(ELEVATOR_ID, SCHEDULER_ID));
	}
	
	
	public void handleEvent(Event event) {
		switch (event.getName()) {
		case ElevatorArrivalSensorEvent.NAME:
			handleElevatorArrivalSensorEvent((ElevatorArrivalSensorEvent) event);
			break;
		case ElevatorPressedButtonEvent.NAME:
			handleElevatorPressedButtonEvent((ElevatorPressedButtonEvent) event);
			break;
		case ElevatorClosedDoorEvent.NAME:
			handleElevatorClosedDoorEvent((ElevatorClosedDoorEvent) event);
			break;
		case FloorPressButtonEvent.NAME:
			handleFloorPressButtonEvent((FloorPressButtonEvent) event);
			break;
		case ElevatorStoppedEvent.NAME:
			handleElevatorStoppedEvent((ElevatorStoppedEvent) event);
		case ElevatorOpenedDoorEvent.NAME:
			handleElevatorOpenedDoorEvent((ElevatorOpenedDoorEvent) event);
		}
	}
	
	
	private void handleElevatorArrivalSensorEvent(ElevatorArrivalSensorEvent event) {
		if (event.getArrivingFloor() == destinationQueue.getFirst()) {
			sendElevatorEventIn(new ElevatorStopMovingEvent(ELEVATOR_ID, SCHEDULER_ID));
		} else {
			sendElevatorEventIn(new ElevatorKeepMovingEvent(elevatorDirection, ELEVATOR_ID, SCHEDULER_ID));
		}
		elevatorCurrentFloor = event.getArrivingFloor();
	}
	
	
	private void handleElevatorPressedButtonEvent(ElevatorPressedButtonEvent event) {
		scheduleElevator(event.getDesiredFloor());
	}
	
	
	private void handleElevatorClosedDoorEvent(ElevatorClosedDoorEvent event) {
		int floorDelta = destinationQueue.getFirst() - elevatorCurrentFloor;
		elevatorDirection = floorDelta < 0 ? Direction.DOWN : Direction.UP;
		sendElevatorEventIn(new ElevatorStartMovingEvent(
				elevatorDirection, event.getSender(), SCHEDULER_ID
		));
	}
	
	
	private void handleFloorPressButtonEvent(FloorPressButtonEvent event) {
		//forward event to elevator
		FloorPressButtonEvent forwardedEvent = new FloorPressButtonEvent(event.getTime(), 
				event.getCurrentFloor(), event.getDirection(), event.getDesiredFloor(), 
				event.getSender(), SCHEDULER_ID
		);
		sendElevatorEventIn(forwardedEvent);
		
		//schedule an elevator if one is not already waiting at the correct floor with doors open
		if (! (elevatorIsIdle && elevatorCurrentFloor == event.getCurrentFloor())) {
			scheduleElevator(event.getCurrentFloor());
		}
	}
	
	
	private void handleElevatorStoppedEvent(ElevatorStoppedEvent event) {
		//TODO: TELL FLOOR TO TURN OFF APPROPRIATE DIRECTION LAMP
		destinationQueue.pop();
		sendElevatorEventIn(new ElevatorOpenDoorEvent(event.getSender(), SCHEDULER_ID));
	}
	
	
	private void handleElevatorOpenedDoorEvent(ElevatorOpenedDoorEvent event) {
		if (destinationQueue.isEmpty()) {
			elevatorIsIdle = true;
		} else {
			closeElevatorDoors();
		}
	}
}
