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
        System.out.println("Success!\n");
    }

    public void updateTaskDescription(int id, String description) { // add description by id
        Task task = findTaskById(id);
        if (task == null) {
            System.out.println("Error: couldn't find id.");
        } else {
            task.setDescription(description);
            System.out.println("Success!\n");
        }
    }
    
    public void deleteTask(int id) { // delete by id
        Task task = findTaskById(id);
        if (task == null) {
            System.out.println("Error: couldn't find id.");
        } else {
            tasks.remove(task);
            System.out.println("Success!\n");
        }
    }
    public void clearTasks() {
        for (Task task : tasks) {
            tasks.remove(task);
            System.out.println("Success!\n");
        }
    }

    
    public Task findTaskById(int id) { // find task by id
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public String linebreakStart = "--------\\";
    public String linebreakEnd = "--------/";
    public String label = "id   status   description   updated   created"; //label placed above data
    public void listTasks() { // list tasks
        System.out.println(linebreakStart+" Tasks\n"+label+"\n");
        for (Task task : tasks) {
            System.out.println(task);
        }
        System.out.println("\n"+linebreakEnd);
    }


    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.addTask("hello");
        manager.addTask("get groceries");

        manager.listTasks();
    }

}