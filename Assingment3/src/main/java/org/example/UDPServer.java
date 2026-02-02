package org.example;

// UDPServer.java
import java.io.*;
import java.net.*;

public class UDPServer {
    private static final int PORT = 9876;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) throws Exception {

        DatagramSocket serverSocket = new DatagramSocket(PORT);
        byte[] buffer = new byte[BUFFER_SIZE];

        System.out.println("UDP Server started...");

        // Receive file name from client
        DatagramPacket request =
                new DatagramPacket(buffer, buffer.length);
        serverSocket.receive(request);

        String fileName = new String(request.getData(), 0, request.getLength());
        System.out.println("Client requested file: " + fileName);

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File not found");
            serverSocket.close();
            return;
        }

        FileInputStream fis = new FileInputStream(file);

        InetAddress clientAddress = request.getAddress();
        int clientPort = request.getPort();

        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            DatagramPacket sendPacket =
                    new DatagramPacket(buffer, bytesRead, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        }

        // Send EOF message
        byte[] eof = "EOF".getBytes();
        serverSocket.send(new DatagramPacket(eof, eof.length, clientAddress, clientPort));

        fis.close();
        serverSocket.close();
        System.out.println("File sent successfully.");
    }
}
