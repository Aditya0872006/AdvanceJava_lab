import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class FileSimulation {

    // Base folder where all "machines" will be simulated
    static String basePath = "FileSystemSimulation/";

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // 1. Take input from keyboard
        System.out.print("Enter data to write into file: ");
        String input = sc.nextLine();

        // 2. Write input to main file
        String mainFile = basePath + "MainMachine/data.txt";
        writeToFile(mainFile, input);

        // 3. Copy file to other machines
        copyFile(mainFile, basePath + "Machine1/data.txt");
        copyFile(mainFile, basePath + "Machine2/data.txt");
        copyFile(mainFile, basePath + "Machine3/data.txt");

        System.out.println("\nFile copied to all machines.");

        // 4. Update file on ONE specified machine
        String machineToUpdate = basePath + "Machine2/data.txt";
        System.out.print("\nEnter updated content for Machine2: ");
        String updatedData = sc.nextLine();
        writeToFile(machineToUpdate, updatedData);

        // 5. Show updated file on YOUR machine
        System.out.println("\nUpdated file content from Machine2:");
        readFile(machineToUpdate);

        sc.close();
    }

    // ------------------- Helper Methods -------------------

    // Write to file (creates folders if missing)
    static void writeToFile(String path, String data) throws IOException {
        ensureFolderExists(path); // create folders if not exist
        FileWriter fw = new FileWriter(path);
        fw.write(data);
        fw.close();
    }

    // Ensure folder exists
    static void ensureFolderExists(String filePath) {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs(); // create all missing directories
        }
    }

    // Copy file (overwrite if exists)
    static void copyFile(String src, String dest) throws IOException {
        ensureFolderExists(dest); // create dest folders if missing
        Files.copy(Paths.get(src), Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
    }

    // Read file and print
    static void readFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }
}
