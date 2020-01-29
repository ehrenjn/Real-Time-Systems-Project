package floor;

import java.util.ArrayList;

import common.Event;

public class Floor{
	private int floorNumber;
	private DirectionLamp[] directionLamps;
	private FloorLamp[] floorLamps;
	private FloorButton[] floorButtons;
	private ArrayList<Event> upEventList;
	private ArrayList<Event> downEventList;
	
	public Floor(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	
}
