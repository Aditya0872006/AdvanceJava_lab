package org.example;

// FileServer.java
import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        try {
            // Create server socket
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server waiting for client...");

            // Accept client
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            // File to send
            File file = new File("data.txt");
            FileInputStream fis = new FileInputStream(file);

            // Output stream to client
            DataOutputStream dos =
                    new DataOutputStream(socket.getOutputStream());

            byte[] buffer = new byte[4096];
            int bytesRead;

            // Send file
            while ((bytesRead = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent successfully");

            fis.close();
            dos.close();
            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

