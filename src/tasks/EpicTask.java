package tasks;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class EpicTask extends Task {

    private final List<Integer> subtasksId = new ArrayList<>();

    public EpicTask(String name, String description) {

        super(name, description, Status.NEW);
    }

    public List<Integer> getSubtasksId() {
        return this.subtasksId;
    }

    public void addSubtaskId(Integer keyId) {
        this.subtasksId.add(keyId);
    }

    @Override

    public String toString() {

        return "tasks.EpicTask{" +

                "subtasksID=" + subtasksId +

                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                '}';

    }
}