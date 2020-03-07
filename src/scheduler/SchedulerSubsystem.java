package scheduler;

import common.Constants;
import common.*;
import event.Event;

public class SchedulerSubsystem {
	
	/**
	 * The method to run as in a Thread. Runs forever as it waits for incoming event
	 */
	public static void main(String[] args) {
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
