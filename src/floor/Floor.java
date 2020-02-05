package floor;

import java.util.ArrayList;
import common.CommunicationSocket;
import common.Event;

public class Floor{
	private int floorNumber;
	private CommunicationSocket floorSocket;
	
	/**
	 * Creates a new CommunicationSocket for floor
	 * Creates # of floors
	 */
	public Floor(CommunicationSocket floorSocket, int floorNumber) {
		this.floorNumber = floorNumber;
		this.floorSocket = floorSocket;
	}
	
	/**
	 * Receives floor event from the server
	 * @return the floor event that was received
	 */
	public Event recieveEventIn()
	{
		return this.floorSocket.recieveEventIn();
	}
	
	/**
	 * Sends the floor event to the server
	 * @param event is the event to send
	 */
	
	public void sendEventOut(Event event)
	{
		this.floorSocket.sendEventOut(event);
	}
	
}

