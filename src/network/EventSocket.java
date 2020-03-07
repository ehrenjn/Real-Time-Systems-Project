package network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import event.Event;

/**
 * A class for sending and receiving Events
 *
 */

public class EventSocket {
	private DatagramSocket socket;
	private static final int MAX_PACKET_SIZE = 1024;
	
	
	/**
	 * Constructs a new EventSocket bound to a specific port
	 * @param port the port to bind to
	 */
	public EventSocket(int port) {
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	/**
	 * Constructs a new EventSocket bound to any port
	 */
	public EventSocket() {
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	/**
	 * Sends an Event to a destination
	 * @param event the event to send
	 * @param destIp the InetAddress of the destination
	 * @param destPort the port number of the destination
	 */
	public void sendEvent(Event event, InetAddress destIp, int destPort) {
		byte[] data = EventSerializer.serialize(event);
		if (data.length > MAX_PACKET_SIZE) {
			throw new IllegalArgumentException("Data is too large, can't send");
		}
		DatagramPacket packet = new DatagramPacket(data, data.length, destIp, destPort);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	/**
	 * Receives a DatagramPacket
	 * @return the datagram packet sent to this socket
	 */
	public Event receiveEvent() {
		byte[] buffer = new byte[MAX_PACKET_SIZE];
		DatagramPacket receiver = new DatagramPacket(buffer, buffer.length);
		try {
			socket.receive(receiver);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		Event event = EventSerializer.deserialize(buffer);
		return event;
	}
	
	
	/**
	 * Gets the port of this event socket
	 */
	public int getPort() {
		return socket.getLocalPort();
	}
	
	
	/**
	 * Closes this EventSocket
	 */
	public void close() {
		socket.close();
	}
}
