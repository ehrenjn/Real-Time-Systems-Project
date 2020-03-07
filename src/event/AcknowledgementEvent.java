package event;

/**
 * A event for the scheduler to acknowledge that it has received a message from the elevator or floor subsystem
 *
 */

public class AcknowledgementEvent extends Event {
	public static final String NAME = "AcknowledgementEvent";
	
	/**
	 * Creates a new AcknowledgementEvent
	 * @param recipientId the id of the recipient
	 * @param senderId the id of the sender
	 */
	public AcknowledgementEvent(int recipientId, int senderId) {
		super(NAME, recipientId, senderId);
	}
}
