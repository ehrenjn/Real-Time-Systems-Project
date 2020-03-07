package event;

import java.io.*;
import java.net.*;

public class EventSerializer {
	
	public static void main(String[] args) throws Exception {
		DatagramSocket ds = new DatagramSocket(3000);
		byte[] buf = new byte[1024];
		
		DatagramPacket dP = new DatagramPacket(buf, 1024);
		ds.receive(dP);
		System.out.println("Datagram recieved.");
		ByteArrayInputStream byteIn = null;
		byteIn = new ByteArrayInputStream(dP.getData());

		ObjectInputStream in = new ObjectInputStream(byteIn);
		FloorButtonEvent recEvent = (FloorButtonEvent)in.readObject();
		in.close();
		System.out.println("Event deserialized.");

		System.out.println(recEvent);
		System.out.println(recEvent.getFloor());
	}

}
