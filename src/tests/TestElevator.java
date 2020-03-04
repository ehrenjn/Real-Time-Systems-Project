package tests;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import common.*;
import elevator.*;
import event.*;
import event.toElevator.*;
import event.toScheduler.*;



public class TestElevator {
	
	CommunicationSocket elevatorSock = new CommunicationSocket();
	int numOfFloors = 22;
	
	/*
	 * Test ElevatorState class
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
		
		ElevatorPressedButtonEvent elevatorPressed = new ElevatorPressedButtonEvent(10,0,0);

		assertEquals("Elevator button is pressed", elevatorPressed, elevatorSock.recieveEventOut());
	}
	
	@Test
	public void TestHandleElevatorDirectionLampEvent() {
		
	}
	
	
	@Test
	public void TestHandleElevatorCloseDoorEvent() {
		
	}
	
	@Test
	public void TestHandleElevatorOpenDoorEvent() {
		
	}
	
	@Test
	public void TestHandleElevatorStartMovingEvent() {
		
	}
	
	@Test
	public void TestHandleElevatorKeepMovingEvent() {
		
	}
	
	@Test
	public void TestHandleElevatorStopMovingEvent() {
		
	}
	
	@Test
	public void TestHandleElevatorButtonLampEvent() {
		ElevatorButtonLampEvent elevatorButtonLampEvent = new ElevatorButtonLampEvent(2,1,3,LampState.ON);
		testElevator.handleElevatorButtonLampEvent(elevatorButtonLampEvent);
		
		ElevatorButton
		
		
		assertEquals("Event should match", elevatorButtonLampEvent,  );
	}
	
	@Test
	public void TestGetFloorIncrement() {
		
		assertEquals("Floor should increment upwards",1, Elevator.getFloorIncrement(common.Direction.UP));
		assertEquals("Floor should increment downwards",-1, Elevator.getFloorIncrement(common.Direction.DOWN));
		assertEquals("Floor should not increment",0,Elevator.getFloorIncrement(null));
		
	}

	
	
}



