package scheduler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.LinkedList;

import event.Event;

public class RPCReceiver {
	private DatagramSocket socket;
	private SchedulerMessageQueue messageQueue;
	
	
	/**
	 * Creates a new RPCReceiver
	 * @param port the port to listen on
	 */
	public RPCReceiver(int port, SchedulerMessageQueue messageQueue) {
		this.messageQueue = messageQueue;
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
						messageQueue.sendToScheduler(event);
					} else {
						messageQueue.addElevatorWaitingForResponse(event.getSender());
					}
				}
			}
		};
		Thread responseSender = new Thread() {
			public void run() {
				while (true) {
					Event event = messageQueue.getEventForElevators();
					send(event);
				}
			}
		};
		
		eventReceiver.start();
		responseSender.start();
	}
}
