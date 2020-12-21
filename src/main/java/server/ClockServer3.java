package server;

import static common.AppConstants.formatter;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;

import common.AppConstants;

public class ClockServer3 {

	public static void main(String[] args) {
		try {
			ClockServer clockServer3 = new ClockServerImpl(LocalTime.parse("13:50:00", formatter));
			Registry registry3 = LocateRegistry.createRegistry(AppConstants.SERVER_PORT_3);
			registry3.rebind(ClockServerImpl.class.getSimpleName(), clockServer3);
			System.out.println(String.format("Server 3 is running on port %s", AppConstants.SERVER_PORT_3));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
