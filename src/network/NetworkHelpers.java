package network;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * A class for useful static network functions
 *
 */
public class NetworkHelpers {
	
	
	/**
	 * @return the local ip of this machine
	 */
	public static InetAddress getLocalIp() {
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return address;
	}
	
	
}
