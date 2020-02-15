package elevator;

import java.util.ArrayList;

import common.CommunicationSocket;
import event.Event;
import floor.EventReader;

public class ElevatorSubsystem implements Runnable {
	private Elevator[] elevators;
	

	/**
	 * Constructor of the ElevatorSubsystem
	 * @param elevatorSocket the socket for the elevator to receive and send events on
	 * @param numberOfFloors the number of floors the elevator must service
	 */	
	
	public ElevatorSubsystem(CommunicationSocket elevatorSocket, int numberOfFloors) {
		elevators = new Elevator[] {new Elevator(elevatorSocket, numberOfFloors)};
	}
	
	/**
	 * The method to run as in a Thread. Runs forever as it waits for incoming event
	 */
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
