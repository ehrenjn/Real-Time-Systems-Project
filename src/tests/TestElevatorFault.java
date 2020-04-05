package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import common.*;
import elevator.*;
import event.*;
import event.toElevator.*;
import event.toScheduler.*;
import network.*;



public class TestElevatorFault {
	
	CommunicationSocket elevatorSock;
	ElevatorState testState;
	int numOfFloors = 22;
	
	/*
	 * Test Elevator class
	 */
	@Before
	public void setUp() {
		testState = new ElevatorOpenDoorState(numOfFloors);
		elevatorSock = new CommunicationSocket();
	}
	
	@Test
	public void TestHandleElevatorCloseDoorFault() {
		ElevatorCloseDoorEvent elevatorCDE = new ElevatorCloseDoorEvent(0,0);
		testState = new ElevatorCloseDoorState(testState);
		
		assertTrue(testState.handleElevatorCloseDoorEvent(elevatorCDE) instanceof ElevatorFailureState);
	}
	
	@Test
	public void TestHandleElevatorOpenDoorFault() {
		ElevatorOpenDoorEvent elevatorODE = new ElevatorOpenDoorEvent(0,0);
		testState = new ElevatorOpenDoorState(numOfFloors); 
		
		assertTrue(testState.handleElevatorOpenDoorEvent(elevatorODE) instanceof ElevatorFailureState);	
	}
	
	@Test
	public void TestHandleElevatorStartMovingFault() {
		ElevatorStartMovingEvent elevatorSME = new ElevatorStartMovingEvent(0, 0, common.Direction.UP);
		testState = new ElevatorOpenDoorState(numOfFloors); 
		
		
		assertTrue(testState.handleElevatorStartMovingEvent(elevatorSME) instanceof ElevatorFailureState);
	}
	
	@Test
	public void TestHandleElevatorKeepMovingFault() {
		ElevatorKeepMovingEvent elevatorKME = new ElevatorKeepMovingEvent(0, 0, common.Direction.UP);
		testState = new ElevatorOpenDoorState(numOfFloors); 

		assertTrue(testState.handleElevatorKeepMovingEvent(elevatorKME) instanceof ElevatorFailureState);		
	}
	
	@Test
	public void TestHandleElevatorStopMovingFault() {
		ElevatorStopMovingEvent elevatorStop = new ElevatorStopMovingEvent(0,0);
		testState = new ElevatorOpenDoorState(numOfFloors); 
	
		assertTrue(testState.handleElevatorStopMovingEvent(elevatorStop) instanceof ElevatorFailureState);		
	}
}



