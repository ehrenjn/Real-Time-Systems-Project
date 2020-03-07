package scheduler;

import common.Constants;
import common.*;
import event.Event;
import network.EventQueue;
import network.MultiRecipientEventQueue;
import network.NetworkHelpers;
import network.RPCReceiver;

public class SchedulerSubsystem {
	
	/**
	 * The method to run as in a Thread. Runs forever as it waits for incoming event
	 */
	public static void main(String[] args) {
		ThreadPrinter.setColumnWidth(40);
		ThreadPrinter.print("This is the scheduler main thread");
		ThreadPrinter.print("Ip of scheduler: " + NetworkHelpers.getLocalIp());
		
		EventQueue schedulerEventQueue = new EventQueue();
		MultiRecipientEventQueue floorEventQueue = new MultiRecipientEventQueue();
		MultiRecipientEventQueue elevatorEventQueue = new MultiRecipientEventQueue();
		
		RPCReceiver server = new RPCReceiver(Constants.SCHEDULER_PORT, schedulerEventQueue, floorEventQueue, elevatorEventQueue);
		server.start();
		
		Scheduler scheduler = new Scheduler(floorEventQueue, elevatorEventQueue);
		while(true) {
			Event event = schedulerEventQueue.getNextEvent();
			ThreadPrinter.print("Scheduler recieved: " + event);
			scheduler.handleEvent(event);
		}
	}
}
