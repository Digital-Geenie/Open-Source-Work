# README #

* Repository for Smart Theft Alarm open source project
* 1.0

Demo :
https://youtu.be/LGfPAdpLG1Y


INSTRUCTIONS TO RUN :

Hardware Requirements : 1. Raspberry Pi with Raspbian OS installed. 2. Bluetooth OBD 2 Scanner ELM237 3. Computer/Laptop/Raspberry Pi to run the alarmListener

Software requirements : 1. Raspbian OS on raspberry pi 2. JRE 1.8 3. Bluecove lib for bluetooth communication (attached with source) 4. OBD-Java api for polling OBD 2 adapter(bluetooth) 5. Text to Speech api for voice alarms.

Installation steps : Raspberry Pi (SmartTheftAlarm code) 1. Configure the raspberry device for creating wireless hotspot(open network) using the following link : http://elinux.org/RPI-Wireless-Hotspot 2. Import SmartTheftAlarm source code and build a jar after adding all the provided libraries. 3. Create a folder /home/pi/SmartTheftAlarm and add the config.properties, startTheftAlarm.sh and SmartTheftAlarm_{version} jar in this folder. Update the name respectively in startTheftAlarm.sh 4. Allow permission for startTheftAlarm.sh by using chmod command 5. Run "crontab -e" and update "reboot startTheftAlarm.sh" to run this jar without human intervention.

Note : If the solution is unable to connect to bluetooth OBD update the source code in TheftDetector.java. In this situation comment out the listener.scan() and conn = listener.connectToDevice(listener.getUrl()); and replace it with conn = listener.connectToDevice(PropertyFileReader.URL); Update the url in config.properties with url of OBD2 device

Installation Steps : AlarmListener 1. Import the source code. 2. Run the ListNets java file to fetch the network interface names of your machine. 3. Update the name of the wireless interface name in the MReceiver.java 4. Build and Run the jar

