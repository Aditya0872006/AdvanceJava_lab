package org.example.EchoServerImplement;
import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Echo Server started on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true);

                String message;

                while ((message = in.readLine()) != null) {
                    System.out.println("Received: " + message);
                    out.println(message);  // Echo back
                }

                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
