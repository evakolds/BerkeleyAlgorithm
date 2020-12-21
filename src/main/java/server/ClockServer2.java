package server;

import static common.AppConstants.formatter;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;

import common.AppConstants;

public class ClockServer2 {

	public static void main(String[] args) {
		try {
			ClockServer clockServer2 = new ClockServerImpl(LocalTime.parse("13:45:00", formatter));
			Registry registry2 = LocateRegistry.createRegistry(AppConstants.SERVER_PORT_2);
			registry2.rebind(ClockServerImpl.class.getSimpleName(), clockServer2);
			System.out.println(String.format("Server 2 is running on port %s", AppConstants.SERVER_PORT_2));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
