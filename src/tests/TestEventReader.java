package tests;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import common.Direction;
import event.Event;
import floor.*;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class TestEventReader {
	
	private String csv;
	private int lineNumber;
	private SimpleDateFormat timeParser;

	@Before
	public void setUp() {
		this.csv = "10:10:05.666 0 up 10\n0:0:0.555 20 DoWN 4";
		this.lineNumber = -1;
		this.timeParser = new SimpleDateFormat("HH:mm:ss.SSS");
	}

	@Test
	public void TestFromEventFile() {
		ByteArrayInputStream stream = new ByteArrayInputStream(csv.getBytes());
		ArrayList<Event> events = EventReader.fromEventFile(stream);
		
		assertEquals("initial floor should be parsed correctly", 0, events.get(0).getCurrentFloor());
		assertEquals("direction should be parsed correctly", Direction.DOWN, events.get(1).getDoorState());
		assertEquals("destination floor should be parsed correctly", 4, events.get(1).getDesiredFloor());
		
		try {
			assertEquals("floor should be parsed correctly", this.timeParser.parse("10:10:05.666"), events.get(0).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			assert(false);
		}
	}
	
	@Test
	public void TestEventParseTime() {
		String exampleTime = "01:02:03.456";
		
		try {
			assertEquals("floor should be parsed correctly", this.timeParser.parse(exampleTime), EventReader.EventParseTime(exampleTime, lineNumber));
		} catch (ParseException e) {
			e.printStackTrace();
			assert(false);
		}
	}
	
	@Test
	public void TestEventParseInt() {
		assertEquals("direction should be parsed correctly", 1, EventReader.EventParseInt("1", lineNumber));
		assertEquals("direction should be parsed correctly", -1, EventReader.EventParseInt("-1", lineNumber));
	}
	
	@Test
	public void TestEventParseDirection() {
		assertEquals("destination floor should be parsed correctly", Direction.UP, EventReader.EventParseDirection("up", lineNumber));
		assertEquals("destination floor should be parsed correctly", Direction.DOWN, EventReader.EventParseDirection("DOWN", lineNumber));
	}
	

}
