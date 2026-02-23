package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            FileServiceImpl obj = new FileServiceImpl();

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("FileService", obj);

            System.out.println("Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}