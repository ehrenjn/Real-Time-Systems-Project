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
		System.out.println("Elevator sending: " + event);
		if (event instanceof ElevatorArrivalSensorEvent) {
			System.out.println("\tArrival sensor floor: " + ((ElevatorArrivalSensorEvent)event).getArrivingFloor());
		}
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
		ElevatorPressedButtonEvent event = new ElevatorPressedButtonEvent(button, sender, recipient);
		this.sendEventOut(event);
	}	

	/**
	 * Elevator implementation for the handling of ElevatorCloseDoorEvent
	 * @param elevatorCloseDoorEvent the event modeling the closing of a door
	 */
	public void handleElevatorCloseDoorEvent(ElevatorCloseDoorEvent elevatorCloseDoorEvent) {
		this.state = this.state.handleElevatorCloseDoorEvent(elevatorCloseDoorEvent);
		//Sleep to simulate door closing
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int arrivingFloor = this.state.getCurrentFloor() + getFloorIncrement(elevatorStartMovingEvent.getDirection());
		this.state.setCurrentFloor(arrivingFloor);
		
		ElevatorArrivalSensorEvent event = new ElevatorArrivalSensorEvent(arrivingFloor, sender, recipient);
		this.sendEventOut(event);
	}
	
	/**
	 * Elevator implementation for the handling of ElevatorKeepMovingEvent
	 * @param elevatorArrivalEvent the event modeling the keep moving
	 */
	public void handleElevatorKeepMovingEvent(ElevatorKeepMovingEvent elevatorKeepMovingEvent) {
		this.state = this.state.handleElevatorKeepMovingEvent(elevatorKeepMovingEvent);
		int recipient = elevatorKeepMovingEvent.getRecipient();
		int sender = elevatorKeepMovingEvent.getSender();
		
		int arrivingFloor = this.state.getCurrentFloor() + getFloorIncrement(elevatorKeepMovingEvent.getDirection());
		this.state.setCurrentFloor(arrivingFloor);
		
		ElevatorArrivalSensorEvent event = new ElevatorArrivalSensorEvent(this.state.getCurrentFloor(), sender, recipient);
		this.sendEventOut(event);
	}
	
	/**
	 * Elevator implementation for the handling of ElevatorStopMovingEvent
	 * @param elevatorStopMovingEvent the event modeling the stop moving
	 */
	public void handleElevatorStopMovingEvent(ElevatorStopMovingEvent elevatorStopMovingEvent) {
		this.state = this.state.handleElevatorStopMovingEvent(elevatorStopMovingEvent);
		int recipient = elevatorStopMovingEvent.getRecipient();
		int sender = elevatorStopMovingEvent.getSender();
		
		ElevatorStoppedEvent event = new ElevatorStoppedEvent(sender, recipient);
		this.sendEventOut(event);
	}
	
	/**
	 * @return the floor increment given a direction
	 */
	public static int getFloorIncrement(Direction direction) {
		switch(direction) {
			case UP:
				return 1;
			case DOWN:
				return -1;
			default:
				return 0;
		}
	}


	@Override
	public String toString() {
		return state.toString();
	}
}
