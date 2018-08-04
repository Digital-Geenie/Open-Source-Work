package com.theft.obd.detector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.StreamConnection;

import com.github.pires.obd.commands.SpeedCommand;
import com.github.pires.obd.commands.protocol.EchoOffCommand;
import com.github.pires.obd.commands.protocol.HeadersOffCommand;
import com.github.pires.obd.commands.protocol.LineFeedOffCommand;
import com.github.pires.obd.commands.protocol.SelectProtocolCommand;
import com.github.pires.obd.commands.protocol.TimeoutCommand;
import com.github.pires.obd.enums.ObdProtocols;
import com.theft.alarm.AlarmBroadcaster;
import com.theft.alarm.NetworkSetup;

public class TheftDetector {

	private static OBDListener listener = new OBDListener();
	private static OutputStream out;
	private static InputStream in;
	private static StreamConnection conn = null;

	public static void main(String args[]) throws IOException {

		NetworkSetup adhocNetwork = new NetworkSetup();
		adhocNetwork.destroyAdhocNetwork();
		PropertyFileReader properties = new PropertyFileReader();
		properties.getPropValues();

		do {

			listener.scan();
			conn = listener.connectToDevice(listener.getUrl());
			//conn = listener.connectToDevice(PropertyFileReader.URL);
		} while (conn == null);

		out = conn.openOutputStream();

		in = conn.openInputStream();

		try {
			new EchoOffCommand().run(in, out);
			new LineFeedOffCommand().run(in, out);
			new TimeoutCommand(125).run(in, out);
			new SelectProtocolCommand(ObdProtocols.AUTO).run(in, out);
			new HeadersOffCommand().run(in, out);

			SpeedCommand speedCommand = new SpeedCommand();
			int currentRecordedSpeed = 0;
			AlarmBroadcaster alarmBroadcaster = new AlarmBroadcaster();
			Boolean theftDetected = false;

			while (!Thread.currentThread().isInterrupted()) {
				speedCommand.run(in, out);
				currentRecordedSpeed = speedCommand.getMetricSpeed();
				System.out.println(speedCommand.getMetricSpeed());

				// Checking if speed is positive
				if (currentRecordedSpeed >= 0 && !theftDetected) {
					System.out.println("Theft Detected : Setting up network and broadcasting message");
					adhocNetwork.createAdhocNetwork();
					theftDetected = true;
				}
				alarmBroadcaster.sendMessage();
			}
		} catch (Exception e) {
			System.out.println("Destroying network since exception thrown");
			e.printStackTrace();
			adhocNetwork.destroyAdhocNetwork();
		}

		conn.close();

	}
}
