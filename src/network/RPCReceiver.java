package network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.LinkedList;

import event.Event;

public class RPCReceiver {
	private EventSocket socket;
	private EventQueue schedulerEventQueue;
	private MultiRecipientEventQueue floorEventQueue;
	private MultiRecipientEventQueue elevatorEventQueue;
	
	
	/**
	 * Creates a new RPCReceiver
	 * @param port the port to listen on
	 */
	public RPCReceiver(int port, EventQueue schedulerEventQueue, MultiRecipientEventQueue floorEventQueue, 
	MultiRecipientEventQueue elevatorEventQueue) {
		this.schedulerEventQueue = schedulerEventQueue;
		this.floorEventQueue = floorEventQueue;
		this.elevatorEventQueue = elevatorEventQueue;
		socket = new EventSocket(port);
	}
	
	
	private Event receiveEvent() {
		CODE();
	}
	
	
	private Event sendEvent() {
		CODE();
	}
	
	
	public void start() {
		Thread eventReceiver = new Thread() {
			public void run() {
				while (true) {
					Event event = receiveEvent();
					if (EVENT_FOR_SCHEDULER) {
						schedulerEventQueue.addEvent(event);
					} else if (REQUEST_FROM_ELEVATORS) {
						elevatorEventQueue.addRecipientWaitingForEvent(event.getSender());
					} else if (REQUEST_FROM_FLOORS) {
						floorEventQueue.addRecipientWaitingForEvent(event.getSender());
					}
				}
			}
		};
		
		Thread elevatorResponseSender = new Thread() {
			public void run() {
				while (true) {
					Event event = elevatorEventQueue.getEventForRecipients();
					sendEvent(event);
				}
			}
		};
		
		Thread floorResponseSender = new Thread() {
			public void run() {
				while (true) {
					Event event = floorEventQueue.getEventForRecipients();
					sendEvent(event);
				}
			}
		};
		
		eventReceiver.start();
		elevatorResponseSender.start();
		floorResponseSender.start();
	}
}
