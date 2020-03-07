package scheduler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.LinkedList;

import event.Event;

public class RPCReceiver {
	private DatagramSocket socket;
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
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	/**
	 * Receives a DatagramPacket
	 * @return the datagram packet sent to this socket
	 */
	private DatagramPacket receive() {
		byte[] buffer = new byte[MAX_PACKET_SIZE];
		DatagramPacket receiver = new DatagramPacket(buffer, buffer.length);
		try {
			socket.receive(receiver);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		NetworkHelpers.printPacketInfo(receiver);
		return receiver;
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
					send(event);
				}
			}
		};
		
		Thread floorResponseSender = new Thread() {
			public void run() {
				while (true) {
					Event event = floorEventQueue.getEventForRecipients();
					send(event);
				}
			}
		};
		
		eventReceiver.start();
		elevatorResponseSender.start();
		floorResponseSender.start();
	}
}
