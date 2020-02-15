package event;

/**
 * Represents an event in which an elevator arrives at a floor
 *
 */
public class ElevatorArrivalEvent extends Event {
	private int floor;
	public static final String NAME = "ElevatorArrivalEvent";
	
	/**
	 * Creates a new ElevatorArrivalEvent
	 * @param floor the floor that has been arrived
	 */
	public ElevatorArrivalEvent(int floor, int recipientId, int senderId) {
		super(NAME, recipientId, senderId);
		this.floor = floor;
	}
	
	public int getFloor() {
		return floor;
	}
}
