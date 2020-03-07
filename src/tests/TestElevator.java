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
	int numOfFloors = 22;
	
	/*
	 * Test Elevator class
	 */
	//@Before
	//public void setUp() {
		Elevator testElevator = new Elevator(elevatorSock,numOfFloors);		
	//}
	
	@Test
	public void TestRecieveEventIn() {
		Event testEvent = new Event("test",2,1);
		elevatorSock.sendEventIn(testEvent);
		
		assertEquals("Elevator should have received event", testEvent, testElevator.recieveEventIn());
	}
	
	@Test
	public void TestSendEventOut() {
		
		Event testEvent = new Event("test",2,1);
		elevatorSock.sendEventOut(testEvent);
		
		assertEquals("Elevator should have sent event", testEvent, elevatorSock.recieveEventOut());
	}

	
	@Test
	public void TesthandleElevatorPressButtonEvent() {
		
		ElevatorPressButtonEvent elevatorPBE = new ElevatorPressButtonEvent(0,0,10);
		testElevator.handleElevatorPressButtonEvent(elevatorPBE);
		
		ElevatorPressedButtonEvent elevatorPressed = new ElevatorPressedButtonEvent(0,0,10);

		assertEquals("Elevator button is pressed", elevatorPressed, elevatorSock.recieveEventOut());
	}
	
	@Test
	public void TestHandleElevatorDirectionLampEvent() {
		ElevatorDirectionLampEvent elevatorDLE = new ElevatorDirectionLampEvent(0, 0, common.Direction.UP, common.LampState.ON);
		testElevator.handleElevatorDirectionLampEvent(elevatorDLE);
		
		assertEquals("Direction lamp changed", elevatorDLE, testElevator.getState().handleElevatorDirectionLampEvent(elevatorDLE));
		
	}
	
	
	@Test
	public void TestHandleElevatorCloseDoorEvent() {
		
		ElevatorCloseDoorEvent elevatorCDE = new ElevatorCloseDoorEvent(0,0);
		testElevator.handleElevatorCloseDoorEvent(elevatorCDE);
		
		ElevatorClosedDoorEvent elevatorClosed = new ElevatorClosedDoorEvent(0, 0);
		
		assertEquals("Elevator door is closed", elevatorClosed, elevatorSock.recieveEventOut());
		
	}
	
	@Test
	public void TestHandleElevatorOpenDoorEvent() {
		
		ElevatorOpenDoorEvent elevatorODE = new ElevatorOpenDoorEvent(0,0);
		testElevator.handleElevatorOpenDoorEvent(elevatorODE);
		
		ElevatorOpenedDoorEvent elevatorOpened = new ElevatorOpenedDoorEvent(0, 0);
		
		assertEquals("Elevator door is closed", elevatorOpened, elevatorSock.recieveEventOut());
			
	}
	
	@Test
	public void TestHandleElevatorStartMovingEvent() {
		
		ElevatorStartMovingEvent elevatorSME = new ElevatorStartMovingEvent(0, 0, common.Direction.UP);
		testElevator.handleElevatorStartMovingEvent(elevatorSME);
		
		ElevatorState testState = testElevator.getState();
		int nextFloor = testState.getCurrentFloor() +1;
		ElevatorArrivalSensorEvent elevatorMove = new ElevatorArrivalSensorEvent(0, 0, nextFloor);
		
		assertEquals("Elevator is moving", elevatorMove, elevatorSock.recieveEventOut());
		
	}
	
	@Test
	public void TestHandleElevatorKeepMovingEvent() {
		
		ElevatorKeepMovingEvent elevatorKME = new ElevatorKeepMovingEvent(0, 0, common.Direction.UP);
		testElevator.handleElevatorKeepMovingEvent(elevatorKME);
		
		ElevatorState testState = testElevator.getState();
		int currFloor = testState.getCurrentFloor();
		ElevatorArrivalSensorEvent elevatorMoving = new ElevatorArrivalSensorEvent(0, 0, currFloor);
		
		assertEquals("Elevator is still moving", elevatorMoving, elevatorSock.recieveEventOut());
		
	}
	
	@Test
	public void TestHandleElevatorStopMovingEvent() {
		
		ElevatorStopMovingEvent elevatorStop = new ElevatorStopMovingEvent(0,0);
		testElevator.handleElevatorStopMovingEvent(elevatorStop);
		
		ElevatorStoppedEvent elevatorStopped = new ElevatorStoppedEvent(0,0);
		
		assertEquals("Elevator has stopped", elevatorStopped, elevatorSock.recieveEventOut());
		
	}
	
	@Test
	public void TestHandleElevatorButtonLampEvent() {
		ElevatorButtonLampEvent elevatorBLE = new ElevatorButtonLampEvent(0,0,3,LampState.ON);
		testElevator.handleElevatorButtonLampEvent(elevatorBLE);
		
		assertEquals("Event should match", elevatorBLE,  testElevator.getState().handleElevatorButtonLampEvent(elevatorBLE));
	}
	
	@Test
	public void TestGetFloorIncrement() {
		
		assertEquals("Floor should increment upwards",1, Elevator.getFloorIncrement(common.Direction.UP));
		assertEquals("Floor should increment downwards",-1, Elevator.getFloorIncrement(common.Direction.DOWN));
		//assertEquals("Floor should not increment",0,Elevator.getFloorIncrement(null));
		
	}

	
	
}



