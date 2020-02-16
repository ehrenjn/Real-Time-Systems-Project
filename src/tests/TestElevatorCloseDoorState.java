package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import elevator.*;
import event.*;
import floor.EventReader;
import common.*;

class TestElevatorCloseDoorState {
	
	

	
	
	ElevatorDoorEvent SchedulerCommmandsClose;
	
	CommunicationSocket sock = new CommunicationSocket();
	Elevator e = new Elevator(sock, 4);
	
	ElevatorState state = new ElevatorOpenDoorState(2);
	ElevatorCloseDoorState close = new ElevatorCloseDoorState(state);
	
	
	
	@Test
	void testElevatorCloseDoorState() {
		
		SchedulerCommmandsClose = new ElevatorDoorEvent(DoorState.CLOSE, 12, 34);
		sock.sendEventIn(SchedulerCommmandsClose);

		
		
		//fail("Not yet implemented");
		
	}

	@Test
	void testhandleElevatorDoorEvent() {
			assertEquals("Elevator Should Open", DoorState.OPENING, close.handleElevatorDoorEvent(SchedulerCommmandsClose));
		
	}

	@Test
	void testhandleElevatorTransitEvent() {


	}

	

}
