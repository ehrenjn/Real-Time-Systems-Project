package floor;

import java.util.ArrayList;

import common.CommunicationSocket;
import common.Event;
import floor.Floor;


public class FloorSubsystem implements Runnable{
	private Floor[] floors;

	public FloorSubsystem(CommunicationSocket floorSocket, int numFloors) { 
		this.floors = new Floor[numFloors];
		
		for (int i = 0; i < numFloors; i++)
			{
				this.floors[i] = new Floor(floorSocket, i );
			}
	}

	

	public void run() {

		ArrayList<Event> events = EventReader.fromEventFile("floorEvents.tsv");
		
		for (Event event: events) {
			this.floors[event.getCurrentFloor()].sendEventOut(event);
			System.out.println("Floor sent floor event out: " + event);
			event = this.floors[event.getCurrentFloor()].recieveEventIn();
			System.out.println("Floor recieved floor event In: " + event);
		}
	}
}
