import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class TaskJsonHandler {

    private static final String FILE_NAME = "tasks.json";

    public boolean CheckForFile(String FILE_NAME) { // check and create file

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


    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(FILE_NAME))) {
            
            StringBuilder jsonBuilder = new StringBuilder(); // stores buffer into a string
            String line;
            while ((line = bf.readLine()) != null) {
                jsonBuilder.append(line);
            }
            System.out.println(jsonBuilder.toString());

        } 
        
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error: "+e);
        }

        return tasks;
    }


    public static void main(String[] args) {

        TaskJsonHandler handler = new TaskJsonHandler();
        handler.loadTasksFromFile();
    }
}