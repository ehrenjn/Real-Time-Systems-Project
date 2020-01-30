package floor;

import java.util.ArrayList;
import common.CommunicationSocket;
import common.Event;

public class Floor{
	private int floorNumber;
	private DirectionLamp[] directionLamps;
	private FloorLamp[] floorLamps;
	private FloorButton[] floorButtons;
	private ArrayList<Event> upEventList;
	private ArrayList<Event> downEventList;
	private CommunicationSocket floorSocket;
	
	public Floor(CommunicationSocket floorSocket, int floorNumber) {
		this.floorNumber = floorNumber;
		this.floorSocket = floorSocket;
	}
	
	public Event recieveEventIn()
	{
		return this.floorSocket.recieveEventIn();
	}
	
	public void sendEventOut(Event event)
	{
		this.floorSocket.sendEventOut(event);
	}
	
}

