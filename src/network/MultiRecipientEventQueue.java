package network;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import common.ThreadPrinter;
import event.Event;

public class MultiRecipientEventQueue {
	private HashMap<Integer, LinkedList<Event>> messageQueues;
	private HashSet<RecipientAddress> recipientsWaitingForResponse;
	
	
	public MultiRecipientEventQueue() {
		messageQueues = new HashMap<Integer, LinkedList<Event>>();
		recipientsWaitingForResponse = new HashSet<RecipientAddress>();
	}
	
	
	/**
	 * Adds an event into the queue to be sent to the recipients
	 * @param event the event to add
	 */
	public synchronized void addEvent(Event event) {
		int recipient = event.getRecipient();
		if (! messageQueues.containsKey(recipient)) { // create queue for recipient if they don't have one yet
			messageQueues.put(recipient, new LinkedList<Event>());
		}
		messageQueues.get(recipient).add(event);
		notifyAll();
	}
	
	
	/**
	 * Registers a recipient as waiting for a response event from the scheduler
	 * @param recipientId id of the recipient
	 */
	public synchronized void addRecipientWaitingForEvent(RecipientAddress recipient) {
		recipientsWaitingForResponse.add(recipient);
		notifyAll();
	}
	
	
	private Event tryToGetNextEventForRecipients() {
		for (RecipientAddress recipient: recipientsWaitingForResponse) {
			LinkedList<Event> messages = messageQueues.get(recipient.getId());
			if (messages != null && ! messages.isEmpty()) {
				recipientsWaitingForResponse.remove(recipient);
				Event event = messages.pop();
				event.setToIp(recipient.getIp()); // make sure event keeps track of where it should go
				event.setToPort(recipient.getPort());
				return event;
			}
		}
		return null;
	}
	
	
	/**
	 * Blocks until there is both a recipient waiting for a message AND a message for that recipient
	 * @return an event to be sent to a recipient
	 */
	public synchronized Event getEventForRecipients() {
		Event message = tryToGetNextEventForRecipients();
		while (message == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(1);
			}
			message = tryToGetNextEventForRecipients();
		}
		return message;
	}
}
