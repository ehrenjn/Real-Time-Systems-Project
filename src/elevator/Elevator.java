package elevator;

import common.CommunicationSocket;
import common.Direction;
import event.ElevatorArrivalEvent;
import event.ElevatorButtonEvent;
import event.ElevatorButtonLampEvent;
import event.ElevatorDirectionLampEvent;
import event.ElevatorDoorEvent;
import event.ElevatorTransitEvent;
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
	public Event recieveEventIn()
	{
		return this.elevatorSocket.recieveEventIn();
	}
	
	/**
	 * Sends an event
	 * @param event event to send
	 */
	public void sendEventOut(Event event)
	{
		this.elevatorSocket.sendEventOut(event);
	}
	
	/**
	 * Elevator implementation for the handling of elevatorButtonEvent
	 * @param elevatorButtonEvent the event modeling the press of a button
	 */
	public void handleElevatorButtonEvent(ElevatorButtonEvent elevatorButtonEvent) {
		this.state = this.state.handleElevatorButtonEvent(elevatorButtonEvent);
		return;
	}
	
	/**
	 * Elevator implementation for the handling of elevatorDirectionLampEvent
	 * @param elevatorDirectionLampEvent the event modeling the on/off of a direction lamp
	 */
	public void handleElevatorDirectionLampEvent(ElevatorDirectionLampEvent elevatorDirectionLampEvent) {
		this.state = this.state.handleElevatorDirectionLampEvent(elevatorDirectionLampEvent);
	}
	
	/**
	 * Elevator implementation for the handling of elevatorButtonLampEvent
	 * @param elevatorButtonLampEvent the event modeling the on/off of a button lamp
	 */
	public void handleElevatorButtonLampEvent(ElevatorButtonLampEvent elevatorButtonLampEvent) {
		this.state = this.state.handleElevatorButtonLampEvent(elevatorButtonLampEvent);
	}
		
	/**
	 * Elevator implementation for the handling of elevatorDoorEvent
	 * @param elevatorDoorEvent the event modeling the opening/closing of a door
	 */
	public void handleElevatorDoorEvent(ElevatorDoorEvent elevatorDoorEvent) {
		this.state = this.state.handleElevatorDoorEvent(elevatorDoorEvent);
	}
	
	/**
	 * Elevator implementation for the handling of elevatorTransitEvent
	 * @param elevatorTransitEvent the event modeling the acceleration/deceleration of an elevator
	 */
	public void handleElevatorTransitEvent(ElevatorTransitEvent elevatorTransitEvent) {
		this.state = this.state.handleElevatorTransitEvent(elevatorTransitEvent);
	}
	
	/**
	 * Elevator implementation for the handling of elevatorArrivalEvent
	 * @param elevatorArrivalEvent the event modeling the arrival sensor of an elevator
	 */
	public void handleElevatorArrivalEvent(ElevatorArrivalEvent elevatorArrivalEvent) {
		this.state = this.state.handleElevatorArrivalEvent(elevatorArrivalEvent);
	}
	
}
