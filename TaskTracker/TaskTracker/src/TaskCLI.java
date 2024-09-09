public class TaskCLI {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        if (args == 0) {
            System.out.println("Syntax: task-cli ... ...\nadd \"description\"\nupdate id \"description\"\ndelete id\n\nmark-in-progress id\nmark-done id\nmark-todo id\n\nlist --: listing shows the ids\nlist in-progress\nlist todo\nlist in-progress");
            return;
        }
    }
}