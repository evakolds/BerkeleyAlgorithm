package server;

import static common.AppConstants.formatter;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;

import common.AppConstants;

public class ClockServer1 {

	public static void main(String[] args) {
		try {
			ClockServer clockServer1 = new ClockServerImpl(LocalTime.parse("13:40:00", formatter));
			Registry registry1 = LocateRegistry.createRegistry(AppConstants.SERVER_PORT_1);
			registry1.rebind(ClockServerImpl.class.getSimpleName(), clockServer1);
			System.out.println(String.format("Server 1 is running on port %s", AppConstants.SERVER_PORT_1));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
