package event;

public class ElevatorLampEvent extends Event {
	private LampState lampState;
	
	public ElevatorLampEvent(LampState lampState) {
		this.lampState = lampState;
	}
	
	public LampState getLampState() {
		return lampState;
	}
}
