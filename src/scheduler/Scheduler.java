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
	private ElevatorInfo[] elevators;
	private LinkedList<FloorPressButtonEvent> unfulfilledRequests;
	
	/**
	 * Constructs a new Scheduler
	 * @param elevatorSocket represents a communicationSocket which communicates the elevator events
	 * @param floorSocket represents the communicationSocket which communicates the floor events
	 */
	public Scheduler(CommunicationSocket elevatorSocket, CommunicationSocket floorSocket) {
		this.elevatorSocket = elevatorSocket;
		this.floorSocket = floorSocket;
		this.elevators = new ElevatorInfo[Constants.NUM_ELEVATORS];
		for (int id = 0; id < elevators.length; id++) {
			elevators[id] = new ElevatorInfo(id);
		}
		unfulfilledRequests = new LinkedList<FloorPressButtonEvent>();
	}
	
	
	/**
	 * sends the floor event to the client
	 */
	public void sendFloorEventIn(Event event) {
		System.out.println("Scheduler sending FLOOR event: " + event);
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
	
	
	private Direction floorsToDirection(int initialFloor, int destinationFloor) {
		return destinationFloor < initialFloor ? Direction.DOWN : Direction.UP;
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
		ElevatorInfo bestElevator = ALGORITHM(event);
		
		// no available elevator was found
		if (bestElevator == null) {
			unfulfilledRequests.add(event);
		} 
		
		// we found an elevator, let's make it move
		else {
			// start moving the elevator if it isn't moving already
			if (! bestElevator.hasStops() && bestElevator.getDirectionOfMovement() == Direction.IDLE) {
				sendElevatorEventIn(new ElevatorPressButtonEvent(bestElevator.getId(), SCHEDULER_ID, event.getDesiredFloor()));
			}
			bestElevator.addStop(event.getCurrentFloor());
		}
	}
	
	
	public void handleElevatorClosedDoorEvent(ElevatorClosedDoorEvent event) {
		ElevatorInfo sender = elevators[event.getSender()];
		Direction elevatorDirection = floorsToDirection(sender.getCurrentFloor(), sender.getNextStop());
		sender.setDirectionOfMovement(elevatorDirection);
		sendElevatorEventIn(new ElevatorDirectionLampEvent(
				sender.getId(), SCHEDULER_ID, elevatorDirection, LampState.ON));
		sendElevatorEventIn(new ElevatorStartMovingEvent(event.getSender(), SCHEDULER_ID, elevatorDirection));
	}
	
	
	public void handleElevatorArrivalSensorEvent(ElevatorArrivalSensorEvent event) {
		ElevatorInfo sender = elevators[event.getSender()];
		if (event.getArrivingFloor() == sender.getNextStop()) {
			sendElevatorEventIn(new ElevatorStopMovingEvent(sender.getId(), SCHEDULER_ID));
		} else {
			sendElevatorEventIn(new ElevatorKeepMovingEvent(sender.getId(), SCHEDULER_ID, sender.getDirectionOfMovement()));
		}
		sender.setCurrentFloor(event.getArrivingFloor());
	}
	
	
	public void handleElevatorPressedButtonEvent(ElevatorPressedButtonEvent event) {
		ElevatorInfo sender = elevators[event.getSender()];
		sender.addStop(event.getDesiredFloor());
		sendElevatorEventIn(new ElevatorCloseDoorEvent(ELEVATOR_ID, SCHEDULER_ID));
	}
	
	
	public void handleElevatorStoppedEvent(ElevatorStoppedEvent event) {
		ElevatorInfo sender = elevators[event.getSender()];
		sendElevatorEventIn(new ElevatorDirectionLampEvent(
				event.getSender(), SCHEDULER_ID, sender.getDirectionOfMovement(), LampState.OFF));
		sendElevatorEventIn(new ElevatorOpenDoorEvent(sender.getId(), SCHEDULER_ID));
	}
	
	
	public void handleElevatorOpenedDoorEvent(ElevatorOpenedDoorEvent event) {
		ElevatorInfo sender = elevators[event.getSender()];
		sender.popNextStop();
		if (sender.hasStops()) { //there was someone waiting for this elevator to go to another floor
			int nextDestination = sender.getNextStop();
			Direction floorDirectionLamp = floorsToDirection(sender.getCurrentFloor(), nextDestination);
			sendFloorEventIn(new FloorLampEvent(sender.getCurrentFloor(), SCHEDULER_ID, 
					LampState.OFF, floorDirectionLamp));
			sendElevatorEventIn(new ElevatorButtonLampEvent(sender.getId(), SCHEDULER_ID, 
					nextDestination, LampState.ON));
			sendElevatorEventIn(new ElevatorCloseDoorEvent(sender.getId(), SCHEDULER_ID));
		} 
		
		// do one of the unfulfilled requests if there's nowhere else to go
		else if (! unfulfilledRequests.isEmpty()) { 
			FloorPressButtonEvent request = unfulfilledRequests.pop();
			sendElevatorEventIn(new ElevatorPressButtonEvent(sender.getId(), SCHEDULER_ID, request.getDesiredFloor()));
			sender.addStop(request.getCurrentFloor());
		} 
		
		// there's no unfulfilled requests so just chill
		else {
			sender.setDirectionOfMovement(Direction.IDLE);
		}
	}
}
