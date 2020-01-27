package main;
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


/**
 * A class representing events caused by a person waiting for an elevator
 * An event consists of a time, a floor that the person wants is on, a destination floor, and a desired direction 
 */
public class Event {
	/**
	 * A enum representing the direction of an elevator
	 */
	public static enum Direction {
		UP, DOWN;
	}
	
	private Date time;
	private int currentFloor;
	private Direction direction;
	private int desiredFloor;
	
	/**
	 * Constructs a new Event
	 * @param time the time the event occurs
	 * @param currentFloor the floor the person is on
	 * @param direction the direction that the person has selected
	 * @param desiredFloor the floor the person would like to travel to
	 */
	public Event(Date time, int currentFloor, Direction direction, int desiredFloor) {
		this.time = time;
		this.currentFloor = currentFloor;
		this.direction = direction;
		this.desiredFloor = desiredFloor;
	}
	
	public String toString() {
		String formatStr = "Event[time=%tr and %tN ns, currentFloor=%d, direction=%s, desiredFloor=%d]";
		return String.format(formatStr, time, time, currentFloor, direction, desiredFloor);
	}
	
	/**
	 * @return the time of this event
	 */
	public Date getTime() {
		return time;
	}
	
	/**
	 * @return floor this event originates on
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}
	
	/**
	 * @return which direction button was pressed in this event
	 */
	public Direction getDirection() {
		return direction;
	}
	
	/**
	 * @return where the person causing this event would like to go
	 */
	public int getDesiredFloor() {
		return desiredFloor;
	}
	
	
	public static ArrayList<Event> fromCSV(String fileLocation) {
		try (FileInputStream stream = new FileInputStream(new File(fileLocation))) {
			return fromCSV(stream);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	/**
	 * Creates an array of Events from a CSV file
	 * @param csv the CSV input stream to read a list of events from
	 * @return An array of Events
	 */
	public static ArrayList<Event> fromCSV(InputStream csv) {
		ArrayList<Event> events = new ArrayList<Event>();
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(csv))) {
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				int lineNumber = events.size() + 1;
				String[] columns = line.split(",");
				
				if (columns.length != 4) {
					throw new IllegalArgumentException("CSV file does not contain the correct number of columns on line " + lineNumber);
				}
				
				Date newTime = CSVParseTime(columns[0], lineNumber);
				int newCurrentFloor = CSVParseInt(columns[1], lineNumber);
				Direction newDirection = CSVParseDirection(columns[2], lineNumber);
				int newDesiredFloor = CSVParseInt(columns[3], lineNumber);
				
				events.add(new Event(newTime, newCurrentFloor, newDirection, newDesiredFloor));
			}
		}
		
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return events;
	}
	
	
	private static Date CSVParseTime(String timeStr, int lineNumber) {
		SimpleDateFormat timeParser = new SimpleDateFormat("HH:mm:ss.SSS");
		try {
			return timeParser.parse(timeStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Time is not formatted properly in CSV file on line " + lineNumber);
		}  
	}
	
	private static int CSVParseInt(String intStr, int lineNumber) {
		try {
			return Integer.parseInt(intStr);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Floor number is not formatted properly in CSV file on line " + lineNumber);
		}
	}
	
	private static Direction CSVParseDirection(String directionStr, int lineNumber) {
		String normalizedStr = directionStr.toLowerCase();
		if (normalizedStr.equals("up")) {
			return Direction.UP;
		} else if (normalizedStr.equals("down")) {
			return Direction.DOWN;
		}
		throw new IllegalArgumentException("Direction is not formatted properly in CSV file on line " + lineNumber);
 	}
}
