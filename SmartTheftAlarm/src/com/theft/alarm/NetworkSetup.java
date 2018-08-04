package com.theft.alarm;

import static com.theft.constants.Constants.START_SCRIPT_HOSTAPD;
import static com.theft.constants.Constants.START_SCRIPT_UDHCPD;
import static com.theft.constants.Constants.STOP_SCRIPT_HOSTAPD;
import static com.theft.constants.Constants.STOP_SCRIPT_UDHCPD;

public class NetworkSetup {

	public void createAdhocNetwork() {

		Process p1, p2;
		try {
			p1 = Runtime.getRuntime().exec(START_SCRIPT_HOSTAPD);
			p1.waitFor();
			p1.destroy();
			
			p2 = Runtime.getRuntime().exec(START_SCRIPT_UDHCPD);
			p2.waitFor();
			p2.destroy();
		} catch (Exception e) {

		}

	}

	public void destroyAdhocNetwork() {

		Process p1, p2;
		try {
			System.out.println("Stopping hostapad");

			// p1 =
			// Runtime.getRuntime().exec(PropertyFileReader.STOP_SCRIPT_HOSTAPD);
			p1 = Runtime.getRuntime().exec(STOP_SCRIPT_HOSTAPD);
			p1.waitFor();
			p1.destroy();

			p2 = Runtime.getRuntime().exec(STOP_SCRIPT_UDHCPD);
			p2.waitFor();
			p2.destroy();
		} catch (Exception e) {

		}
	}

}
