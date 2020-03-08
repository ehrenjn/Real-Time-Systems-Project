package floor;

import java.util.ArrayList;
import java.util.List;

import common.CommunicationSocket;
import common.Constants;
import common.LampState;
import common.ThreadPrinter;
import event.*;
import event.toScheduler.*;
import floor.Floor;
import network.NetworkHelpers;
import network.RPCSender;

public class FloorSubsystem {
	
	// The relative filepath to read events from (starting from the project root)
	private static final String fileLocation = "floorEvents.tsv";
	
	
	public static void main(String[] args) {
		ThreadPrinter.setColumnWidth(100);
		Floor[] floors = new Floor[Constants.NUM_FLOORS];
		for (int floorNum = 0; floorNum < floors.length; floorNum++) {
			floors[floorNum] = new Floor(floorNum);
		}
		RPCSender rpcSender = new RPCSender(NetworkHelpers.getIPFromInput(), Constants.SCHEDULER_PORT);
		ArrayList<FloorPressButtonEvent> events = EventReader.fromEventFile(fileLocation);
		
		for (FloorPressButtonEvent event: events) {
			ThreadPrinter.print(event);
			Floor floor = floors[event.getSender()];
			floor.changeButtonLampState(event.getDirection(), LampState.ON);
			rpcSender.sendEvent(event);
			ThreadPrinter.print("Floor sent floor event out: " + event);
			
			FloorLampEvent lampEvent = (FloorLampEvent) rpcSender.receiveFloorEvent(floor.getFloorNumber());
			System.out.println("Floor receieved event: " + lampEvent);
			floors[lampEvent.getRecipient()].changeButtonLampState(
					lampEvent.getDirection(), lampEvent.getLampState());
		}
	}
}
