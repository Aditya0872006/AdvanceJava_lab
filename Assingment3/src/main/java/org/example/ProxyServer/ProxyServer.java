package org.example.ProxyServer;

import java.io.*;
import java.net.*;

public class ProxyServer {

    public static void main(String[] args) throws Exception {

        ServerSocket proxySocket = new ServerSocket(8000);
        System.out.println("Proxy Server running on port 8000...");

        while (true) {
            Socket clientSocket = proxySocket.accept();
            new Thread(() -> handleClient(clientSocket)).start();
        }
    }

    public static void handleClient(Socket clientSocket) {
        try {
            BufferedReader clientIn = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter clientOut = new PrintWriter(
                    clientSocket.getOutputStream(), true);

            // Connect to backend server
            Socket serverSocket = new Socket("localhost", 9000);

            BufferedReader serverIn = new BufferedReader(
                    new InputStreamReader(serverSocket.getInputStream()));
            PrintWriter serverOut = new PrintWriter(
                    serverSocket.getOutputStream(), true);

            // Receive from client
            String clientMessage = clientIn.readLine();
            System.out.println("Proxy received from Client: " + clientMessage);

            // Send to backend server
            serverOut.println(clientMessage);

            // Receive response from server
            String serverResponse = serverIn.readLine();
            System.out.println("Proxy received from Server: " + serverResponse);

            // Send back to client
            clientOut.println(serverResponse);

            serverSocket.close();
            clientSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

