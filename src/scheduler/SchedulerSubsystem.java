package scheduler;

import common.Constants;
import event.Event;

public class SchedulerSubsystem {
	
	/**
	 * The method to run as in a Thread. Runs forever as it waits for incoming event
	 */
	public static void main(String[] args) {
		SchedulerMessageQueue messageQueue = new SchedulerMessageQueue();
		RPCReceiver server = new RPCReceiver(Constants.SCHEDULER_PORT, messageQueue);
		server.start();
		
		Scheduler scheduler = new Scheduler(messageQueue);
		while(true) {
			Event event = messageQueue.getNextSchedulerEvent();
			System.out.println("Scheduler recieved: " + event);
			scheduler.handleEvent(event);
		}
	}
}
