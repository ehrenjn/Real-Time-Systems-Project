package elevator;

import java.util.ArrayList;

import common.CommunicationSocket;
import common.Event;
import floor.EventReader;

public class ElevatorSubsystem implements Runnable {
	private Elevator[] elevators;
	private static int NUM_ELEVATORS = 1;
	
	public ElevatorSubsystem(CommunicationSocket elevatorSocket) {
		elevators = new Elevator[] {new Elevator(elevatorSocket)};
	}
	
	
	public void run() {
		while(true) {
			for (Elevator elevator: elevators) {
				Event event = elevator.recieveEventIn();
				System.out.println("Elevator recieved elevator event in: " + event);
				elevator.sendEventOut(event);
				System.out.println("Elevator sent elevator event out: " + event);
			}
		}
	}
	
}
