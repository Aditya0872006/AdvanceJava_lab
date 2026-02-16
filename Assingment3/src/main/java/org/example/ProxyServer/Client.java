package org.example.ProxyServer;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 8000);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);

        out.println("Hello Server, this is Client");

        String response = in.readLine();
        System.out.println("Client received: " + response);

        socket.close();
    }
}
