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
        System.out.println("Success, task added! (id: "+id+")\n");
    }

    public void updateTaskDescription(int id, String description) { // add description by id
        Task task = findTaskById(id);
        if (task == null) {
            System.out.println("Error: couldn't find id.");
        } else {
            task.setDescription(description);
            System.out.println("Success, description updated!\n");
        }
    }
    
    public void deleteTask(int id) { // delete by id
        Task task = findTaskById(id);
        if (task == null) {
            System.out.println("Error: couldn't find id.");
        } else {
            tasks.remove(task);
            System.out.println("Success, task deleted!\n");
            this.updateId();
        }
    }
    public void clearTasks() { // clear all tasks
        tasks.clear();
        System.out.println("Success, cleared tasks!\n");
        this.updateId();
    }

    public void markStatus(String markStatus, int id) {
        Task task = findTaskById(id);
        switch (markStatus) {
            case "mark-todo":
                task.setStatus("todo");
                break;
            case "mark-done":
                task.setStatus("done");
                break;
            case "mark-in-progress":
                task.setStatus("in-progress");
                break;
            default:
                System.err.println("Something went wrong with setting status.");
                break;   
        }
        System.out.println("Success, marked "+id+" as "+task.getStatus()+"!\n");
    }
    
    public Task findTaskById(int id) { // find task by id
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
    public void updateId() {
        int newId = 1;
        for (Task task : tasks) {
            task.setId(newId);
            newId++;
        }
    }

    public String label = "id   status   description   updated   created"; //label placed above data
    public void listTasks() { // list tasks
        System.out.println("---Tasks\n"+label+"\n");
        for (Task task : tasks) {
            System.out.println(task);
        }
        System.out.println("\n");
    }
    public void listTasksByStatus(String status) { // list tasks

        System.out.println("---Tasks by status: "+status+"\n"+label+"\n");
        for (Task task : tasks) {
            if (task.getStatus() == status) {
                System.out.println(task);
            }
        }
        System.out.println("\n");
    }

    /* public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.addTask("hello");
        manager.addTask("get groceries");
        manager.addTask("third one");

    } */

}