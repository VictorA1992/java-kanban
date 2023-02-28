package model;

public class SubTask extends Task {

    private Integer epicId;

    public SubTask(String name, String description, Status status, Integer epicId) {
        super(name, description, status.NEW);
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return this.epicId;
    }


    public String toString() {

        return "Subtask{" +

                "epicID=" + epicId +

                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                '}';

    }
}