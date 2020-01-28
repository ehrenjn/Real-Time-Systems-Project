package main;
import java.io.File;

import java.util.ArrayList;

import common.Event;
import floor.EventReader;


public class Main {
	public static void main(String[] args) {
		ArrayList<Event> events = EventReader.fromEventFile("floorEvents.tsv");
		for (Event event: events) {
			System.out.println(event);
		}
	}
}
