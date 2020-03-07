package network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;


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
	
	/**
	 *  
	 * @return input IP address from scanner
	 */
	public static InetAddress getIPFromInput() {
		
		try(Scanner scanner = new Scanner(System.in)){
			System.out.print("EnterIP of the Scheduler: ");
			
			String input = scanner.nextLine();
			
			InetAddress ip = null;
			try {
				ip = InetAddress.getByName(input);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.exit(1);
			}
			return ip;
			
		}
				
	}
	
	
}
