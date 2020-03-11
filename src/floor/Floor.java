package floor;

import java.util.ArrayList;
import common.CommunicationSocket;
import event.Event;
import common.*;

public class Floor{
	private int floorNumber;
	private LampState upButton;
	private LampState downButton;
	
	/**
	 * Creates a new CommunicationSocket for floor
	 * Creates # of floors
	 */
	public Floor(int floorNumber) {
		this.floorNumber = floorNumber;
		this.upButton = LampState.OFF;
		this.downButton = LampState.OFF;
	}
	
	
	/**
	 * Changes the state for a direction lamp on this floor
	 * @param direction the direction lamp to change
	 * @param state the state to change the lamp to
	 */
	public void changeButtonLampState(Direction direction, LampState state) {
		if (direction == Direction.UP) {
			upButton = state;
		} else if (direction == Direction.DOWN) {
			downButton = state;
		}
		ThreadPrinter.print(String.format(
				"Floor %d %s button lamp turned %s", floorNumber, direction, state));
	}
	
	/**
	 * @return floor number for this floor
	 */
	public int getFloorNumber() {
		return floorNumber;
	}

	
	/**
	 * @return upButton state
	 */
	public LampState getUpButton() {
		return upButton;
	}
	
	/**
	 * @return upButton state
	 */
	public LampState getDownButton() {
		return downButton;
	}
	
}

