package elevator;

import java.net.InetAddress;

import common.CommunicationSocket;
import common.Constants;
import common.Direction;
import common.LampState;
import common.ThreadPrinter;
import event.toScheduler.*;
import network.RPCSender;
import event.toElevator.*;
import event.Event;

public class Elevator implements Runnable {
	
	private RPCSender rpcSender;
	private ElevatorState state; 
	private int id;
	
	/**
	 * Creates a new Elevator
	 * @param elevatorSocket the socket for the elevator to receive and send events on
	 * @param numberOfFloors the number of floors the elevator must service
	 */
	public Elevator(int numberOfFloors, int id, InetAddress schedulerIp) {
		this.id = id;
		this.rpcSender = new RPCSender(schedulerIp, Constants.SCHEDULER_PORT);
		this.state = new ElevatorOpenDoorState(numberOfFloors);
	}
	
	/**
	 * Handles an elevator event
	 * @param event event to handle
	 */
	public void handleElevatorEvent(Event event){;
		switch (event.getName()) {
			case ElevatorCloseDoorEvent.NAME:
				this.handleElevatorCloseDoorEvent((ElevatorCloseDoorEvent) event);
				break;
			case ElevatorPressButtonEvent.NAME:
				this.handleElevatorPressButtonEvent((ElevatorPressButtonEvent) event);
				break;
			case ElevatorDirectionLampEvent.NAME:
				this.handleElevatorDirectionLampEvent((ElevatorDirectionLampEvent) event);
				break;
			case ElevatorButtonLampEvent.NAME:
				this.handleElevatorButtonLampEvent((ElevatorButtonLampEvent) event);
				break;
			case ElevatorOpenDoorEvent.NAME:
				this.handleElevatorOpenDoorEvent((ElevatorOpenDoorEvent) event);
				break;
			case ElevatorStartMovingEvent.NAME:
				this.handleElevatorStartMovingEvent((ElevatorStartMovingEvent) event);
				break;
			case ElevatorStopMovingEvent.NAME:
				this.handleElevatorStopMovingEvent((ElevatorStopMovingEvent) event);
				break;
			case ElevatorKeepMovingEvent.NAME:
				this.handleElevatorKeepMovingEvent((ElevatorKeepMovingEvent) event);
				break;
		}
	}
	
	/**
	 * Elevator implementation for the handling of ElevatorDirectionLampEvent
	 * @param elevatorDirectionLampEvent the event modeling the on/off of a direction lamp
	 */
	public void handleElevatorDirectionLampEvent(ElevatorDirectionLampEvent elevatorDirectionLampEvent) {
		this.state = this.state.handleElevatorDirectionLampEvent(elevatorDirectionLampEvent);
	}
	
	/**
	 * Elevator implementation for the handling of ElevatorPressButtonEvent
	 * @param elevatorPressButtonEvent the event modeling the press of a button
	 */
	public void handleElevatorPressButtonEvent(ElevatorPressButtonEvent elevatorPressButtonEvent) {
		this.state = this.state.handleElevatorPressButtonEvent(elevatorPressButtonEvent);
		int recipient = elevatorPressButtonEvent.getRecipient();
		int sender = elevatorPressButtonEvent.getSender();
		int button = elevatorPressButtonEvent.getButton();
		ElevatorPressedButtonEvent event = new ElevatorPressedButtonEvent(sender, recipient, button);
		rpcSender.sendEvent(event);
	}	

	/**
	 * Elevator implementation for the handling of ElevatorCloseDoorEvent
	 * @param elevatorCloseDoorEvent the event modeling the closing of a door
	 */
	public void handleElevatorCloseDoorEvent(ElevatorCloseDoorEvent elevatorCloseDoorEvent) {
		this.state = this.state.handleElevatorCloseDoorEvent(elevatorCloseDoorEvent);
		//Sleep to simulate door closing
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.state = this.state.handleElevatorCloseDoorEvent(elevatorCloseDoorEvent);
		int recipient = elevatorCloseDoorEvent.getRecipient();
		int sender = elevatorCloseDoorEvent.getSender();
		ElevatorClosedDoorEvent event = new ElevatorClosedDoorEvent(sender, recipient);
		rpcSender.sendEvent(event);
	}
	
	/**
	 * Elevator implementation for the handling of ElevatorOpenDoorEvent
	 * @param elevatorOpenDoorEvent the event modeling the opening of a door
	 */
	public void handleElevatorOpenDoorEvent(ElevatorOpenDoorEvent elevatorOpenDoorEvent) {
		this.state = this.state.handleElevatorOpenDoorEvent(elevatorOpenDoorEvent);
		// sleep to simulate door opening
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.state = this.state.handleElevatorOpenDoorEvent(elevatorOpenDoorEvent);
		
		int recipient = elevatorOpenDoorEvent.getRecipient();
		int sender = elevatorOpenDoorEvent.getSender();
		
		ElevatorOpenedDoorEvent event = new ElevatorOpenedDoorEvent(sender, recipient);
		rpcSender.sendEvent(event);
	}
	
	/**
	 * Elevator implementation for the handling of ElevatorStartMovingEvent
	 * @param elevatorStartMovingEvent the event modeling the start moving
	 */
	public void handleElevatorStartMovingEvent(ElevatorStartMovingEvent elevatorStartMovingEvent) {
		this.state = this.state.handleElevatorStartMovingEvent(elevatorStartMovingEvent);
		int recipient = elevatorStartMovingEvent.getRecipient();
		int sender = elevatorStartMovingEvent.getSender();
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int arrivingFloor = this.state.getCurrentFloor() + getFloorIncrement(elevatorStartMovingEvent.getDirection());
		this.state.setCurrentFloor(arrivingFloor);
		
		ElevatorArrivalSensorEvent event = new ElevatorArrivalSensorEvent(sender, recipient, arrivingFloor);
		rpcSender.sendEvent(event);
	}
	
	/**
	 * Elevator implementation for the handling of ElevatorKeepMovingEvent
	 * @param elevatorKeepMovingEvent the event modeling the continued moving 
	 */
	public void handleElevatorKeepMovingEvent(ElevatorKeepMovingEvent elevatorKeepMovingEvent) {
		this.state = this.state.handleElevatorKeepMovingEvent(elevatorKeepMovingEvent);
		int recipient = elevatorKeepMovingEvent.getRecipient();
		int sender = elevatorKeepMovingEvent.getSender();
		
		int arrivingFloor = this.state.getCurrentFloor() + getFloorIncrement(elevatorKeepMovingEvent.getDirection());
		this.state.setCurrentFloor(arrivingFloor);
		
		ElevatorArrivalSensorEvent event = new ElevatorArrivalSensorEvent(sender, recipient, this.state.getCurrentFloor());
		rpcSender.sendEvent(event);
	}
	
	/**
	 * Elevator implementation for the handling of ElevatorStopMovingEvent
	 * @param elevatorStopMovingEvent the event modeling the stop moving
	 */
	public void handleElevatorStopMovingEvent(ElevatorStopMovingEvent elevatorStopMovingEvent) {
		this.state = this.state.handleElevatorStopMovingEvent(elevatorStopMovingEvent);
		int recipient = elevatorStopMovingEvent.getRecipient();
		int sender = elevatorStopMovingEvent.getSender();
		this.state.setButtonLamp(this.state.currentFloor, LampState.OFF);
		
		ElevatorStoppedEvent event = new ElevatorStoppedEvent(sender, recipient);
		rpcSender.sendEvent(event);
	}
	
	/**
	 * Handles ElevatorButtonLampEvents
	 * @param elevatorButtonLampEvent the event modeling the button lamp state change
	 */
	public void handleElevatorButtonLampEvent(ElevatorButtonLampEvent elevatorButtonLampEvent) {
		this.state = this.state.handleElevatorButtonLampEvent(elevatorButtonLampEvent);
	}
	
	/**
	 * @return the floor increment given a direction
	 */
	public static int getFloorIncrement(Direction direction) {
		switch(direction) {
			case UP:
				return 1;
			case DOWN:
				return -1;
			default:
				return 0;
		}
	}
	
	/**
	 * @return state of the elevator
	 */
	public ElevatorState getState() {
		return state;
	}


	@Override
	public String toString() {
		return state.toString();
	}
	
	
	/**
	 * The method to run as in a Thread. Runs forever as it waits for incoming event
	 */
	public void run() {
		Event event = rpcSender.receiveElevatorEvent(id);
		ThreadPrinter.print("\nElevator recieved: " + event);
		handleElevatorEvent(event);
		ThreadPrinter.print("Elevator State:" + this + "\n");
	}
}
