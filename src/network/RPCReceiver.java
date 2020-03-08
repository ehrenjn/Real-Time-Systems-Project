package network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.LinkedList;

import common.ThreadPrinter;
import event.AcknowledgementEvent;
import event.Event;
import event.toScheduler.RequestForElevatorMessageEvent;
import event.toScheduler.RequestForFloorMessageEvent;

public class RPCReceiver {
	private EventSocket socket;
	private EventQueue schedulerEventQueue;
	private MultiRecipientEventQueue floorEventQueue;
	private MultiRecipientEventQueue elevatorEventQueue;
	private InetAddress elevatorSubsystemAddress;
	private InetAddress floorSubsystemAddress;
	
	
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
				ThreadPrinter.print("This is the eventReceiver thread");
				while (true) {
					Event event = socket.receiveEvent();
					ThreadPrinter.print("Received event: " + event);
					if (event.getName().equals(RequestForElevatorMessageEvent.NAME)) {
						ThreadPrinter.print("got a request for elevator message");
						RecipientAddress recipient = RecipientAddress.fromEvent(event);
						elevatorEventQueue.addRecipientWaitingForEvent(recipient);
					} else if (event.getName().equals(RequestForFloorMessageEvent.NAME)) {
						ThreadPrinter.print("got a request for floor message");
						RecipientAddress recipient = RecipientAddress.fromEvent(event);
						floorEventQueue.addRecipientWaitingForEvent(recipient);
					} else { // this is an event for the scheduler
						ThreadPrinter.print("This event is for the scheduler");
						AcknowledgementEvent ack = new AcknowledgementEvent(event.getSender(), event.getRecipient());
						socket.sendEvent(ack, event.getSenderIp(), event.getSenderPort());
						ThreadPrinter.print("Ack sent back to sender");
						schedulerEventQueue.addEvent(event);
					}
					ThreadPrinter.print(" ");
				}
			}
		};
		
		Thread elevatorResponseSender = new Thread() {
			public void run() {
				ThreadPrinter.print("This is the elevatorResponseSender thread");
				while (true) {
					Event event = elevatorEventQueue.getEventForRecipients();
					ThreadPrinter.print("Received response: " + event);
					socket.sendEvent(event, event.getToIp(), event.getToPort());
				}
			}
		};
		
		Thread floorResponseSender = new Thread() {
			public void run() {
				ThreadPrinter.print("This is the floorResponseSender thread");
				while (true) {
					Event event = floorEventQueue.getEventForRecipients();
					ThreadPrinter.print("Received response: " + event);
					socket.sendEvent(event, event.getToIp(), event.getToPort());
				}
			}
		};
		
		eventReceiver.start();
		elevatorResponseSender.start();
		floorResponseSender.start();
	}
}
