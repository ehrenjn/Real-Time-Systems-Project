package network;

import java.io.ByteArrayInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;

import event.Event;

public class RPCSender {
	private EventSocket socket;
	private InetAddress destinationIp;
	private int destinationPort;
	
	/**
	 * Creates a new RPCSender
	 * @param toIp the Ip to send to and receive from
	 * @param toPort the port to send to and receive from
	 */
	public RPCSender(InetAddress toIp, int toPort) {
		destinationIp = toIp;
		destinationPort = toPort;
		socket = new EventSocket();
	}
	
	/**
	 * Receives a new message from an RPCReceiver
	 * @return the message received
	 */
	private Event receiveEvent() {
		CODE();
	}
	
	/**
	 * Sends a message to an RPCReceiver
	 * @param data the message to send
	 */
	private Event sendEvent() {
		CODE();
	}
}