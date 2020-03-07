package network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import event.Event;

/**
 * A class for serializing Events
 *
 */

public class EventSerializer {
	
	/**
	 * deserializes an event
	 * @param bytes the bytes to get an Event from
	 * @return an Event from bytes
	 */
	public static Event deserialize(byte[] bytes) {
		ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
		ObjectInputStream deserializer = null;
		
		try {
			deserializer = new ObjectInputStream(byteStream);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		Event event = null;
		try {
			event = (Event) deserializer.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return event;
	}
	
	
	/**
	 * Converts an Event to a byte array
	 * @param event event to convert
	 * @return the event as a byte array
	 */
	public static byte[] serialize(Event event) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ObjectOutputStream serializer = null;
		
		try {
			serializer = new ObjectOutputStream(byteStream);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		try {
			serializer.writeObject(event);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return byteStream.toByteArray();
	}
}
