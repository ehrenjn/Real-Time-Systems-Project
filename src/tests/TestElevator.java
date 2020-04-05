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



public class TestElevator {
	
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
	public void TestHandleElevatorButtonLampEvent() {	
		ElevatorButtonLampEvent elevatorBLE = new ElevatorButtonLampEvent(0,0,3,LampState.ON);
		
		assertTrue(testState.handleElevatorButtonLampEvent(elevatorBLE) instanceof ElevatorOpenDoorState);
	}

	@Test
	public void TestHandleElevatorDirectionLampEvent() {
		ElevatorDirectionLampEvent elevatorDLE = new ElevatorDirectionLampEvent(0, 0, common.Direction.UP, common.LampState.ON);
		
		assertTrue(testState.handleElevatorDirectionLampEvent(elevatorDLE) instanceof ElevatorOpenDoorState);
		
	}
	
	@Test
	public void TesthHandleElevatorPressButtonEvent() {
		ElevatorPressButtonEvent elevatorPBE = new ElevatorPressButtonEvent(0,0,10);

		assertTrue(testState.handleElevatorPressButtonEvent(elevatorPBE) instanceof ElevatorOpenDoorState);
	}
	
	@Test
	public void TestHandleElevatorCloseDoorEvent() {
		ElevatorCloseDoorEvent elevatorCDE = new ElevatorCloseDoorEvent(0,0);
		
		assertTrue(testState.handleElevatorCloseDoorEvent(elevatorCDE) instanceof ElevatorClosingDoorState);
	}
	
	@Test
	public void TestHandleElevatorOpenDoorEvent() {
		testState = new ElevatorCloseDoorState(testState);
		ElevatorOpenDoorEvent elevatorODE = new ElevatorOpenDoorEvent(0,0);
		
		assertTrue(testState.handleElevatorOpenDoorEvent(elevatorODE) instanceof ElevatorOpeningDoorState);	
	}
	
	@Test
	public void TestHandleElevatorStartMovingEvent() {
		testState = new ElevatorCloseDoorState(testState);
		ElevatorStartMovingEvent elevatorSME = new ElevatorStartMovingEvent(0, 0, common.Direction.UP);
		
		
		assertTrue(testState.handleElevatorStartMovingEvent(elevatorSME) instanceof ElevatorMovingState);
	}
	
	@Test
	public void TestHandleElevatorKeepMovingEvent() {
		testState = new ElevatorMovingState(testState, Direction.UP);
		ElevatorKeepMovingEvent elevatorKME = new ElevatorKeepMovingEvent(0, 0, common.Direction.UP);
		
		assertTrue(testState.handleElevatorKeepMovingEvent(elevatorKME) instanceof ElevatorMovingState);
	}
	
	@Test
	public void TestHandleElevatorStopMovingEvent() {
		testState = new ElevatorMovingState(testState, Direction.UP);
		ElevatorStopMovingEvent elevatorStop = new ElevatorStopMovingEvent(0,0);
	
		assertTrue(testState.handleElevatorStopMovingEvent(elevatorStop) instanceof ElevatorCloseDoorState);		
	}
	
	
	@Test
	public void TestGetFloorIncrement() {
		assertEquals("Floor should increment upwards",1, Elevator.getFloorIncrement(common.Direction.UP));
		assertEquals("Floor should increment downwards",-1, Elevator.getFloorIncrement(common.Direction.DOWN));
	}
}



