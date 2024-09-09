import java.time.LocalDateTime;

public class Task {

    //fields
    private int id;
    private String description; // "grocery list"
    private String status; // "done, todo, in-progress"
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //constructor
    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.status = "todo";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    //getters
    public int getId() {return id;}
    public String getDescription() {return description;}
    public String getStatus() {return status;}
    public LocalDateTime getCreatedAt() {return createdAt;}
    public LocalDateTime getUpdatedAt() {return updatedAt;}

    //setters
    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }
    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    // list display
    public static String label() {
        return "id   status   description   updated   created\n\n"; //label placed above data
    }

    public String toShortSymbol() {
        switch (status) {
            case "todo":
                return "X";
            case "done":
                return "V";
            case "progress":
                return ">";
            default:
                return "?";
        }
    }

    @Override
    public String toString() { // returns human readable string
        return id+"    "+toShortSymbol()+"  "+description+"    "+updatedAt+"  "+createdAt;
    }

}