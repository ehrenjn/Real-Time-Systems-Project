package elevator;

import common.CommunicationSocket;
import common.Direction;
import common.Event;

public class Elevator {
	private Direction direction;
	private int floor;
	private CommunicationSocket elevatorSocket;
	
	/**
	 * Creates a new Elevator
	 * @param elevatorSocket the socket for the elevator to receive and send events on
	 */
	public Elevator(CommunicationSocket elevatorSocket) {
		this.elevatorSocket = elevatorSocket;
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
