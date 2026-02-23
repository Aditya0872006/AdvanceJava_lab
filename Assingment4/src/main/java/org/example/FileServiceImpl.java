package org.example;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileServiceImpl extends UnicastRemoteObject implements FileService {

    protected FileServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String readFile(String filePath) throws RemoteException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            return "Error reading file: " + e.getMessage();
        }

        return content.toString();
    }
}