package org.example.EchoServerImplement;
import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(host, port)) {

            BufferedReader userInput = new BufferedReader(
                    new InputStreamReader(System.in));

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            String message;

            System.out.println("Enter message:");

            while ((message = userInput.readLine()) != null) {
                out.println(message);
                System.out.println("Server replied: " + in.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
