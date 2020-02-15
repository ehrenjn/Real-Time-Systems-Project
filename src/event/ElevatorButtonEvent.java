package event;

/**
 * Represents the event of a button being pressed in an elevator
 *
 */
public class ElevatorButtonEvent extends Event {
	private int desiredFloor;
	public static final String NAME = "ElevatorButtonEvent";
	
	/**
	 * Creates a new ElevatorButtonEvent
	 * @param desiredFloor the floor a person would like to go to
	 */
	public ElevatorButtonEvent(int desiredFloor, int recipientId, int senderId) {
		super(NAME, recipientId, senderId);
		this.desiredFloor = desiredFloor;
	}
	
	public int getDesiredFloor() {
		return desiredFloor;
	}
}
