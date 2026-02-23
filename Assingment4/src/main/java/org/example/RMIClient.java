package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) {
        try {

            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            FileService stub = (FileService) registry.lookup("FileService");

            String filePath = "sample.txt";
            String result = stub.readFile(filePath);

            System.out.println("File Content:");
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}