package network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.LinkedList;

import event.AcknowledgementEvent;
import event.Event;
import event.toScheduler.RequestForElevatorMessageEvent;
import event.toScheduler.RequestForFloorMessageEvent;

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
	
	
	public void start() {
		Thread eventReceiver = new Thread() {
			public void run() {
				while (true) {
					Event event = socket.receiveEvent();
					if (event.getName() == RequestForElevatorMessageEvent.NAME) {
						elevatorEventQueue.addRecipientWaitingForEvent(event.getSender());
					} else if (event.getName() == RequestForFloorMessageEvent.NAME) {
						floorEventQueue.addRecipientWaitingForEvent(event.getSender());
					} else { // this is an event for the scheduler
						AcknowledgementEvent ack = new AcknowledgementEvent(event.getSender(), event.getRecipient());
						socket.sendEvent(ack, event.getSenderIp(), event.getSenderPort());
						schedulerEventQueue.addEvent(event);
					}
				}
			}
		};
		
		Thread elevatorResponseSender = new Thread() {
			public void run() {
				while (true) {
					Event event = elevatorEventQueue.getEventForRecipients();
					socket.sendEvent(event, event.getToIp(), event.getToPort());
				}
			}
		};
		
		Thread floorResponseSender = new Thread() {
			public void run() {
				while (true) {
					Event event = floorEventQueue.getEventForRecipients();
					socket.sendEvent(event, event.getToIp(), event.getToPort());
				}
			}
		};
		
		eventReceiver.start();
		elevatorResponseSender.start();
		floorResponseSender.start();
	}
}
