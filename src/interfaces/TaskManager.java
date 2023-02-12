package interfaces;

import tasks.*;

import java.util.ArrayList;
public interface TaskManager {

    Integer getId();

    void addTask(Task task);
    Integer addEpic(EpicTask epicTask);

    void addSubTask(SubTask subTask);

    Integer generatedId();

    ArrayList<SubTask> getSubTasksEpic(int id);

    ArrayList<SubTask> getSubTaskList();
    ArrayList<EpicTask> getEpicList();

    ArrayList<Task> getTaskList();

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



    void statusChanges(Integer id);
}