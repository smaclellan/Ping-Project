package PingProjectPackage;

import java.io.*;
import java.net.*;
import java.util.*;


public class PingClient
{
	private static final int MAX_TIMEOUT = 1000;	// milliseconds

	public static void main(String[] args) throws Exception
	{
		InetAddress IPAddress = InetAddress.getByName("localhost");
		
		DatagramSocket clientSocket = new DatagramSocket();
			
		
		byte[] sendData = new byte[1024]; 
		int packets = 1;
		
		while(packets<11)
		 {
			
			Date now = new Date();
			long msSend = now.getTime();
			
			Random random = new Random();
			String sentence = "Ping " + packets + "\n";
			sendData = sentence.getBytes();		
			DatagramPacket sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress,
					 2014);
			clientSocket.send(sendPacket); 
			
			try{	
			clientSocket.setSoTimeout(MAX_TIMEOUT);
			
			
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			String message = new String(receivePacket.getData(),0, receivePacket.getLength());
			now = new Date();
			long msReceived = now.getTime();
			double time = msReceived - msSend;
			
			
			int rand =random.nextInt(10);//a random number in the range of 0 to10
			 // if rand is less than 4 we consider the packet lost and do not reply
			 if (rand < 4) {
			 System.out.println("Packets lost    " + time + "ms");
			 } 
			 else{
				 
				 System.out.println("server's message = " + message + "       "+ time + "ms"); 
			 }
			}
			catch(IOException e){
				System.out.println("Request times out  " + MAX_TIMEOUT + "ms");
			}
			
			
		 packets++;
		 }//while

	clientSocket.close();	
}


}