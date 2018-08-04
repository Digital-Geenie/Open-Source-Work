package com.theft.listener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class MReceiver {

	public static void main(String[] args) throws IOException, InterruptedException {

		int port = 5000;
		String group = "225.4.5.6";
		byte buf[] = new byte[1024];
		DatagramPacket pack = new DatagramPacket(buf, buf.length);
		VoiceAlert valert = new VoiceAlert();
		valert.init();
		MulticastSocket mSocket = new MulticastSocket(port);
		boolean receiverflag = false;
		while (true) {

			try {

				mSocket.setNetworkInterface(NetworkInterface.getByName("wlan0"));
				mSocket.setBroadcast(true);
				mSocket.joinGroup(InetAddress.getByName(group));
				System.out.println("Joined Successfully");
				receiverflag = true;
			} catch (Exception e) {
				receiverflag = false;

			}

			while (receiverflag) {

				try {
					mSocket.receive(pack);
					System.out.println("Received data from: " + pack.getAddress().toString() + ":" + pack.getPort()
							+ " with length: " + pack.getLength());
					System.out.write(pack.getData(), 0, pack.getLength());

					valert.playText("Theft detected");
					System.out.println();
					// Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}

}