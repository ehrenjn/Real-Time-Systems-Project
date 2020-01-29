package scheduler;

import common.CommunicationSocket;
import common.Event;

public class Scheduler {
	private CommunicationSocket elevatorSocket;
	private CommunicationSocket floorSocket;
	
	public Scheduler(CommunicationSocket elevatorSocket, CommunicationSocket floorSocket) {
		this.elevatorSocket = elevatorSocket;
		this.floorSocket = floorSocket;
	}
	
	public void sendFloorEventIn(Event event) {
		this.floorSocket.sendEventIn(event);
	}
	
	public Event recieveFloorEventOut() {
		return this.floorSocket.recieveEventOut();
	}
	
	public void sendElevatorEventIn(Event event) {
		this.elevatorSocket.sendEventIn(event);
	}
	
	public Event recieveElevatorEventOut() {
		return this.elevatorSocket.recieveEventOut();
	}
}
