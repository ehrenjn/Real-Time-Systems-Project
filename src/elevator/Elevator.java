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
				this.handleElevatorStartMovingEvent((ElevatorStopMovingEvent) event);
				break;
			case ElevatorKeepMovingEvent.NAME:
				this.handleElevatorStartMovingEvent((ElevatorKeepMovingEvent) event);
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
	}	

	/**
	 * Elevator implementation for the handling of ElevatorCloseDoorEvent
	 * @param elevatorCloseDoorEvent the event modeling the closing of a door
	 */
	public void handleElevatorCloseDoorEvent(ElevatorCloseDoorEvent elevatorCloseDoorEvent) {
	}
	
	/**
	 * Elevator implementation for the handling of elevatorOpenDoorEvent
	 * @param elevatorCloseDoorEvent the event modeling the opening of a door
	 */
	public void handleElevatorOpenDoorEvent(ElevatorOpenDoorEvent elevatorOpenDoorEvent) {
	}
	
	/**
	 * Elevator implementation for the handling of ElevatorStartMovingEvent
	 * @param elevatorStartMovingEvent the event modeling the start moving
	 */
	public void handleElevatorStartMovingEvent(ElevatorStartMovingEvent elevatorStartMovingEvent) {
	}
	
	/**
	 * Elevator implementation for the handling of ElevatorStopMovingEvent
	 * @param elevatorStopMovingEvent the event modeling the stop moving
	 */
	public void handleElevatorStartMovingEvent(ElevatorStopMovingEvent elevatorStopMovingEvent) {
	}
	
	/**
	 * Elevator implementation for the handling of ElevatorKeepMovingEvent
	 * @param elevatorArrivalEvent the event modeling the keep moving
	 */
	public void handleElevatorStartMovingEvent(ElevatorKeepMovingEvent elevatorArrivalEvent) {
	}
}
