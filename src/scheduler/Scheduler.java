package scheduler;

import java.util.LinkedList;

import common.CommunicationSocket;
import event.toScheduler.*;
import event.toElevator.*;
import event.*;
import common.*;

public class Scheduler {
	private SchedulerMessageQueue messageQueue;
	public static final int SCHEDULER_ID = 0;
	
	//elevator state variables
	private ElevatorInfo[] elevators;
	private LinkedList<FloorPressButtonEvent> unfulfilledRequests;
	
	/**
	 * Constructs a new Scheduler
	 * @param elevatorSocket represents a communicationSocket which communicates the elevator events
	 * @param floorSocket represents the communicationSocket which communicates the floor events
	 */
	public Scheduler(SchedulerMessageQueue messageQueue) {
		this.messageQueue = messageQueue;
		this.elevators = new ElevatorInfo[Constants.NUM_ELEVATORS];
		for (int id = 0; id < elevators.length; id++) {
			elevators[id] = new ElevatorInfo(id);
		}
		unfulfilledRequests = new LinkedList<FloorPressButtonEvent>();
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
				messageQueue.sendEventToElevators(new ElevatorPressButtonEvent(
						bestElevator.getId(), SCHEDULER_ID, event.getDesiredFloor()));
			}
			bestElevator.addStop(event.getCurrentFloor());
		}
	}
	
	
	public void handleElevatorClosedDoorEvent(ElevatorClosedDoorEvent event) {
		ElevatorInfo sender = elevators[event.getSender()];
		Direction elevatorDirection = floorsToDirection(sender.getCurrentFloor(), sender.getNextStop());
		sender.setDirectionOfMovement(elevatorDirection);
		messageQueue.sendEventToElevators(new ElevatorDirectionLampEvent(
				sender.getId(), SCHEDULER_ID, elevatorDirection, LampState.ON));
		messageQueue.sendEventToElevators(new ElevatorStartMovingEvent(event.getSender(), SCHEDULER_ID, elevatorDirection));
	}
	
	
	public void handleElevatorArrivalSensorEvent(ElevatorArrivalSensorEvent event) {
		ElevatorInfo sender = elevators[event.getSender()];
		if (event.getArrivingFloor() == sender.getNextStop()) {
			messageQueue.sendEventToElevators(new ElevatorStopMovingEvent(sender.getId(), SCHEDULER_ID));
		} else {
			messageQueue.sendEventToElevators(new ElevatorKeepMovingEvent(
					sender.getId(), SCHEDULER_ID, sender.getDirectionOfMovement()));
		}
		sender.setCurrentFloor(event.getArrivingFloor());
	}
	
	
	public void handleElevatorPressedButtonEvent(ElevatorPressedButtonEvent event) {
		ElevatorInfo sender = elevators[event.getSender()];
		sender.addStop(event.getDesiredFloor());
		messageQueue.sendEventToElevators(new ElevatorCloseDoorEvent(sender.getId(), SCHEDULER_ID));
	}
	
	
	public void handleElevatorStoppedEvent(ElevatorStoppedEvent event) {
		ElevatorInfo sender = elevators[event.getSender()];
		messageQueue.sendEventToElevators(new ElevatorDirectionLampEvent(
				event.getSender(), SCHEDULER_ID, sender.getDirectionOfMovement(), LampState.OFF));
		messageQueue.sendEventToElevators(new ElevatorOpenDoorEvent(sender.getId(), SCHEDULER_ID));
	}
	
	
	public void handleElevatorOpenedDoorEvent(ElevatorOpenedDoorEvent event) {
		ElevatorInfo sender = elevators[event.getSender()];
		sender.popNextStop();
		if (sender.hasStops()) { //there was someone waiting for this elevator to go to another floor
			int nextDestination = sender.getNextStop();
			Direction floorDirectionLamp = floorsToDirection(sender.getCurrentFloor(), nextDestination);
			sendFloorEventIn(new FloorLampEvent(sender.getCurrentFloor(), SCHEDULER_ID, 
					LampState.OFF, floorDirectionLamp));
			messageQueue.sendEventToElevators(new ElevatorButtonLampEvent(sender.getId(), SCHEDULER_ID, 
					nextDestination, LampState.ON));
			messageQueue.sendEventToElevators(new ElevatorCloseDoorEvent(sender.getId(), SCHEDULER_ID));
		} 
		
		// do one of the unfulfilled requests if there's nowhere else to go
		else if (! unfulfilledRequests.isEmpty()) { 
			FloorPressButtonEvent request = unfulfilledRequests.pop();
			messageQueue.sendEventToElevators(new ElevatorPressButtonEvent(
					sender.getId(), SCHEDULER_ID, request.getDesiredFloor()));
			sender.addStop(request.getCurrentFloor());
		} 
		
		// there's no unfulfilled requests so just chill
		else {
			sender.setDirectionOfMovement(Direction.IDLE);
		}
	}
}
