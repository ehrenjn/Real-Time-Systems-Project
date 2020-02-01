package common;

/**
 * A placeholder synchronized "box" class for event passing between threads. In the future it will be replaced with a
 * DatagramSocket class.
 */
public class CommunicationSocket {
	private Event eventIn;
	private Event eventOut;
	
	/**
	 * Creates a new CommunicationSocket
	 */
	public CommunicationSocket() {
		this.eventIn = null;
		this.eventOut = null;
	}
	
	/**
	 * Sends an event to a client
	 * @param event the event to send
	 */
	public synchronized void sendEventIn(Event event) {
        while (this.eventIn != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                return;
            }
        }
		this.eventIn = event;
		notifyAll();
	}
	
	/**
	 * Sends an event to a server
	 * @param event the event to send
	 */
	public synchronized void sendEventOut(Event event) {
        while (this.eventOut != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                return;
            }
        }
		this.eventOut = event;
		notifyAll();
	}
	
	/**
	 * Receives an event from a server
	 * @return the event received
	 */
	public synchronized Event recieveEventIn() {
        while (this.eventIn == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                return null;
            }
        }
        Event eventIn = this.eventIn;
        this.eventIn = null;
        notifyAll();
		return eventIn;
	}
	
	/**
	 * Receives an event from a client
	 * @return the event received
	 */
	
	public synchronized Event recieveEventOut() {
        while (this.eventOut == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                return null;
            }
        }
        Event eventOut = this.eventOut;
        this.eventOut = null;
        notifyAll();
		return eventOut;
	}
}
