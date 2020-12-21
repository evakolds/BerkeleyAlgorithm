package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;

import common.AppConstants;


public class ClockServerImpl extends UnicastRemoteObject implements ClockServer {

	private static final long serialVersionUID = -6810169856453308607L;
	private LocalTime time;

	public ClockServerImpl(LocalTime time) throws RemoteException {
		this.time = time;
	}

	@Override
	public LocalTime getTime() {
		return time;
	}

	@Override
	public void adjustTime(LocalTime localTime, long diffNanos) {
		long timeLocalNanos = localTime.toNanoOfDay();
		long thisNanos = this.getTime().toNanoOfDay();
		long newNanos = thisNanos - timeLocalNanos;
		newNanos = newNanos * -1 + diffNanos + thisNanos;
		LocalTime newLocalTime = LocalTime.ofNanoOfDay(newNanos);
		System.out.println("Updated time: " + AppConstants.formatter.format(newLocalTime));
		this.time = newLocalTime;
	}
}