import model.*;

import interfaces.TaskManager;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        printTask();
    }

    public static void printTask() {
        TaskManager mng = Managers.getDefault();
        // создание задач
        Task taskOne = new Task("Яндекс Практикум", "Успеть всё до дэдлайна", Status.NEW);
        Task taskTwo = new Task("Дом", "Провести генеральную уборку", Status.NEW);

        mng.addTask(taskOne);
        mng.addTask(taskTwo);

        // создание эпиков
        EpicTask epicTaskOne = new EpicTask("ПВЗ", "Открыть ПВЗ");
        EpicTask epicTaskTwo = new EpicTask("Путешествие", "Посетить золотое кольцо");

        Integer epicIdOne = mng.addEpic(epicTaskOne);
        Integer epicIdTwo = mng.addEpic(epicTaskTwo);

        // создание подзадач
        SubTask subTask1 = new SubTask("Налоговая", "Открыть ИП", Status.NEW, epicIdOne);
        SubTask subTask2 = new SubTask("Локация", "Согласовать локацию", Status.NEW, epicIdOne);
        SubTask subTask3 = new SubTask("Помещение", "Сделать ремонт", Status.NEW, epicIdOne);
        SubTask subTask4 = new SubTask("Маршут", "Составить маршут", Status.DONE, epicIdTwo);
        SubTask subTask5 = new SubTask("Машина", "Сделать полное ТО", Status.IN_PROGRESS, epicIdTwo);

        mng.addSubTask(subTask1);
        mng.addSubTask(subTask2);
        mng.addSubTask(subTask3);
        mng.addSubTask(subTask4);
        mng.addSubTask(subTask5);

        // проверка
        List<Task> out = mng.getHistory();
        System.out.println(out);

        System.out.println(mng.getTaskById());
        System.out.println(mng.getEpicList());
        System.out.println(mng.getTaskList());
        System.out.println(mng.getSubTaskList());
    }
}