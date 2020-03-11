package scheduler;

import java.util.LinkedList;

import event.toScheduler.*;
import network.MultiRecipientEventQueue;
import event.toElevator.*;
import event.*;
import common.*;

public class Scheduler {
	private MultiRecipientEventQueue elevatorEventQueue;
	private MultiRecipientEventQueue floorEventQueue;
	public static final int SCHEDULER_ID = 0;
	
	//elevator state variables
	
	/**
	 * @param elevators An array of elevators available to the scheduler. 
	 */
	private ElevatorInfo[] elevators;
	private LinkedList<FloorPressButtonEvent> unfulfilledRequests;
	
	/**
	 * Constructs a new Scheduler
	 * @param elevatorSocket represents a communicationSocket which communicates the elevator events
	 * @param floorSocket represents the communicationSocket which communicates the floor events
	 */
	public Scheduler(MultiRecipientEventQueue elevatorEventQueue, MultiRecipientEventQueue floorEventQueue) {
		this.elevatorEventQueue = elevatorEventQueue;
		this.floorEventQueue = floorEventQueue;
		this.elevators = new ElevatorInfo[Constants.NUM_ELEVATORS];
		for (int id = 0; id < elevators.length; id++) {
			elevators[id] = new ElevatorInfo(id);
		}
		unfulfilledRequests = new LinkedList<FloorPressButtonEvent>();
	}
	
	
	private Direction floorsToDirection(int initialFloor, int destinationFloor) {
		return destinationFloor < initialFloor ? Direction.DOWN : Direction.UP;
	}
	
	
	/**
	 * Returns true IF the elevator is moving toward the floor of the buttonEvent.
	 * 
	 * @param elevatorInfo
	 * @param inputEvent
	 * @return boolean
	 */
	public boolean elevatorCanPickup(ElevatorInfo elevator, FloorPressButtonEvent event) {
		ThreadPrinter.print(elevator);
		if (elevator.hasStops()) {
			if ((elevator.getDirectionOfMovement() == Direction.UP) && (event.getDirection() == Direction.UP) &&
			(elevator.getCurrentFloor() < event.getCurrentFloor()) && (elevator.getNextStop() > event.getDesiredFloor())) {
				return true;
			}
			if ((elevator.getDirectionOfMovement() == Direction.DOWN) && (event.getDirection() == Direction.DOWN) &&
			(elevator.getCurrentFloor() > event.getCurrentFloor()) && (elevator.getNextStop() < event.getDesiredFloor())) {
				return true;
			}
		}
		else if (elevator.getDirectionOfMovement() == Direction.IDLE) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the nearest elevator to the eventFloor that is either idle, or in transit towards the eventFloor & traveling in the same direction of the event.
	 * 
	 * @param inputEvent Expect a floor event from a user pushing an "UP" or "DOWN" button.
	 * @return bestElevator the nearest elevator to the eventFloor
	 */
	public ElevatorInfo findBestElevator(FloorPressButtonEvent inputEvent) {
		ElevatorInfo bestElevator = null;
		
		for (ElevatorInfo elevatorInfo : elevators) {
			if (elevatorCanPickup(elevatorInfo, inputEvent)) {
				if (bestElevator == null) {
					bestElevator = elevatorInfo;
				}
				else if((bestElevator.getCurrentFloor() - inputEvent.getCurrentFloor()) < 
				(bestElevator.getCurrentFloor() - inputEvent.getCurrentFloor())) {
					bestElevator = elevatorInfo;
				}
			}
		}
		return bestElevator;
	}

	public void handleFloorPressButtonEvent(FloorPressButtonEvent event) {
		ThreadPrinter.print("in handleFloorPressButtonEvent");
		ElevatorInfo bestElevator = findBestElevator(event);
		ThreadPrinter.print("best elevator found: " + bestElevator);
		
		// no available elevator was found
		if (bestElevator == null) {
			ThreadPrinter.print("no best elevator found");
			unfulfilledRequests.add(event);
		}
		
		// we found an elevator, let's make it move
		else {
			// start moving the elevator if it isn't moving already
			if (! bestElevator.hasStops() && bestElevator.getDirectionOfMovement() == Direction.IDLE) {
				elevatorEventQueue.addEvent(new ElevatorPressButtonEvent(
						bestElevator.getId(), SCHEDULER_ID, event.getDesiredFloor()));
				// don't move the elevator if it's already on the right floor
				if (bestElevator.getCurrentFloor() != event.getCurrentFloor()) {
					bestElevator.appendStop(event.getCurrentFloor());
				}
			} else {
				bestElevator.addIntermediateStop(event.getCurrentFloor());
				bestElevator.addIntermediateStop(event.getDesiredFloor());
			}
		}
	}
	
	
	public void handleElevatorClosedDoorEvent(ElevatorClosedDoorEvent event) {
		ElevatorInfo sender = elevators[event.getSender()];
		ThreadPrinter.print("elevator info: " + sender);
		Direction elevatorDirection = floorsToDirection(sender.getCurrentFloor(), sender.getNextStop());
		sender.setDirectionOfMovement(elevatorDirection);
		elevatorEventQueue.addEvent(new ElevatorDirectionLampEvent(
				sender.getId(), SCHEDULER_ID, elevatorDirection, LampState.ON));
		elevatorEventQueue.addEvent(new ElevatorStartMovingEvent(event.getSender(), SCHEDULER_ID, elevatorDirection));
	}
	
	
	public void handleElevatorArrivalSensorEvent(ElevatorArrivalSensorEvent event) {
		ElevatorInfo sender = elevators[event.getSender()];
		ThreadPrinter.print("Arriving at floor " + event.getArrivingFloor());
		ThreadPrinter.print("elevator info: " + sender);
		if (event.getArrivingFloor() == sender.getNextStop()) {
			elevatorEventQueue.addEvent(new ElevatorStopMovingEvent(sender.getId(), SCHEDULER_ID));
		} else {
			elevatorEventQueue.addEvent(new ElevatorKeepMovingEvent(
					sender.getId(), SCHEDULER_ID, sender.getDirectionOfMovement()));
		}
		sender.setCurrentFloor(event.getArrivingFloor());
	}
	
	
	public void handleElevatorPressedButtonEvent(ElevatorPressedButtonEvent event) {
		ElevatorInfo sender = elevators[event.getSender()];
		ThreadPrinter.print("elevator info: " + sender);
		sender.appendStop(event.getDesiredFloor());
		elevatorEventQueue.addEvent(new ElevatorCloseDoorEvent(sender.getId(), SCHEDULER_ID));
	}
	
	
	public void handleElevatorStoppedEvent(ElevatorStoppedEvent event) {
		ElevatorInfo sender = elevators[event.getSender()];
		ThreadPrinter.print("elevator info: " + sender);
		elevatorEventQueue.addEvent(new ElevatorDirectionLampEvent(
				event.getSender(), SCHEDULER_ID, sender.getDirectionOfMovement(), LampState.OFF));
		elevatorEventQueue.addEvent(new ElevatorOpenDoorEvent(sender.getId(), SCHEDULER_ID));
	}
	
	
	public void handleElevatorOpenedDoorEvent(ElevatorOpenedDoorEvent event) {
		ElevatorInfo sender = elevators[event.getSender()];
		ThreadPrinter.print("elevator info: " + sender);
		sender.popNextStop();
		if (sender.hasStops()) { //there was someone waiting for this elevator to go to another floor
			int nextDestination = sender.getNextStop();
			Direction floorDirectionLamp = floorsToDirection(sender.getCurrentFloor(), nextDestination);
			floorEventQueue.addEvent(new FloorLampEvent(sender.getCurrentFloor(), SCHEDULER_ID, 
					LampState.OFF, floorDirectionLamp));
			elevatorEventQueue.addEvent(new ElevatorButtonLampEvent(sender.getId(), SCHEDULER_ID, 
					nextDestination, LampState.ON));
			elevatorEventQueue.addEvent(new ElevatorCloseDoorEvent(sender.getId(), SCHEDULER_ID));
		} 
		
		// do one of the unfulfilled requests if there's nowhere else to go
		else if (! unfulfilledRequests.isEmpty()) { 
			FloorPressButtonEvent request = unfulfilledRequests.pop();
			elevatorEventQueue.addEvent(new ElevatorPressButtonEvent(
					sender.getId(), SCHEDULER_ID, request.getDesiredFloor()));
			sender.appendStop(request.getCurrentFloor());
		} 
		
		// there's no unfulfilled requests so just chill
		else {
			sender.setDirectionOfMovement(Direction.IDLE);
		}
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
}
