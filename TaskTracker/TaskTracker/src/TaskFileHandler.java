import java.io.*;
import java.util.ArrayList;


public class TaskFileHandler {

    private static final String FILE_NAME = "tasks.dat";


    public static void saveTasksToFile(ArrayList<Task> tasks) {  //serialise objects into file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(tasks);
            System.out.println("Tasks successfully saved!");
        } catch (IOException e) {
            System.out.println("Error while saving tasks to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static ArrayList<Task> loadTasksFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while loading tasks from file: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}