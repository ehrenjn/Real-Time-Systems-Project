package elevator;

import java.util.ArrayList;

import common.CommunicationSocket;
import common.Event;
import floor.EventReader;

public class ElevatorSubsystem implements Runnable{
	private Elevator[] elevators;
	private CommunicationSocket elevatorSocket;
	
	public ElevatorSubsystem(CommunicationSocket elevatorSocket) {
		this.elevatorSocket = elevatorSocket;
	}
	
	
	public void run() {
		while(true) {
			Event event = this.elevatorSocket.recieveEventIn();
			System.out.println("Elevator recieved elevator event in: " + event);
			//Do work on message
			this.elevatorSocket.sendEventOut(event);
			System.out.println("Elevator sent elevator event out: " + event);
		}
	}
	
}
