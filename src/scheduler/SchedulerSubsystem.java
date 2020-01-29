package scheduler;

import common.Event;

import common.CommunicationSocket;

public class SchedulerSubsystem implements Runnable{
	private Scheduler scheduler;
	
	public SchedulerSubsystem(CommunicationSocket elevatorSocket, CommunicationSocket floorSocket) {
		this.scheduler = new Scheduler(elevatorSocket, floorSocket);
	}
	
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
