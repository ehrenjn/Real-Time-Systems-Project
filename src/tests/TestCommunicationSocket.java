package tests;

import java.util.Date;

import common.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class TestCommunicationSocket {
	
	@Test
	public void testCommunicationSocket() {
		CommunicationSocket sock = new CommunicationSocket();
		Event event = new Event(new Date(), 5, Direction.UP, 7);
		sock.sendEventIn(event);
		Event receivedEvent = sock.recieveEventIn();
		assertEquals("received event should be the same as sent event", event, receivedEvent);
		sock.sendEventOut(receivedEvent);
		receivedEvent = sock.recieveEventOut();
		assertEquals("received event should be the same as sent event", event, receivedEvent);
	}
}
