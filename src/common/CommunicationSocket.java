package common;

public class CommunicationSocket {
	private Event eventIn;
	private Event eventOut;
	
	public CommunicationSocket() {
		this.eventIn = null;
		this.eventOut = null;
	}
	
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
