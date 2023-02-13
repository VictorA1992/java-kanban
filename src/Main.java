import tasks.*;

import interfaces.TaskManager;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        printTask();
    }

    public static void printTask() {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
        // создание задач
        Task taskOne = new Task("Яндекс Практикум", "Успеть всё до дэдлайна", Status.NEW);
        Task taskTwo = new Task("Дом", "Провести генеральную уборку", Status.NEW);

        inMemoryTaskManager.addTask(taskOne);
        inMemoryTaskManager.addTask(taskTwo);

        // создание эпиков
        EpicTask epicTaskOne = new EpicTask("ПВЗ", "Открыть ПВЗ");
        EpicTask epicTaskTwo = new EpicTask("Путешествие", "Посетить золотое кольцо");

        Integer epicIdOne = inMemoryTaskManager.addEpic(epicTaskOne);
        Integer epicIdTwo = inMemoryTaskManager.addEpic(epicTaskTwo);

        // создание подзадач
        SubTask subTask1 = new SubTask("Налоговая", "Открыть ИП", Status.NEW, epicIdOne);
        SubTask subTask2 = new SubTask("Локация", "Согласовать локацию", Status.NEW, epicIdOne);
        SubTask subTask3 = new SubTask("Помещение", "Сделать ремонт", Status.NEW, epicIdOne);
        SubTask subTask4 = new SubTask("Маршут", "Составить маршут", Status.DONE, epicIdTwo);
        SubTask subTask5 = new SubTask("Машина", "Сделать полное ТО", Status.IN_PROGRESS, epicIdTwo);

        inMemoryTaskManager.addSubTask(subTask1);
        inMemoryTaskManager.addSubTask(subTask2);
        inMemoryTaskManager.addSubTask(subTask3);
        inMemoryTaskManager.addSubTask(subTask4);
        inMemoryTaskManager.addSubTask(subTask5);

        // проверка
        List<Task> out = inMemoryTaskManager.getHistory();
        System.out.println(out);

        System.out.println(inMemoryTaskManager.getTaskById());
        System.out.println(inMemoryTaskManager.getEpicList());
        System.out.println(inMemoryTaskManager.getTaskList());
        System.out.println(inMemoryTaskManager.getSubTaskList());
    }
}