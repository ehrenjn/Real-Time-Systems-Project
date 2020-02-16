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
	
	public boolean emptyQueue() {
		return destinationQueue.isEmpty();
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
		System.out.println("Scheduler sending: " + event);
		this.elevatorSocket.sendEventIn(event);
	}
	
	/**
	 * Receives the elevator event from a client
	 * @return the event received
	 */
	public Event recieveElevatorEventOut() {
		return this.elevatorSocket.recieveEventOut();
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
				break;
			case ElevatorOpenedDoorEvent.NAME:
				handleElevatorOpenedDoorEvent((ElevatorOpenedDoorEvent) event);
				break;
		}
	}
	
	public void handleFloorPressButtonEvent(FloorPressButtonEvent event) {
		sendElevatorEventIn(new ElevatorPressButtonEvent(ELEVATOR_ID, SCHEDULER_ID, event.getDesiredFloor()));
		destinationQueue.add(event.getCurrentFloor());
	}
	
	public void handleElevatorClosedDoorEvent(ElevatorClosedDoorEvent event) {
		int floorDelta = destinationQueue.getFirst() - elevatorCurrentFloor;
		elevatorDirection = floorDelta < 0 ? Direction.DOWN : Direction.UP;
		sendElevatorEventIn(new ElevatorDirectionLampEvent(
				event.getSender(), SCHEDULER_ID, elevatorDirection, LampState.ON));
		sendElevatorEventIn(new ElevatorStartMovingEvent(elevatorDirection, event.getSender(), SCHEDULER_ID));
	}
	
	public void handleElevatorArrivalSensorEvent(ElevatorArrivalSensorEvent event) {
		if (destinationQueue.getFirst() == event.getArrivingFloor()) {
			sendElevatorEventIn(new ElevatorStopMovingEvent(ELEVATOR_ID, SCHEDULER_ID));
		} else {
			sendElevatorEventIn(new ElevatorKeepMovingEvent(elevatorDirection, ELEVATOR_ID, SCHEDULER_ID));
		}
		elevatorCurrentFloor = event.getArrivingFloor();
	}
	
	
	public void handleElevatorPressedButtonEvent(ElevatorPressedButtonEvent event) {
		destinationQueue.add(event.getDesiredFloor());
		sendElevatorEventIn(new ElevatorCloseDoorEvent(ELEVATOR_ID, SCHEDULER_ID));
	}
	
	
	public void handleElevatorStoppedEvent(ElevatorStoppedEvent event) {
		sendElevatorEventIn(new ElevatorDirectionLampEvent(
				event.getSender(), SCHEDULER_ID, elevatorDirection, LampState.OFF));
		sendElevatorEventIn(new ElevatorOpenDoorEvent(event.getSender(), SCHEDULER_ID));
	}
	
	
	public void handleElevatorOpenedDoorEvent(ElevatorOpenedDoorEvent event) {
		destinationQueue.pop();
		if (! destinationQueue.isEmpty()) { //keep visiting more floors if need be
			sendElevatorEventIn(new ElevatorCloseDoorEvent(ELEVATOR_ID, SCHEDULER_ID));
		}
	}
}
