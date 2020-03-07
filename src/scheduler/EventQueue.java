package scheduler;

import java.util.LinkedList;

import event.Event;

public class EventQueue {
	
	private LinkedList<Event> queue;
	
	
	public EventQueue() {
		queue = new LinkedList<Event>();
	}
	
	
	public void addEvent(Event event) {
		queue.add(event);
		notifyAll();
	}
	
	
	public Event getNextEvent() {
		while (queue.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		return queue.pop();
	}
}
