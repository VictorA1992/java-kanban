package LastAndStatus;

import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class LastTasks {
    private static final int TASKS_TO_SAVE = 10;
    private final LinkedList<Task> lastTasks = new LinkedList<>();

    public void addTask(Task task) {
        if (lastTasks.size() == TASKS_TO_SAVE) lastTasks.removeLast();
        lastTasks.addFirst(task);
    }

    public List<Task> getHistory() {
        return new ArrayList<>(lastTasks);
    }

}
