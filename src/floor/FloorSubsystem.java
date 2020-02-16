package floor;

import java.util.ArrayList;


import common.CommunicationSocket;
import event.Event;
import event.toScheduler.*;
import floor.Floor;

public class FloorSubsystem implements Runnable{
	
	private Floor[] floors;
	
	/**
	 * Constructor of the FloorSubsystem
	 * @param floorSocket the socket for the floor to receive and send events on
	 * @param numFloors the number of floors of the system
	 */
	public FloorSubsystem(CommunicationSocket floorSocket, int numFloors) { 
		this.floors = new Floor[numFloors];
		
		for (int i = 0; i < numFloors; i++){
			this.floors[i] = new Floor(floorSocket, i );
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
			this.floors[event.getSender()].sendEventOut(event);
			System.out.println("Floor sent floor event out: " + event);
		}
	}
}
