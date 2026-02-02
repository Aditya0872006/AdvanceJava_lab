package org.example;

import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        try {
            // Connect to server
            Socket socket = new Socket("localhost", 5000);

            System.out.println("Connected to server");

            // Input stream from server
            DataInputStream dis =
                    new DataInputStream(socket.getInputStream());

            // File to save
            FileOutputStream fos =
                    new FileOutputStream("received.txt");

            byte[] buffer = new byte[4096];
            int bytesRead;

            // Receive file
            while ((bytesRead = dis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            System.out.println("File received successfully");

            fos.close();
            dis.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
