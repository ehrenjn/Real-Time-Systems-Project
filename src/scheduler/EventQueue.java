package scheduler;

import java.util.LinkedList;

import event.Event;

/**
 * A thread safe queue of events
 *
 */

public class EventQueue {
	
	private LinkedList<Event> queue;
	
	/**
	 * Creates a new event queue
	 */
	public EventQueue() {
		queue = new LinkedList<Event>();
	}
	
	
	/**
	 * Adds a new event to the queue
	 * @param event the event to add
	 */
	public synchronized void addEvent(Event event) {
		queue.add(event);
		notifyAll();
	}
	
	
	/**
	 * Blocks until there is an event available 
	 * @return the event received
	 */
	public synchronized Event getNextEvent() {
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
