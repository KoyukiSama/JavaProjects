import java.io.*;
import java.util.ArrayList;


public class TaskFileHandler {

    private static final String FILE_NAME = "tasks.dat";

    public static boolean CheckForFile() { // check and create file
        File file = new File(FILE_NAME);
        if (file.exists()) {
            return true;
        } else {
            try {
                if (file.createNewFile()) {
                    System.out.println("Created new file: "+file.getName());
                } else {
                    System.out.println("Failed to created file");
                }
            } catch (IOException e) {
                System.out.println("Failed to create file\nError: "+e);
            }
            return false;
        }
    }


    public static void saveTasksToFile(ArrayList<Task> tasks) {  //serialise objects into file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(tasks);
            System.out.println("Tasks successfully saved!");
        } catch (IOException e) {
            System.out.println("Error while saving tasks to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Task> loadTasksFromFile() {
        File file = new File(FILE_NAME);
        if (file.length() == 0) {
            System.out.println("File is empty. No tasks to load.");
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while loading tasks from file: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}