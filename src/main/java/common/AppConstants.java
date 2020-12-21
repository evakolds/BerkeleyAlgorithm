package common;

import java.time.format.DateTimeFormatter;

public interface AppConstants {

	String SERVER_NAME = "localhost";

	int SERVER_PORT_1 = 1500;
	int SERVER_PORT_2 = 1501;
	int SERVER_PORT_3 = 1502;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
}
