package elevator;

import common.CommunicationSocket;
import common.Direction;
import event.toScheduler.*;
import event.toElevator.*;
import event.Event;

public class Elevator {
	
	private CommunicationSocket elevatorSocket;
	private ElevatorState state;
	
	/**
	 * Creates a new Elevator
	 * @param elevatorSocket the socket for the elevator to receive and send events on
	 * @param numberOfFloors the number of floors the elevator must service
	 */
	public Elevator(CommunicationSocket elevatorSocket, int numberOfFloors) {
		this.elevatorSocket = elevatorSocket;
		this.state = new ElevatorOpenDoorState(numberOfFloors);
	}
	
	
	/**
	 * Receives an event
	 * @return the received event
	 */
	public Event recieveEventIn(){
		Event event = this.elevatorSocket.recieveEventIn();
		return event;
	}
	
	/**
	 * Sends an event
	 * @param event event to send
	 */
	public void sendEventOut(Event event){
		this.elevatorSocket.sendEventOut(event);
	}
	
	/**
	 * Handles an elevator event
	 * @param event event to handle
	 */
	public void handleElevatorEvent(Event event){;
		switch (event.getName()) {
			case ElevatorCloseDoorEvent.NAME:
				this.handleElevatorCloseDoorEvent((ElevatorCloseDoorEvent) event);
				break;
			case ElevatorPressButtonEvent.NAME:
				this.handleElevatorPressButtonEvent((ElevatorPressButtonEvent) event);
				break;
			case ElevatorDirectionLampEvent.NAME:
				this.handleElevatorDirectionLampEvent((ElevatorDirectionLampEvent) event);
				break;
			case ElevatorOpenDoorEvent.NAME:
				this.handleElevatorOpenDoorEvent((ElevatorOpenDoorEvent) event);
				break;
			case ElevatorStartMovingEvent.NAME:
				this.handleElevatorStartMovingEvent((ElevatorStartMovingEvent) event);
				break;
			case ElevatorStopMovingEvent.NAME:
				this.handleElevatorStopMovingEvent((ElevatorStopMovingEvent) event);
				break;
			case ElevatorKeepMovingEvent.NAME:
				this.handleElevatorKeepMovingEvent((ElevatorKeepMovingEvent) event);
				break;
		}
	}
	
	/**
	 * Elevator implementation for the handling of ElevatorDirectionLampEvent
	 * @param elevatorDirectionLampEvent the event modeling the on/off of a direction lamp
	 */
	public void handleElevatorDirectionLampEvent(ElevatorDirectionLampEvent elevatorDirectionLampEvent) {
		this.state = this.state.handleElevatorDirectionLampEvent(elevatorDirectionLampEvent);
	}
	
	/**
	 * Elevator implementation for the handling of ElevatorPressButtonEvent
	 * @param elevatorPressButtonEvent the event modeling the press of a button
	 */
	public void handleElevatorPressButtonEvent(ElevatorPressButtonEvent elevatorPressButtonEvent) {
		this.state = this.state.handleElevatorPressButtonEvent(elevatorPressButtonEvent);
		int recipient = elevatorPressButtonEvent.getRecipient();
		int sender = elevatorPressButtonEvent.getSender();
		int button = elevatorPressButtonEvent.getButton();
		ElevatorPressedButtonEvent event = new ElevatorPressedButtonEvent(sender, recipient, button);
		this.sendEventOut(event);
	}	

	/**
	 * Elevator implementation for the handling of ElevatorCloseDoorEvent
	 * @param elevatorCloseDoorEvent the event modeling the closing of a door
	 */
	public void handleElevatorCloseDoorEvent(ElevatorCloseDoorEvent elevatorCloseDoorEvent) {
		this.state = this.state.handleElevatorCloseDoorEvent(elevatorCloseDoorEvent);
		//Sleep to simulate door closing
		this.state = this.state.handleElevatorCloseDoorEvent(elevatorCloseDoorEvent);
		int recipient = elevatorCloseDoorEvent.getRecipient();
		int sender = elevatorCloseDoorEvent.getSender();
		ElevatorClosedDoorEvent event = new ElevatorClosedDoorEvent(sender, recipient);
		this.sendEventOut(event);
	}
	
	/**
	 * Elevator implementation for the handling of elevatorOpenDoorEvent
	 * @param elevatorCloseDoorEvent the event modeling the opening of a door
	 */
	public void handleElevatorOpenDoorEvent(ElevatorOpenDoorEvent elevatorOpenDoorEvent) {
		this.state = this.state.handleElevatorOpenDoorEvent(elevatorOpenDoorEvent);
		//Sleep to simulate door opening
		this.state = this.state.handleElevatorOpenDoorEvent(elevatorOpenDoorEvent);
		int recipient = elevatorOpenDoorEvent.getRecipient();
		int sender = elevatorOpenDoorEvent.getSender();
		ElevatorOpenedDoorEvent event = new ElevatorOpenedDoorEvent(sender, recipient);
		this.sendEventOut(event);
	}
	
	/**
	 * Elevator implementation for the handling of ElevatorStartMovingEvent
	 * @param elevatorStartMovingEvent the event modeling the start moving
	 */
	public void handleElevatorStartMovingEvent(ElevatorStartMovingEvent elevatorStartMovingEvent) {
		this.state = this.state.handleElevatorStartMovingEvent(elevatorStartMovingEvent);
		int recipient = elevatorStartMovingEvent.getRecipient();
		int sender = elevatorStartMovingEvent.getSender();
		int arrivingFloor = this.state.getCurrentFloor();
		ElevatorArrivalSensorEvent event = new ElevatorArrivalSensorEvent(sender, recipient, arrivingFloor);
		this.sendEventOut(event);
	}
	
	/**
	 * Elevator implementation for the handling of ElevatorKeepMovingEvent
	 * @param elevatorArrivalEvent the event modeling the keep moving
	 */
	public void handleElevatorKeepMovingEvent(ElevatorKeepMovingEvent elevatorKeepMovingEvent) {
		this.state = this.state.handleElevatorKeepMovingEvent(elevatorKeepMovingEvent);
	}
	
	/**
	 * Elevator implementation for the handling of ElevatorStopMovingEvent
	 * @param elevatorStopMovingEvent the event modeling the stop moving
	 */
	public void handleElevatorStopMovingEvent(ElevatorStopMovingEvent elevatorStopMovingEvent) {
		this.state = this.state.handleElevatorStopMovingEvent(elevatorStopMovingEvent);
	}
}
