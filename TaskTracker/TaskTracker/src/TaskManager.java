import java.util.LinkedList;
import java.util.List;

public class TaskManager {

    private static final String FILE_NAME = "tasks.json";

    private LinkedList<Task> tasks;

    public TaskManager() {
        this.tasks = new LinkedList<>();
    }

    // methods
    public void addTask(String description) { // add task
        int id = tasks.size() + 1;
        tasks.add( new Task(id, description) );
    }

    public void listTasks() {
        System.out.println("Tasks:\n");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.addTask("hello");
        manager.addTask("get groceries");

        manager.listTasks();
    }

}