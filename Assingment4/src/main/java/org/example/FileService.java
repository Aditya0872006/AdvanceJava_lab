package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileService extends Remote {
    String readFile(String filePath) throws RemoteException;
}