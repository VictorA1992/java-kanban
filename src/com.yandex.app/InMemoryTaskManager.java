import interfaces.*;
import model.*;

import java.util.*;

public class InMemoryTaskManager implements TaskManager, HistoryManager {

    private Integer id = 1;

    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, SubTask> subTasks = new HashMap<>();
    private final Map<Integer, EpicTask> epicTasks = new HashMap<>();
    // recheck
    private final HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public void add(Task task) {
        historyManager.add(task);
    }

    // getter and setter

    public Integer getId() {
        return id;
    }


    // create
    @Override
    public void addTask(Task task) {
        Integer newId = generatedId();
        task.setId(newId);
        this.tasks.put(newId, task);
    }
    @Override
    public Integer addEpic(EpicTask epicTask) {
        Integer newId = generatedId();
        epicTask.setId(newId);
        this.epicTasks.put(newId, epicTask);
        return newId;
    }

    @Override
    public void addSubTask(SubTask subTask) {
        int newId = generatedId();
        subTask.setId(newId);
        subTasks.put(newId, subTask);
//Не дублируем обращение к мапе, а вводим переменную
        EpicTask epic = epicTasks.get(subTask.getEpicId());
        epic.addSubtaskId(newId);
//Обновление статуса должно осуществляться с помощью метода обновления статуса
        statusChanges(epic.getId());
    }

    // create id
// create id
    private Integer generatedId() {
        return ++id;
    }


    // get
    @Override
    public List<SubTask> getSubTasksEpic(int id) {
        List<SubTask> subTasksEpic = new ArrayList<>();
        for (int idSubTask : epicTasks.get(id).getSubtasksId()) {
            subTasksEpic.add(subTasks.get(idSubTask));
        }
        return subTasksEpic;
    }

    @Override
    public List<SubTask> getSubTaskList() {
        return new ArrayList<>(subTasks.values());
    }
    @Override
    public List<EpicTask> getEpicList() {
        return new ArrayList<>(epicTasks.values());
    }

    @Override
    public List<Task> getTaskList() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Task getTaskById() {
        return tasks.get(id);
    }

    @Override
    public SubTask getSubTaskById() { return subTasks.get(id); }

    @Override
    public EpicTask getEpicTaskById() {
        return epicTasks.get(id);
    }

    @Override
    public void removeAllTasks() {
        tasks.clear();
    }

    @Override
    public void removeAllEpics() {
        epicTasks.clear();
        removeAllSubtasks();
    }

    @Override
    public void removeAllSubtasks() {
        subTasks.clear();

        for (EpicTask epic : epicTasks.values()) {
            epic.getSubtasksId().clear();
            statusChanges(epic.getId());
        }
    }
    @Override
    public void deleteTaskById(int id) {
        if (tasks.get(id) != null) { // в общем не нужно
            tasks.remove(id);
        }
    }

    @Override
    public void deleteEpicTaskById(int id) {
        if (epicTasks.get(id) != null) { // в общем не нужно
            for (Integer subtaskId : epicTasks.get(id).getSubtasksId()) {
                    subTasks.remove(subtaskId);
                }
            }
            epicTasks.remove(id);
        }


    @Override
    public void deleteSubtaskById(int id) {
        if (subTasks.get(id) != null) { // в общем не нужно
            int idEpic = subTasks.get(id).getEpicId();
            epicTasks.get(idEpic).getSubtasksId().remove((Integer) id);
            subTasks.remove(id);
            statusChanges(idEpic);
        }
    }


    // update
    @Override
    public void updateTask(Task task) {
        if (task.getId() != null && tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }
    @Override
    public void updateEpic(EpicTask epicTask) {

        if (epicTask.getId() != null && epicTasks.containsKey(epicTask.getId())) {
            epicTasks.put(epicTask.getId(), epicTask);
        }
    }

    @Override
    public void updateSubtask(SubTask subtask) {
        if (subtask.getId() != null && subTasks.containsKey(subtask.getId())) {
            subTasks.put(subtask.getId(), subtask);
            statusChanges(subtask.getEpicId());
        }
    }



    private void statusChanges(Integer id) {

        boolean isAllSubtasksNew = false;
        boolean isAllSubtasksDone = false;


            EpicTask epic = epicTasks.get(id);
            if (epic.getSubtasksId().isEmpty()) {
                epic.setStatus(Status.NEW);
                return;
            }

            for (int subTaskId : epic.getSubtasksId()) {
                SubTask subTask = subTasks.get(subTaskId);
                switch (subTask.getStatus()) {
                    case NEW:
                        isAllSubtasksNew = true;
                        break;
                    case IN_PROGRESS:
                        epic.setStatus(Status.IN_PROGRESS);
                        return;
                    case DONE:
                        isAllSubtasksDone = true;
                        break;
                }
            }
            if (isAllSubtasksNew && !isAllSubtasksDone) {
                epic.setStatus(Status.NEW);
            } else if (!isAllSubtasksNew && isAllSubtasksDone) {
                epic.setStatus(Status.DONE);
            } else {
                epic.setStatus(Status.IN_PROGRESS);
            }
        }
    }
