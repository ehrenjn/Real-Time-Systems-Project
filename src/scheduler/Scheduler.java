package scheduler;

import java.util.LinkedList;

import common.CommunicationSocket;
import common.Direction;
import common.DoorState;
import common.LampState;
import event.*;
import event.toElevator.*;
import event.toScheduler.*;
import event.Event;
import common.*;

public class Scheduler {
	private CommunicationSocket elevatorSocket;
	private CommunicationSocket floorSocket;
	private static final int SCHEDULER_ID = 0;
	private static final int ELEVATOR_ID = 0;
	
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
		if (elevatorDirection == Direction.IDLE) {
			elevatorIsIdle = false;
			closeElevatorDoors();
		}
	}
	
	private void closeElevatorDoors() {
		//TODO: SEND ELEVATOR DOOR CLOSE REQUEST TO ELEVATOR
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
		case FloorButtonEvent.NAME:
			handleFloorButtonEvent((FloorButtonEvent) event);
			break;
		case ElevatorStoppedEvent.NAME:
			handleElevatorStoppedEvent((ElevatorStoppedEvent) event);
		case ElevatorOpenedDoorEvent.NAME:
			handleElevatorOpenedDoorEvent((ElevatorOpenedDoorEvent) event);
		}
	}
	
	
	private void handleElevatorArrivalEvent(ElevatorArrivalEvent event) {
		if (event.getFloor() == destinationQueue.getFirst()) {
			
		} else {
			elevatorSocket.sendEventIn(new ElevatorTransitEvent());
		}
	}
	
	private void handleElevatorButtonEvent(ElevatorButtonEvent event) {
		
	}
	
	private void handleElevatorDirectionLampEvent(ElevatorDirectionLampEvent event) {
		
	}
	
	private void handleElevatorDoorEvent(ElevatorDoorEvent event) {
		

	private void handleElevatorArrivalSensorEvent(ElevatorArrivalSensorEvent event) {
		if (event.getArrivingFloor() == destinationQueue.getFirst()) {
			//SEND STOP REQUEST EVENT
		} else {
			//SEND ACKNOWLEDGEMENT
		}
		elevatorCurrentFloor = event.getArrivingFloor();
	}
	
	private void handleElevatorPressedButtonEvent(ElevatorPressedButtonEvent event) {
		scheduleElevator(event.getDesiredFloor());
	}
	
	private void handleElevatorClosedDoorEvent(ElevatorClosedDoorEvent event) {
		int destination = destinationQueue.getFirst();
		//TODO: TELL ELEVATOR TO START MOVING IN CORRECT DIRECTION AND SET ELEVATOR DIRECTION
	}
	
	
	private void handleFloorButtonEvent(FloorButtonEvent event) {
		//do nothing if elevator is already waiting at the correct floor with doors open
		if (! (elevatorIsIdle && elevatorCurrentFloor == event.getFloor())) {
			FloorLampEvent response = new FloorLampEvent(LampState.ON, event.getDirection(), event.getSender(), SCHEDULER_ID);
			floorSocket.sendEventIn(response);
			scheduleElevator(event.getFloor());
		}
	}
	
	private void handleElevatorStoppedEvent(ElevatorStoppedEvent event) {
		//TODO: TELL FLOOR TO TURN OFF APPROPRIATE DIRECTION LAMP
		//TODO: TELL ELEVATOR TO OPEN DOORS
		destinationQueue.pop();
	}
	
	private void handleElevatorOpenedDoorEvent(ElevatorOpenedDoorEvent event) {
		if (destinationQueue.isEmpty()) {
			elevatorIsIdle = true;
		} else {
			closeElevatorDoors();
		}
	}
}
