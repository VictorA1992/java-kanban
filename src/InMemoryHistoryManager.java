import java.util.List;

import LastAndStatus.LastTasks;
import interfaces.HistoryManager;
import tasks.Task;

public class InMemoryHistoryManager implements HistoryManager {
    private LastTasks lastTasks = new LastTasks();

    @Override
    public List<Task> getHistory() {
        return lastTasks.getHistory();
    }

    @Override
    public void add (Task task) {
        lastTasks.addTask(task);
    }
}
