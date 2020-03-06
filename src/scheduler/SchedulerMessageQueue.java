package scheduler;

import java.util.HashSet;
import java.util.LinkedList;

import common.Constants;
import event.Event;

public class SchedulerMessageQueue {
	private LinkedList<Event> toScheduler;
	private LinkedList<Event>[] toElevators;
	private HashSet<Integer> elevatorsWaitingForResponse;
	
	
	/**
	 * Creates a new SchedulerMessageQueue
	 */
	public SchedulerMessageQueue() {
		toScheduler = new LinkedList<Event>();
		toElevators = new LinkedList[Constants.NUM_ELEVATORS];
		elevatorsWaitingForResponse = new HashSet<Integer>();
		for (int queue = 0; queue < Constants.NUM_ELEVATORS; queue++) {
			toElevators[queue] = new LinkedList<Event>();
		}
	}
	
	
	/**
	 * Sends an event to the queue to be picked up by the scheduler
	 * @param event the event to send
	 */
	public synchronized void sendToScheduler(Event event) {
		toScheduler.add(event);
		notifyAll();
	}
	
	
	/**
	 * Blocks until there's an event for the scheduler
	 * @return the event for the scheduler
	 */
	public synchronized Event getNextSchedulerEvent() {
		while (toScheduler.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		return toScheduler.pop();
	}
	
	
	/**
	 * Sends an event into the queue to be sent to the elevators
	 * @param event the event to send
	 */
	public synchronized void sendEventToElevators(Event event) {
		toElevators[event.getRecipient()].add(event);
		notifyAll();
	}
	
	
	/**
	 * Registers an elevator id as waiting for a response event from the scheduler
	 * @param elevatorId
	 */
	public synchronized void addElevatorWaitingForResponse(int elevatorId) {
		elevatorsWaitingForResponse.add(elevatorId);
		notifyAll();
	}
	
	
	private Event tryToGetNextEventForElevators() {
		for (int elevatorId: elevatorsWaitingForResponse) {
			if (! toElevators[elevatorId].isEmpty()) {
				elevatorsWaitingForResponse.remove(elevatorId);
				return toElevators[elevatorId].pop();
			}
		}
		return null;
	}
	
	
	/**
	 * Blocks until there is both an elevator waiting for a response from the scheduler AND a response from the scheduler for that elevator
	 * @return an event to be sent to an elevator
	 */
	public synchronized Event getEventForElevators() {
		Event response = tryToGetNextEventForElevators();
		while (response == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(1);
			}
			response = tryToGetNextEventForElevators();
		}
		return response;
	}
	
}
