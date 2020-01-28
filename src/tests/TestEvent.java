package tests;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import common.Direction;
import common.Event;
import floor.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;


public class TestEvent {
	
	public String csv;

	@Before
	public void setUp() {
		this.csv = "10:10:05.666 0 up 10\n0:0:0.555 20 DoWN 4";
	}

	@Test
	public void TestFromCSV() {
		ByteArrayInputStream stream = new ByteArrayInputStream(csv.getBytes());
		ArrayList<Event> events = EventReader.fromEventFile(stream);
		
		assertEquals("initial floor should be parsed correctly", 0, events.get(0).getCurrentFloor());
		assertEquals("direction should be parsed correctly", Direction.DOWN, events.get(1).getDirection());
		assertEquals("destination floor should be parsed correctly", 4, events.get(1).getDesiredFloor());
	}

}
