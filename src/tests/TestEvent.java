package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;

import main.Event;


public class TestEvent {

	@Test
	public void TestFromCSV() {
		String csv = "10:10:05.666,0,up,10\n" +
		             "0:0:0.555,20,DoWN,4";
		ByteArrayInputStream stream = new ByteArrayInputStream(csv.getBytes());
		ArrayList<Event> events = Event.fromCSV(stream);
		assertEquals("initial floor should be parsed correctly", 0, events.get(0).getCurrentFloor());
		assertEquals("direction should be parsed correctly", Event.Direction.DOWN, events.get(1).getDirection());
		assertEquals("destination floor should be parsed correctly", 4, events.get(1).getDesiredFloor());
	}

}
