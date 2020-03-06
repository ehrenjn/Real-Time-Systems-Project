package scheduler;

import common.CommunicationSocket;

import event.Event;
import event.toScheduler.*;

public class SchedulerSubsystem implements Runnable {
	private Scheduler scheduler;
	
	/**
	 * Constructor of the SchedulerSubsystem
	 * @param elevatorSocket the socket for the elevator to receive and send events on
	 * @param floorSocket the socket for the floor to receive and send events on
	 */
	
	public SchedulerSubsystem(CommunicationSocket elevatorSocket, CommunicationSocket floorSocket) {
		this.scheduler = new Scheduler(elevatorSocket, floorSocket);
	}
	
	/**
	 * The method to run as in a Thread. Runs forever as it waits for incoming event
	 */
	public void run() {
		while(true) {
			Event event = getNextEvent();
			System.out.println("Scheduler recieved: " + event);
			scheduler.handleEvent(event);
		}
	}
}
