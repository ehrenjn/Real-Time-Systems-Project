package elevator;

import common.CommunicationSocket;
import common.Direction;
import common.Event;

public class Elevator {
	private ElevatorButton[] elevatorButtons;
	private ElevatorLamp[] evelatorLamps;
	private Motor motor;
	private Door door;
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
	 * Receives an event, does work, then sends an event back to the server
	 */
	public void doWork() {
		Event event = this.elevatorSocket.recieveEventIn();
		System.out.println("Elevator recieved elevator event in: " + event);
		//Do work on message
		this.elevatorSocket.sendEventOut(event);
		System.out.println("Elevator sent elevator event out: " + event);
	}
}
