import java.util.Scanner;

public class TaskCLI {
    
    static final String syntax = "Syntax: task-cli ... ...\nadd \"description\"                -- add a task, with a description\nupdate id \"description\"          -- update an already existing task, id can be found with list command\ndelete id                        -- delete a task\nclear-all                        -- deletes all tasks at once\n\nmark-in-progress id              -- mark a task to in-progress\nmark-done id                     -- mark a task as done\nmark-todo id                     -- mark a task as todo\n\nlist                             -- lists  {id  status  description  updatedTime creationTime}\nlist in-progress                 -- lists in-progress only\nlist todo                        -- lists todo only\nlist done                        -- lists done only\n\nsession                          -- start a session, keeps the program running";

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println(syntax);
            return;
        }

        boolean stop = false;
        String command = args[0].toLowerCase();
        String attribute1 = (args.length > 1) ? args[1].toLowerCase() : null;
        String attribute2 = (args.length > 2) ? args[2].toLowerCase() : null;

        if (command.equals("session")) {
            Scanner scan = new Scanner(System.in);
            TaskManager manager = new TaskManager(true);

            while (!stop) {

                System.out.print("\ntask-cli> ");
                String inputLine = scan.nextLine();
                String[] inputArgs = inputLine.split(" ");
                command = inputArgs[0].toLowerCase();
                attribute1 = (inputArgs.length > 1) ? inputArgs[1].toLowerCase() : null;
                attribute2 = (inputArgs.length > 2) ? inputArgs[2].toLowerCase() : null;

                clearConsole(); // displays list
                if (command.equals("list")) {
                    stop = processCommand(manager, command, attribute1, attribute2);
                } else {
                    stop = processCommand(manager, command, attribute1, attribute2);
                    System.err.println("");
                    processCommand(manager, "list", attribute1, attribute2);
                }
            }
            scan.close();
        } else {
            TaskManager manager = new TaskManager(true);

            stop = processCommand(manager, command, attribute1, attribute2);
        }
    }


    private static boolean processCommand(TaskManager manager, String command, String attribute1, String attribute2) {
        int attribute1id = -1;
        if (attribute1 != null) {
            try {
                attribute1id = Integer.parseInt(attribute1); // Convert to integer if needed
            } catch (NumberFormatException e) {
                //System.err.println("Error: Attribute1 is not a valid number: " + attribute1); 
            }
        }

        switch (command) {
            case "exit": { // quits session.
                return true;
            }

            case "add": // add update delete
                if (attribute1 != null) {
                    manager.addTask(attribute1);
                } else {
                    System.out.println("Error: Description is required for 'add' command.");
                }
                break;
            case "update":
                if (attribute1id != -1 && attribute2 != null) {
                    manager.updateTaskDescription(attribute1id, attribute2);
                } else {
                    System.out.println("Error: Valid ID and description are required for 'update' command.");
                }
                break;
            case "delete":
                if (attribute1id != -1) {
                    manager.deleteTask(attribute1id);
                } else {
                    System.out.println("Error: Valid ID is required for 'delete' command.");
                }
                break;
            case "clearall":
                manager.clearTasks();
                break;

            case "mark-in-progress": // marking
            case "mark-done":
            case "mark-todo":
                if (attribute1id != -1) {
                    manager.markStatus(command, attribute1id);                
                } else {
                    System.out.println("Error: Valid ID is required for '" + command + "' command.");
                }
                break;


            case "list": // listing
                if (attribute1 != null) {
                    manager.listTasksByStatus(attribute1);
                    System.out.println("got into the list with status");
                } else {
                    System.out.println("got into the list");
                    manager.listTasks();
                    System.out.println("got into the list and did listtasks");
                }
                break;
            default:
                System.out.println("unknown error occured in TaskCLI switch statement\n\n"+syntax);
                break;
        }

        manager.saveTasks();
        return false;
    }
}
