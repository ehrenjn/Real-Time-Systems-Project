package event.toScheduler;

import event.*;

/**
 * Represents a response to pressing a button
 */
public class ElevatorButtonPressedEvent extends Event{
	public ElevatorButtonPressedEvent(String name, int recipientId, int senderId) {
		super(name, recipientId, senderId);
	}
}
