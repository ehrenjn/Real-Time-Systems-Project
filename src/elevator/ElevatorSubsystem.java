package elevator;

import java.net.InetAddress;

import common.Constants;
import network.NetworkHelpers;


public class ElevatorSubsystem {
	public static void main(String args[]) {
		InetAddress ip = NetworkHelpers.getIPFromInput();
		
		for (int id = 0; id < Constants.NUM_ELEVATORS; id++) {
			Elevator elevator = new Elevator(Constants.NUM_FLOORS, id,ip);
			new Thread(elevator).start();
		}
	}
	
}
