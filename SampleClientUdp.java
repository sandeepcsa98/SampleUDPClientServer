package com.example;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class SampleClientUdp {

	public static void main(String[] args) {
        final int SERVER_PORT = 9876;
        
        Scanner sc = new Scanner(System.in);
       
       while(true) {
           try (DatagramSocket clientSocket = new DatagramSocket()) {
               InetAddress serverAddress = InetAddress.getByName("localhost");
               System.out.println("Send some message to server: \n");
               String send = sc.nextLine();
               if(send.equalsIgnoreCase("Quit")) {
            	   break;
               }
               byte[] sendData = send.getBytes();
               DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
               clientSocket.send(sendPacket);

               byte[] receiveData = new byte[1024];
               DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
               clientSocket.receive(receivePacket);

               String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
               System.out.println("Server response: " + serverResponse);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }


    }

}
