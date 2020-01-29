package main;
import java.io.File;

import java.util.ArrayList;

import common.CommunicationSocket;
import common.Event;
import elevator.ElevatorSubsystem;
import floor.EventReader;
import floor.FloorSubsystem;
import scheduler.SchedulerSubsystem;


public class Main {
	public static void main(String[] args) {
		CommunicationSocket elevatorSocket = new CommunicationSocket();
		CommunicationSocket floorSocket = new CommunicationSocket();
		
		ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem(elevatorSocket);
		FloorSubsystem floorSubsystem = new FloorSubsystem(floorSocket);
		SchedulerSubsystem schedulerSubsystem = new SchedulerSubsystem(elevatorSocket, floorSocket);
		
		Thread elevatorThread = new Thread(elevatorSubsystem);
		Thread floorThread = new Thread(floorSubsystem);
		Thread schedulerThread = new Thread(schedulerSubsystem);
		
		
		elevatorThread.start();
		floorThread.start();
		schedulerThread.start();
	}
}
