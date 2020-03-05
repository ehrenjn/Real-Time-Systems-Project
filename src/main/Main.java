package main;
import java.io.File;

import java.util.ArrayList;

import common.CommunicationSocket;
import common.Constants;
import elevator.ElevatorSubsystem;
import event.Event;
import floor.EventReader;
import floor.FloorSubsystem;
import scheduler.SchedulerSubsystem;


public class Main {
	public static void main(String[] args) {
		CommunicationSocket elevatorSocket = new CommunicationSocket();
		CommunicationSocket floorSocket = new CommunicationSocket();
		int numberOfFloors = 22;
		
		ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem(elevatorSocket, Constants.NUM_ELEVATORS, Constants.NUM_FLOORS);
		FloorSubsystem floorSubsystem = new FloorSubsystem(floorSocket, Constants.NUM_FLOORS);
		SchedulerSubsystem schedulerSubsystem = new SchedulerSubsystem(elevatorSocket, floorSocket);
		
		Thread elevatorThread = new Thread(elevatorSubsystem);
		Thread floorThread = new Thread(floorSubsystem);
		Thread schedulerThread = new Thread(schedulerSubsystem);
		
		
		elevatorThread.start();
		floorThread.start();
		schedulerThread.start();
	}
}
