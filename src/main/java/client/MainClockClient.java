package client;

import static common.AppConstants.formatter;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;

import common.AppConstants;
import server.ClockServer;
import server.ClockServerImpl;


public class MainClockClient {

	public static void main(String[] args) {
		try {
			LocalTime localTime = LocalTime.parse("13:35:00", AppConstants.formatter);
			System.out.println("Local time: " + formatter.format(localTime));

			Registry registry1 = LocateRegistry.getRegistry(AppConstants.SERVER_NAME, AppConstants.SERVER_PORT_1);
			ClockServer clockServer1 = (ClockServer) registry1.lookup(ClockServerImpl.class.getSimpleName());
			System.out.println("\nConnection to Server 1 successfully established.");
			LocalTime clockServer1Time = clockServer1.getTime();
			System.out.println("Server 1 time: " + formatter.format(clockServer1Time));

			Registry registry2 = LocateRegistry.getRegistry(AppConstants.SERVER_NAME, AppConstants.SERVER_PORT_2);
			ClockServer clockServer2 = (ClockServer) registry2.lookup(ClockServerImpl.class.getSimpleName());
			System.out.println("Connection to Server 2 successfully established.");
			LocalTime clockServer2Time = clockServer2.getTime();
			System.out.println("Server 2 time: " + formatter.format(clockServer2Time));

			Registry registry3 = LocateRegistry.getRegistry(AppConstants.SERVER_NAME, AppConstants.SERVER_PORT_3);
			ClockServer clockServer3 = (ClockServer) registry3.lookup(ClockServerImpl.class.getSimpleName());
			System.out.println("Connection to Server 3 successfully established.");
			LocalTime clockServer3Time = clockServer3.getTime();
			System.out.println("Server 3 time: " + formatter.format(clockServer3Time));

			long nanoLocal = localTime.toNanoOfDay();
			long diffServer1 = clockServer1Time.toNanoOfDay() - nanoLocal;
			long diffServer2 = clockServer2Time.toNanoOfDay() - nanoLocal;
			long diffServer3 = clockServer3Time.toNanoOfDay() - nanoLocal;
			long avgDiff = (diffServer1 + diffServer2 + diffServer3) / 3;

			clockServer1.adjustTime(localTime, avgDiff);
			clockServer2.adjustTime(localTime, avgDiff);
			clockServer3.adjustTime(localTime, avgDiff);
			localTime = localTime.plusNanos(avgDiff);
			System.out.println("\nTime was updated. ");

			System.out.println("Local time: " + formatter.format(localTime));
			System.out.println("Server 1 time: " + formatter.format(clockServer1.getTime()));
			System.out.println("Server 2 time: " + formatter.format(clockServer2.getTime()));
			System.out.println("Server 3 time: " + formatter.format(clockServer3.getTime()));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}