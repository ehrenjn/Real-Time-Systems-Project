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
import scheduler.*;
import event.toScheduler.*;

public class EventReader {
	
	
	/**
	 * Reads in a file given the relative path of the file
	 * @param fileLocation The file name of the file in relative terms to the project
	 * @return an ArrayList containing all the events read from a file
	 */
	public static ArrayList<FloorPressButtonEvent> fromEventFile(String fileLocation) {
		try (FileInputStream stream = new FileInputStream(new File(fileLocation))) {
			return fromEventFile(stream);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	/**
	 * Creates an array of Events from a FloorPressButtonEvent file
	 * @param FloorPressButtonEvent the FloorPressButtonEvent input stream to read a list of events from
	 * @return An array of Events
	 */
	public static ArrayList<FloorPressButtonEvent> fromEventFile(InputStream eventFile) {
		ArrayList<FloorPressButtonEvent> events = new ArrayList<FloorPressButtonEvent>();
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(eventFile))) {
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				int lineNumber = events.size() + 1;
				String[] columns = line.split("\\s+");
				
				if (columns.length != 4) {
					throw new IllegalArgumentException("FloorPressButtonEvent file does not contain the correct number of columns on line " + lineNumber);
				}
				
				Date newTime = EventParseTime(columns[0], lineNumber);
				int newCurrentFloor = EventParseInt(columns[1], lineNumber);
				Direction newDirection = EventParseDirection(columns[2], lineNumber);
				int newDesiredFloor = EventParseInt(columns[3], lineNumber);
				//Fix this
				events.add(new FloorPressButtonEvent(Scheduler.SCHEDULER_ID, newCurrentFloor, newTime, newCurrentFloor, newDirection, newDesiredFloor));
			}
		}
		
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return events;
	}
	
	/**
	 * Parses a string in the format "HH:mm:ss.SSS" and converts it to a Date object
	 * @param timeStr the string to be parse
	 * @param lineNumber the lineNumber of the string to be parse
	 * @return A Date parsed from the string
	 */
	public static Date EventParseTime(String timeStr, int lineNumber) {
		SimpleDateFormat timeParser = new SimpleDateFormat("HH:mm:ss.SSS");
		try {
			return timeParser.parse(timeStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Time is not formatted properly in FloorPressButtonEvent file on line " + lineNumber);
		}  
	}
	
	/**
	 * Parses a string Integer and converts it to an int
	 * @param intStr the string to be parse
	 * @param lineNumber the lineNumber of the string to be parse
	 * @return An int parsed from the string
	 */
	public static int EventParseInt(String intStr, int lineNumber) {
		try {
			return Integer.parseInt(intStr);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Floor number is not formatted properly in FloorPressButtonEvent file on line " + lineNumber);
		}
	}
	
	/**
	 * Parses a string Direction and converts it to a Direction eum
	 * @param directionStr the string to be parse
	 * @param lineNumber the lineNumber of the string to be parse
	 * @return A Direction parsed from the string
	 */
	public static Direction EventParseDirection(String directionStr, int lineNumber) {
		try{
			directionStr = directionStr.toUpperCase();
			return Direction.valueOf(directionStr);
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Direction is not formatted properly in FloorPressButtonEvent file on line " + lineNumber);
		}
 	}
}
