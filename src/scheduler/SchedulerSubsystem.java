package scheduler;

import common.Event;

import common.CommunicationSocket;

public class SchedulerSubsystem implements Runnable{
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
			Event event = this.scheduler.recieveFloorEventOut();
			System.out.println("Scheduler recieved floor event in: " + event);
			this.scheduler.sendElevatorEventIn(event);
			System.out.println("Scheduler sent elevator event out: " + event);
			event = this.scheduler.recieveElevatorEventOut();
			System.out.println("Scheduler recieved elevator event in:" + event);
			this.scheduler.sendFloorEventIn(event);
			System.out.println("Scheduler sent elevator event out: " + event);
		}
	}
}
