import java.util.Scanner;

public class TaskCLI {

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        int argc = args.length;
        String syntax = "Syntax: task-cli ... ...\nadd \"description\"                -- add a task, with a description\nupdate id \"description\"          -- update an already existing task, id can be found with list command\ndelete id                        -- delete a task\n\nmark-in-progress id              -- mark a task to in-progress\nmark-done id                     -- mark a task as done\nmark-todo id                     -- mark a task as todo\n\nlist                             -- lists  {id  status  description  updatedTime creationTime}\nlist in-progress                 -- lists in-progress only\nlist todo                        -- lists todo only\nlist in-progress                 -- lists in-progress only";
        if (argc == 0) {
            System.out.println(syntax);
            return;
        }

        Scanner scan = new Scanner(System.in);

        boolean stop = true;
        String command = args[0];
        if (command.equals("session")) {
            stop = false;
        }
        String attribute1 = null;
        int attribute1id = -1;
        String attribute2 = null;
        while (stop == false) {
            
            if (stop == false) {
                attribute1 = (argc > 1) ? args[1] : null;
                attribute2 = (argc > 2) ? args[2] : null;
                if (attribute1 != null) {
                    try {
                        attribute1id = Integer.parseInt(attribute1); // Convert to integer if needed
                    } catch (NumberFormatException e) {
                        //System.err.println("Error: Attribute1 is not a valid number: " + attribute1); 
                    }
                }
            } else {
                System.out.print("task-cli ");
                if (scan.hasNext()) {
                    if (scan.hasNext("[A-Za-z]+")) { // set attribute1
                        attribute1 = scan.next();
                    } else if (scan.hasNext("[0-9]+")) { // set attribute1 id
                        attribute1id = scan.nextInt();
                        if (scan.hasNext("[A-Za-z]+")) { // set attribute 2
                            attribute2 = scan.next();
                        }
                    }
                } else { // no args
                    System.out.println(syntax);
                }
            }
        



            switch (command) {
                case "exit": { // quits session.
                    if (attribute1 != null) {
                        stop = true;
                        break;
                    }
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
                    if (argc > 1) {
                        manager.listTasksByStatus(attribute1);
                    } else {
                        manager.listTasks();
                    }
                    break;
                default:
                    System.out.println("unknown error occured in TaskCLI switch statement\n\n"+syntax);
                    break;
            }

            manager.saveTasks();
        }
        scan.close();
    }
}