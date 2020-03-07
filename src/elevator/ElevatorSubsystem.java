package elevator;

import java.lang.management.ThreadInfo;
import java.util.ArrayList;
import java.util.List;

import common.CommunicationSocket;
import common.Constants;
import event.Event;
import floor.EventReader;

public class ElevatorSubsystem {	
	public static void main(String args[]) {
		Elevator[] elevators = new Elevator[Constants.NUM_ELEVATORS];
		for (int id = 0; id < Constants.NUM_ELEVATORS; id++) {
			Elevator elevator = new Elevator(Constants.NUM_FLOORS, id);
			new Thread(elevator).start();
		}
	}
	
}
