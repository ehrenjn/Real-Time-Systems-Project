package network;

import java.net.InetAddress;

import event.Event;

/**
 * Represents a recipient with an ID, IP, and port
 *
 */

public class RecipientAddress {
	private InetAddress ip;
	private int port;
	private int id;
	
	/**
	 * Creates a new Recipient
	 * @param id id of Recipient
	 * @param address address of Recipient
	 * @param port port of Recipient
	 */
	public RecipientAddress(int id, InetAddress ip, int port) {
		this.id = id;
		this.ip = ip;
		this.port = port;
	}
	
	public InetAddress getIp() {
		return ip;
	}
	public int getPort() {
		return port;
	}
	public int getId() {
		return id;
	}
	
	/**
	 * @param event event to make Recipient out of
	 * @return the Recipient
	 */
	public static RecipientAddress fromEvent(Event event) {
		return new RecipientAddress(event.getSender(), event.getSenderIp(), event.getSenderPort());
	}
}
