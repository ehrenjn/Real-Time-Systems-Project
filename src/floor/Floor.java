package floor;

import java.util.ArrayList;
import common.CommunicationSocket;
import event.Event;
import common.*;

public class Floor{
	private int floorNumber;
	private CommunicationSocket floorSocket;
	private LampState upButton;
	private LampState downButton;
	
	/**
	 * Creates a new CommunicationSocket for floor
	 * Creates # of floors
	 */
	public Floor(CommunicationSocket floorSocket, int floorNumber) {
		this.floorNumber = floorNumber;
		this.floorSocket = floorSocket;
		this.upButton = LampState.OFF;
		this.downButton = LampState.OFF;
	}
	
	/**
	 * Receives floor event from the server
	 * @return the floor event that was received
	 */
	public Event recieveEventIn()
	{
		return this.floorSocket.recieveEventIn();
	}
	
	/**
	 * Sends the floor event to the server
	 * @param event is the event to send
	 */
	
	public void sendEventOut(Event event)
	{
		this.floorSocket.sendEventOut(event);
	}
	
	public void changeButtonLampState(Direction direction, LampState state) {
		if (direction == Direction.UP) {
			upButton = state;
		} else if (direction == Direction.DOWN) {
			downButton = state;
		}
		System.out.println(String.format(
				"Floor %d %s button lamp turned %s", floorNumber, direction, state));
	}
	
}

