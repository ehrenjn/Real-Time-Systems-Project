package network;

import java.io.ByteArrayInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;

import common.Constants;
import common.ThreadPrinter;
import event.Event;
import event.toScheduler.RequestForElevatorMessageEvent;
import event.toScheduler.RequestForFloorMessageEvent;

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
		ThreadPrinter.print("starting RPCSender on " + NetworkHelpers.getLocalIp() + ":" + socket.getPort());
	}
	
	
	private Event addNetworkSenderDataToEvent(Event event) {
		event.setSenderIp(NetworkHelpers.getLocalIp());
		event.setSenderPort(socket.getPort());
		return event;
	}
	
	
	private Event receiveEvent(Event requestEvent) {
		requestEvent = addNetworkSenderDataToEvent(requestEvent);
		socket.sendEvent(requestEvent, destinationIp, destinationPort);
		return socket.receiveEvent();
	}
	
	
	/**
	 * Receives an elevator event
	 * @param fromId the id of the object calling this RPCSender
	 * @return the received event
	 */
	public Event receiveElevatorEvent(int thisId) {
		RequestForElevatorMessageEvent request = new RequestForElevatorMessageEvent(Constants.DUMMY_ID, thisId);
		return receiveEvent(request);
	}
	
	
	/**
	 * Receives a floor event
	 * @param fromId the id of the object calling this RPCSender
	 * @return the received event
	 */
	public Event receiveFloorEvent(int thisId) {
		RequestForFloorMessageEvent request = new RequestForFloorMessageEvent(Constants.DUMMY_ID, thisId);
		return receiveEvent(request);
	}
	

	/**
	 * Sends an event 
	 * @param event the event to send
	 */
	public void sendEvent(Event event) {
		event = addNetworkSenderDataToEvent(event);
		socket.sendEvent(event, destinationIp, destinationPort);
		socket.receiveEvent(); // wait for acknowledgement
	}
}