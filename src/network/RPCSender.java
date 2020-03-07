package network;

import java.io.ByteArrayInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;

import common.Constants;
import event.Event;
import event.toScheduler.RequestForElevatorMessageEvent;

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
	 * Receives an event
	 * @param fromId the id of the object calling this RPCSender
	 * @return the received event
	 */
	public Event receiveEvent(int thisId) {
		RequestForElevatorMessageEvent request = new RequestForElevatorMessageEvent(Constants.DUMMY_ID, thisId);
		request.setSenderIp(NetworkHelpers.getLocalIp());
		request.setSenderPort(socket.getPort());
		socket.sendEvent(request, destinationIp, destinationPort);
		return socket.receiveEvent();
	}
	

	/**
	 * Sends an event 
	 * @param event the event to send
	 */
	public void sendEvent(Event event) {
		socket.sendEvent(event, destinationIp, destinationPort);
		socket.receiveEvent(); // wait for acknowledgement
	}
}