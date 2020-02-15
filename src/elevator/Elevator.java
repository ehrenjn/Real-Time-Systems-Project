package elevator;

import common.CommunicationSocket;
import common.Direction;
import event.Event;

public class Elevator {
	
	private CommunicationSocket elevatorSocket;
	private ElevatorState state;
	
	/**
	 * Creates a new Elevator
	 * @param elevatorSocket the socket for the elevator to receive and send events on
	 * @param numberOfFloors the number of floors the elevator must serivce
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
	
}
