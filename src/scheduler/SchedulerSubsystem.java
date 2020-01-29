package scheduler;

import common.Event;

import common.CommunicationSocket;

public class SchedulerSubsystem implements Runnable{
	private CommunicationSocket elevatorSocket;
	private CommunicationSocket floorSocket;
	
	public SchedulerSubsystem(CommunicationSocket elevatorSocket, CommunicationSocket floorSocket) {
		this.elevatorSocket = elevatorSocket;
		this.floorSocket = floorSocket;
	}
	
	public void run() {
		while(true) {
			Event event = this.floorSocket.recieveEventOut();
			System.out.println("Scheduler recieved floor event in: " + event);
			this.elevatorSocket.sendEventIn(event);
			System.out.println("Scheduler sent elevator event out: " + event);
			event = this.elevatorSocket.recieveEventOut();
			System.out.println("Scheduler recieved elevator event in:" + event);
			this.floorSocket.sendEventIn(event);
			System.out.println("Scheduler sent elevator event out: " + event);
		}
	}
}
