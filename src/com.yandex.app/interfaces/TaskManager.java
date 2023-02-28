package interfaces;

import model.*;

import java.util.List;
public interface TaskManager {
    List<Task> getHistory();

    Integer getId();

    void addTask(Task task);
    Integer addEpic(EpicTask epicTask);

    void addSubTask(SubTask subTask);

    List<SubTask> getSubTasksEpic(int id);

    List<SubTask> getSubTaskList();
    List<EpicTask> getEpicList();

    List<Task> getTaskList();

    Task getTaskById();

    SubTask getSubTaskById();

    EpicTask getEpicTaskById();

    void removeAllTasks();
    void removeAllEpics();

    void removeAllSubtasks();
    void deleteTaskById(int id);

    void deleteEpicTaskById(int id);

    void deleteSubtaskById(int id);

    void updateTask(Task task);
    void updateEpic(EpicTask epicTask);

    void updateSubtask(SubTask subtask);

}