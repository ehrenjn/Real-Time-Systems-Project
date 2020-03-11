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
	
	CommunicationSocket elevatorSock = new CommunicationSocket();
	ElevatorState testState;
	int numOfFloors = 22;
	
	/*
	 * Test Elevator class
	 */
	@Before
	public void setUp() {
		testState = new ElevatorOpenDoorState(numOfFloors);		
	}

	
	@Test
	public void TesthandleElevatorPressButtonEvent() {
		
		ElevatorPressButtonEvent elevatorPBE = new ElevatorPressButtonEvent(0,0,10);
		testState.handleElevatorPressButtonEvent(elevatorPBE);
		
		ElevatorPressedButtonEvent elevatorPressed = new ElevatorPressedButtonEvent(0,0,10);

		assertEquals("Elevator button is pressed", elevatorPressed, elevatorSock.recieveEventOut());
	}
	
	@Test
	public void TestHandleElevatorDirectionLampEvent() {
		ElevatorDirectionLampEvent elevatorDLE = new ElevatorDirectionLampEvent(0, 0, common.Direction.UP, common.LampState.ON);
		testState.handleElevatorDirectionLampEvent(elevatorDLE);
		
		assertEquals("Direction lamp changed", elevatorDLE, testState.handleElevatorDirectionLampEvent(elevatorDLE));
		
	}
	
	
	@Test
	public void TestHandleElevatorCloseDoorEvent() {
		
		ElevatorCloseDoorEvent elevatorCDE = new ElevatorCloseDoorEvent(0,0);
		testState.handleElevatorCloseDoorEvent(elevatorCDE);
		
		ElevatorClosedDoorEvent elevatorClosed = new ElevatorClosedDoorEvent(0, 0);
		
		assertEquals("Elevator door is closed", elevatorClosed, elevatorSock.recieveEventOut());
		
	}
	
	@Test
	public void TestHandleElevatorOpenDoorEvent() {
		
		ElevatorOpenDoorEvent elevatorODE = new ElevatorOpenDoorEvent(0,0);
		testState.handleElevatorOpenDoorEvent(elevatorODE);
		
		ElevatorOpenedDoorEvent elevatorOpened = new ElevatorOpenedDoorEvent(0, 0);
		
		assertEquals("Elevator door is closed", elevatorOpened, elevatorSock.recieveEventOut());
			
	}
	
	@Test
	public void TestHandleElevatorStartMovingEvent() {
		
		ElevatorStartMovingEvent elevatorSME = new ElevatorStartMovingEvent(0, 0, common.Direction.UP);
		testState.handleElevatorStartMovingEvent(elevatorSME);
		
		
		int nextFloor = testState.getCurrentFloor() +1;
		ElevatorArrivalSensorEvent elevatorMove = new ElevatorArrivalSensorEvent(0, 0, nextFloor);
		
		assertEquals("Elevator is moving", elevatorMove, elevatorSock.recieveEventOut());
		
	}
	
	@Test
	public void TestHandleElevatorKeepMovingEvent() {
		
		ElevatorKeepMovingEvent elevatorKME = new ElevatorKeepMovingEvent(0, 0, common.Direction.UP);
		testState.handleElevatorKeepMovingEvent(elevatorKME);
		
		
		int currFloor = testState.getCurrentFloor();
		ElevatorArrivalSensorEvent elevatorMoving = new ElevatorArrivalSensorEvent(0, 0, currFloor);
		
		assertEquals("Elevator is still moving", elevatorMoving, elevatorSock.recieveEventOut());
		
	}
	
	@Test
	public void TestHandleElevatorStopMovingEvent() {
		
		ElevatorStopMovingEvent elevatorStop = new ElevatorStopMovingEvent(0,0);
		testState.handleElevatorStopMovingEvent(elevatorStop);
		
		ElevatorStoppedEvent elevatorStopped = new ElevatorStoppedEvent(0,0);
		
		assertEquals("Elevator has stopped", elevatorStopped, elevatorSock.recieveEventOut());
		
	}
	
	@Test
	public void TestHandleElevatorButtonLampEvent() {
		ElevatorButtonLampEvent elevatorBLE = new ElevatorButtonLampEvent(0,0,3,LampState.ON);
		testState.handleElevatorButtonLampEvent(elevatorBLE);
		
		assertEquals("Event should match", elevatorBLE,  testState.handleElevatorButtonLampEvent(elevatorBLE));
	}
	
	@Test
	public void TestGetFloorIncrement() {
		assertEquals("Floor should increment upwards",1, Elevator.getFloorIncrement(common.Direction.UP));
		assertEquals("Floor should increment downwards",-1, Elevator.getFloorIncrement(common.Direction.DOWN));
	}

	
	
}



