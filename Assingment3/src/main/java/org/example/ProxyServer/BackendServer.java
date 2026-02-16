package org.example.ProxyServer;

import java.io.*;
import java.net.*;

public class BackendServer {
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("Backend Server running on port 9000...");

        while (true) {
            Socket socket = serverSocket.accept();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String message = in.readLine();
            System.out.println("Server received: " + message);

            out.println("Response from Backend Server");

            socket.close();
        }
    }
}
