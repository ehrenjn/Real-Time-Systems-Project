package floor;

import java.util.ArrayList;


import common.CommunicationSocket;
import common.LampState;
import event.*;
import event.toScheduler.*;
import floor.Floor;

public class FloorSubsystem implements Runnable{
	
	private Floor[] floors;
	private CommunicationSocket floorSocket;
	
	/**
	 * Constructor of the FloorSubsystem
	 * @param floorSocket the socket for the floor to receive and send events on
	 * @param numFloors the number of floors of the system
	 */
	public FloorSubsystem(CommunicationSocket floorSocket, int numFloors) { 
		this.floors = new Floor[numFloors];
		this.floorSocket = floorSocket;
		
		for (int i = 0; i < numFloors; i++) {
			this.floors[i] = new Floor(floorSocket, i);
		}
	}
	
	/**
	 * The method to run as in a Thread
	 */
	public void run() {
		//The relative filepath to read events from (starting from the project root)
		String fileLocation = "floorEvents.tsv";
		
		ArrayList<FloorPressButtonEvent> events = EventReader.fromEventFile(fileLocation);
		
		for (FloorPressButtonEvent event: events) {
			Floor floor = this.floors[event.getSender()];
			floor.changeButtonLampState(event.getDirection(), LampState.ON);
			floor.sendEventOut(event);
			System.out.println("Floor sent floor event out: " + event);
			
			FloorLampEvent lampEvent = (FloorLampEvent) floorSocket.recieveEventIn();
			System.out.println("Floor receieved event: " + lampEvent);
			this.floors[lampEvent.getRecipient()].changeButtonLampState(
					lampEvent.getDirection(), lampEvent.getLampState());
		}
	}
}
