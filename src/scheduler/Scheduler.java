package scheduler;

import common.CommunicationSocket;
import event.*;

public class Scheduler {
	private CommunicationSocket elevatorSocket;
	private CommunicationSocket floorSocket;
	
	/**
	 * Constructs a new Scheduler
	 * @param elevatorSocket represents a communicationSocket which communicates the elevator events
	 * @param floorSocket represents the communicationSocket which communicates the floor events
	 */
	public Scheduler(CommunicationSocket elevatorSocket, CommunicationSocket floorSocket) {
		this.elevatorSocket = elevatorSocket;
		this.floorSocket = floorSocket;
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
	
	public void handleEvent(Event event) {
		switch (event.getName()) {
		case ElevatorArrivalEvent.NAME:
			handleElevatorArrivalEvent((ElevatorArrivalEvent) event);
			break;
		case ElevatorButtonEvent.NAME:
			handleElevatorButtonEvent((ElevatorButtonEvent) event);
			break;
		case ElevatorDirectionLampEvent.NAME:
			handleElevatorDirectionLampEvent((ElevatorDirectionLampEvent) event);
			break;
		case ElevatorDoorEvent.NAME:
			handleElevatorDoorEvent((ElevatorDoorEvent) event);
			break;
		case ElevatorTransitEvent.NAME:
			handleElevatorTransitEvent((ElevatorTransitEvent) event);
			break;
		}
	}
	
	
	private void handleElevatorArrivalEvent(ElevatorArrivalEvent event) {
		
	}
	
	private void handleElevatorButtonEvent(ElevatorButtonEvent event) {
		
	}
	
	private void handleElevatorDirectionLampEvent(ElevatorDirectionLampEvent event) {
		
	}
	
	private void handleElevatorDoorEvent(ElevatorDoorEvent event) {
		
	}
	
	private void handleElevatorTransitEvent(ElevatorTransitEvent event) {
		
	}
}
