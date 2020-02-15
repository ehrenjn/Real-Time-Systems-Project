package event;

/**
 * Represents the event of a button being pressed in an elevator
 *
 */
public class ElevatorButtonEvent extends Event {
	private int desiredFloor;
	
	/**
	 * Creates a new ElevatorButtonEvent
	 * @param desiredFloor the floor a person would like to go to
	 */
	public ElevatorButtonEvent(int desiredFloor) {
		this.desiredFloor = desiredFloor;
	}
	
	public int getDesiredFloor() {
		return desiredFloor;
	}
}
