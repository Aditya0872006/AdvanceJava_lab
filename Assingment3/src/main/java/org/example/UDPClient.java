package org.example;

// UDPClient.java
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    private static final int PORT = 9876;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) throws Exception {

        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("SERVER_IP"); // change IP

        byte[] buffer = new byte[BUFFER_SIZE];
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter file name: ");
        String fileName = sc.nextLine();

        // Send file request
        DatagramPacket request =
                new DatagramPacket(fileName.getBytes(), fileName.length(),
                        serverAddress, PORT);
        clientSocket.send(request);

        FileOutputStream fos = new FileOutputStream("received_" + fileName);

        while (true) {
            DatagramPacket receivePacket =
                    new DatagramPacket(buffer, buffer.length);
            clientSocket.receive(receivePacket);

            String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());
            if (msg.equals("EOF")) {
                break;
            }

            fos.write(receivePacket.getData(), 0, receivePacket.getLength());
        }

        fos.close();
        clientSocket.close();
        System.out.println("File received successfully.");
    }
}

