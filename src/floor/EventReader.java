package floor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import common.Direction;
import common.Event;

public class EventReader {

	public static ArrayList<Event> fromEventFile(String fileLocation) {
		try (FileInputStream stream = new FileInputStream(new File(fileLocation))) {
			return fromEventFile(stream);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	/**
	 * Creates an array of Events from a Event file
	 * @param Event the Event input stream to read a list of events from
	 * @return An array of Events
	 */
	public static ArrayList<Event> fromEventFile(InputStream eventFile) {
		ArrayList<Event> events = new ArrayList<Event>();
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(eventFile))) {
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				int lineNumber = events.size() + 1;
				String[] columns = line.split("\\s+");
				
				if (columns.length != 4) {
					throw new IllegalArgumentException("Event file does not contain the correct number of columns on line " + lineNumber);
				}
				
				Date newTime = EventParseTime(columns[0], lineNumber);
				int newCurrentFloor = EventParseInt(columns[1], lineNumber);
				Direction newDirection = EventParseDirection(columns[2], lineNumber);
				int newDesiredFloor = EventParseInt(columns[3], lineNumber);
				
				events.add(new Event(newTime, newCurrentFloor, newDirection, newDesiredFloor));
			}
		}
		
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return events;
	}
	
	
	private static Date EventParseTime(String timeStr, int lineNumber) {
		SimpleDateFormat timeParser = new SimpleDateFormat("HH:mm:ss.SSS");
		try {
			return timeParser.parse(timeStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Time is not formatted properly in Event file on line " + lineNumber);
		}  
	}
	
	private static int EventParseInt(String intStr, int lineNumber) {
		try {
			return Integer.parseInt(intStr);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Floor number is not formatted properly in Event file on line " + lineNumber);
		}
	}
	
	private static Direction EventParseDirection(String directionStr, int lineNumber) {
		try{
			return Direction.getValue(directionStr);
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Direction is not formatted properly in Event file on line " + lineNumber);
		}
 	}
}
