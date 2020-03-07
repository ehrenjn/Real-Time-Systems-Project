package tests;

import static org.junit.Assert.*;

import java.net.*;
import java.util.Arrays;

import org.junit.Test;

import common.*;
import event.*;
import event.toElevator.*;
import network.*;


public class TestEventSocket{

	@Test
	public void TestEventSocket() throws UnknownHostException {

		Event testSendEvent = new Event("test",4,2);
		Event testRecEvent = null;
		EventSocket testSendSocket = new EventSocket();
		EventSocket testRecieveSocket = new EventSocket(3000);
		InetAddress ip = InetAddress.getByName("127.0.0.1");
		
		testSendSocket.sendEvent(testSendEvent, ip, 3000);
		byte[] serializedSendEvent = EventSerializer.serialize(testSendEvent);
		
		testRecEvent = testRecieveSocket.receiveEvent();
		byte[] serializedRecEvent = EventSerializer.serialize(testRecEvent);

		testSendSocket.close();
		testRecieveSocket.close();
		
		assertTrue("Event is not the same.", Arrays.equals(serializedSendEvent, serializedSendEvent));	
	}
	
	@Test
	public void TestEBLE() throws Exception {
		ElevatorButtonLampEvent testSendEvent = new ElevatorButtonLampEvent(3, 4, 3, common.LampState.ON);
		Event testRecEvent = null;
		EventSocket testSendSocket = new EventSocket();
		EventSocket testRecieveSocket = new EventSocket(7600);
		InetAddress ip = InetAddress.getByName("127.0.0.1");
		
		testSendSocket.sendEvent(testSendEvent, ip, 7600);
		byte[] serializedSendEvent = EventSerializer.serialize(testSendEvent);
		
		testRecEvent = testRecieveSocket.receiveEvent();
		byte[] serializedRecEvent = EventSerializer.serialize(testRecEvent);

		testSendSocket.close();
		testRecieveSocket.close();
		
		assertTrue("Event is not the same, ElevatorButtonLampEvent.", Arrays.equals(serializedSendEvent, serializedSendEvent));	
	}
	
	@Test
	public void TestCDE() throws Exception {
		ElevatorCloseDoorEvent testSendEvent = new ElevatorCloseDoorEvent(3, 4);
		Event testRecEvent = null;
		EventSocket testSendSocket = new EventSocket();
		EventSocket testRecieveSocket = new EventSocket(8900);
		InetAddress ip = InetAddress.getByName("127.0.0.1");
		
		testSendSocket.sendEvent(testSendEvent, ip, 8900);
		byte[] serializedSendEvent = EventSerializer.serialize(testSendEvent);
		
		testRecEvent = testRecieveSocket.receiveEvent();
		byte[] serializedRecEvent = EventSerializer.serialize(testRecEvent);

		testSendSocket.close();
		testRecieveSocket.close();
		
		assertTrue("Event is not the same, ElevatorCloseDoorEvent.", Arrays.equals(serializedSendEvent, serializedSendEvent));	
	}

	@Test
	public void TestDLE() throws Exception {
		ElevatorDirectionLampEvent testSendEvent = new ElevatorDirectionLampEvent(3, 4, common.Direction.UP, common.LampState.ON);
		Event testRecEvent = null;
		EventSocket testSendSocket = new EventSocket();
		EventSocket testRecieveSocket = new EventSocket(7000);
		InetAddress ip = InetAddress.getByName("127.0.0.1");
		
		testSendSocket.sendEvent(testSendEvent, ip, 7000);
		byte[] serializedSendEvent = EventSerializer.serialize(testSendEvent);
		
		testRecEvent = testRecieveSocket.receiveEvent();
		byte[] serializedRecEvent = EventSerializer.serialize(testRecEvent);

		testSendSocket.close();
		testRecieveSocket.close();
		
		assertTrue("Event is not the same, ElevatorDirectionLampEvent.", Arrays.equals(serializedSendEvent, serializedSendEvent));	
	}
	

	@Test
	public void TestKME() throws Exception {
		ElevatorKeepMovingEvent testSendEvent = new ElevatorKeepMovingEvent(4,2, common.Direction.UP);
		Event testRecEvent = null;
		EventSocket testSendSocket = new EventSocket();
		EventSocket testRecieveSocket = new EventSocket(6000);
		InetAddress ip = InetAddress.getByName("127.0.0.1");
		
		testSendSocket.sendEvent(testSendEvent, ip, 6000);
		byte[] serializedSendEvent = EventSerializer.serialize(testSendEvent);
		
		testRecEvent = testRecieveSocket.receiveEvent();
		byte[] serializedRecEvent = EventSerializer.serialize(testRecEvent);

		testSendSocket.close();
		testRecieveSocket.close();
		
		assertTrue("Event is not the same, ElevatorKeepMovingEvent.", Arrays.equals(serializedSendEvent, serializedSendEvent));	
	}
	

	@Test
	public void TestODE() throws Exception {
		ElevatorOpenDoorEvent testSendEvent = new ElevatorOpenDoorEvent(3, 1);
		Event testRecEvent = null;
		EventSocket testSendSocket = new EventSocket();
		EventSocket testRecieveSocket = new EventSocket(5000);
		InetAddress ip = InetAddress.getByName("127.0.0.1");
		
		testSendSocket.sendEvent(testSendEvent, ip, 5000);
		byte[] serializedSendEvent = EventSerializer.serialize(testSendEvent);
		
		testRecEvent = testRecieveSocket.receiveEvent();
		byte[] serializedRecEvent = EventSerializer.serialize(testRecEvent);

		testSendSocket.close();
		testRecieveSocket.close();
		
		assertTrue("Event is not the same, ElevatorOpenDoorEvent.", Arrays.equals(serializedSendEvent, serializedSendEvent));	
	}
	

	@Test
	public void TestPBE() throws Exception {
		ElevatorPressButtonEvent testSendEvent = new ElevatorPressButtonEvent(3, 5, 7);
		Event testRecEvent = null;
		EventSocket testSendSocket = new EventSocket();
		EventSocket testRecieveSocket = new EventSocket(1000);
		InetAddress ip = InetAddress.getByName("127.0.0.1");
		
		testSendSocket.sendEvent(testSendEvent, ip, 1000);
		byte[] serializedSendEvent = EventSerializer.serialize(testSendEvent);
		
		testRecEvent = testRecieveSocket.receiveEvent();
		byte[] serializedRecEvent = EventSerializer.serialize(testRecEvent);

		testSendSocket.close();
		testRecieveSocket.close();
		
		assertTrue("Event is not the same, ElevatorPressButtonEvent.", Arrays.equals(serializedSendEvent, serializedSendEvent));	
	}
	

	@Test
	public void TestSrME() throws Exception {
		ElevatorStartMovingEvent testSendEvent = new ElevatorStartMovingEvent(5, 0, common.Direction.DOWN);
		Event testRecEvent = null;
		EventSocket testSendSocket = new EventSocket();
		EventSocket testRecieveSocket = new EventSocket(2000);
		InetAddress ip = InetAddress.getByName("127.0.0.1");
		
		testSendSocket.sendEvent(testSendEvent, ip, 2000);
		byte[] serializedSendEvent = EventSerializer.serialize(testSendEvent);
		
		testRecEvent = testRecieveSocket.receiveEvent();
		byte[] serializedRecEvent = EventSerializer.serialize(testRecEvent);

		testSendSocket.close();
		testRecieveSocket.close();
		
		assertTrue("Event is not the same, ElevatorStartMovingEvent.", Arrays.equals(serializedSendEvent, serializedSendEvent));	
	}
	

	@Test
	public void TestSoME() throws Exception {
		ElevatorStopMovingEvent testSendEvent = new ElevatorStopMovingEvent(1, 4);
		Event testRecEvent = null;
		EventSocket testSendSocket = new EventSocket();
		EventSocket testRecieveSocket = new EventSocket(4000);
		InetAddress ip = InetAddress.getByName("127.0.0.1");
		
		testSendSocket.sendEvent(testSendEvent, ip, 4000);
		byte[] serializedSendEvent = EventSerializer.serialize(testSendEvent);
		
		testRecEvent = testRecieveSocket.receiveEvent();
		byte[] serializedRecEvent = EventSerializer.serialize(testRecEvent);
		
		testSendSocket.close();
		testRecieveSocket.close();
		
		assertTrue("Event is not the same, ElevatorStopMovingEvent.", Arrays.equals(serializedSendEvent, serializedSendEvent));	
	}
	
}
