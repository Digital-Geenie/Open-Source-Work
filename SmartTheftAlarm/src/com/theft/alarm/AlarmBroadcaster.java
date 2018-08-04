package com.theft.alarm;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

import com.theft.obd.detector.PropertyFileReader;

public class AlarmBroadcaster {

	public void sendMessage() throws IOException {

		MulticastSocket mSocket = new MulticastSocket(PropertyFileReader.PORT);
		mSocket.setNetworkInterface(NetworkInterface.getByName(PropertyFileReader.NETWORK_NAME));
		mSocket.setBroadcast(true);
		String msg = "Accident ahead.. Drive slow";
		DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length,
				InetAddress.getByName(PropertyFileReader.GROUP), PropertyFileReader.PORT);
		mSocket.send(msgPacket);
		mSocket.close();
		System.out.println("Server sent packet with msg: " + msg);
	}
}
