package floor;

import java.util.ArrayList;

import common.CommunicationSocket;
import common.Event;

public class FloorSubsystem implements Runnable{
	private Floor[] floors;
	private CommunicationSocket floorSocket;
	
	public FloorSubsystem(CommunicationSocket floorSocket) {
		this.floorSocket = floorSocket;
	}
	

	public void run() {

		ArrayList<Event> events = EventReader.fromEventFile("floorEvents.tsv");
		
		for (Event event: events) {
			this.floorSocket.sendEventOut(event);
			System.out.println("Floor sent floor event out: " + event);
			event = this.floorSocket.recieveEventIn();
			System.out.println("Floor recieved floor event In: " + event);
		}
	}
}
