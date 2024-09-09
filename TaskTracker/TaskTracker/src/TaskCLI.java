public class TaskCLI {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        argc = args.length;
        if (argc == 0) {
            System.out.println("Syntax: task-cli ... ...\nadd \"description\"\nupdate id \"description\"\ndelete id\n\nmark-in-progress id\nmark-done id\nmark-todo id\n\nlist --: listing shows the ids\nlist in-progress\nlist todo\nlist in-progress");
            return;
        }

        String command = args[0];
        String attribute1;
        int attribute1id;
        String attribute2;

        if (argc > 1) { // prevent touching memory that isn't that there.
            attribute1 = args[1]; 
            try {
                attribute1id = Integer.parseInt(attribute1); // Convert to integer if needed
            } catch (NumberFormatException e) {
                System.out.println("Error: Attribute1 is not a valid number: " + attribute1);
            }
        } if (argc > 2) {
            attribute2 = args[2];
        }

        switch (command) {
            case "add": // add update delete
                manager.addTask(attribute1);                
                break;
            case "update":
                manager.updateTaskDescription(attribute1id, attribute2);
                break;
            case "delete":
                manager.deleteTask(attribute1id);
                break;


            case "mark-inprogress": // marking
            case "mark-done":
            case "mark-todo":
                manager.markStatus(command, attribute1id);                
                break;


            case "list": // listing
                if (argc > 1) {
                    manager.listTasksByStatus(attribute1);
                } else {
                    manager.listTasks();
                }
                break;
            default:
                System.out.println("unknown error occured in TaskCLI switch statement");
                break;
        }
    }
}