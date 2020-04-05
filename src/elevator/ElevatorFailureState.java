package elevator;
/**
 * Impossible state denoting failure of an elevator. Should never be achieved.
 */
public class ElevatorFailureState extends ElevatorState{
	public static final String NAME = "ElevatorFailureState";
	
	public ElevatorFailureState() {
		super(-1);
		this.name = NAME;
	}

	public ElevatorFailureState(ElevatorState state) {
		super(state);
	}

}
