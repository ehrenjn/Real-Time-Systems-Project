package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import common.*;
import elevator.*;
import event.*;
import event.toElevator.*;
import event.toScheduler.FloorPressButtonEvent;



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
		
	}
	
	@Test
	public void TestHandleElevatorEvent() {
		
	}
	
	@Test
	public void TestHandleElevatorDirectionLampEvent() {
		
	}
	
	@Test
	public void TesthandleElevatorPressButtonEvent() {
		
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
	//	ElevatorButtonLampEvent elevatorButtonLampEvent = new ElevatorButtonLampEvent(2,1,3,LampState.ON);
	//	assertEquals("Elevator Button Lamp should change state", testElevator.state, );
	}
	
	@Test
	public void TestGetFloorIncrement() {
		
		assertEquals("Floor should increment upwards",1, Elevator.getFloorIncrement(common.Direction.UP));
		assertEquals("Floor should increment downwards",-1, Elevator.getFloorIncrement(common.Direction.DOWN));
		assertEquals("Floor should not increment",0,Elevator.getFloorIncrement(null));
		
	}

	
	/*
	 * Test ElevatorCloseDoorState
	 * 
	 */
	
	
	/*
	 * Test ElevatorClosingDoorState
	 * 
	 */
	public void () {
		
	}
	
	/*
	 * Test ElevatorFailureState
	 * 
	 */
	
	/*
	 * Test ElevatorMovingState
	 * 
	 */
	
	/*
	 * Test ElevatorOpenDoorState
	 * 
	 */
	
	/*
	 * Test ElevatorOpeningDoorState
	 * 
	 */
	
}



