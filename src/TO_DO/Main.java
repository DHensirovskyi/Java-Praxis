package TO_DO;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static class Task {
        private String id;
        private String title;
        private boolean completed;
        private int priority;

        public Task(String id, String title, int priority) {
            this.id = id;
            this.title = title;
            this.priority = priority;
            this.completed = false;
        }

        public void markCompleted() {
            this.completed = true;
        }

        public String getId() {
            return this.id;
        }

        public String getTitle() {
            return this.title;
        }

        public int getPriority() {
            return this.priority;
        }

        public boolean isCompleted() {
            return completed;
        }

        public String getStatus() {
            return completed ? "✅ Completed" : "⏳ In Process";
        }
    }

    interface TaskObserver {
        void update(String message);
    }

    static class TaskLogger implements TaskObserver {
        public void update(String message) {
            System.out.println("Log: " + message);
        }
    }

    static class TaskFactory {
        public static Task createTask(String title) {
            String id = "task_" + System.currentTimeMillis();
            return new Task(id, title, 1);
        }
    }

    static class PriorityTaskFactory {
        public static Task createTask(String title) {
            String id = "priority_" + System.currentTimeMillis();
            return new Task(id, title, 3);
        }

        public static Task createCriticalTask(String title) {
            String id = "critical_" + System.currentTimeMillis();
            return new Task(id, title, 5);
        }
    }

    static class TaskManager {
        private static TaskManager instance;
        private List<Task> tasks;
        private List<TaskObserver> observers;

        private TaskManager() {
            this.tasks = new ArrayList<>();
            this.observers = new ArrayList<>();
        }

        public static TaskManager getInstance() {
            if (instance == null) {
                instance = new TaskManager();
            }
            return instance;
        }

        public void addTask(Task task) {
            tasks.add(task);
            notifyObservers("Added task: " + task.getTitle());
        }

        public List<Task> getTasks() {
            return new ArrayList<>(tasks);
        }

        public void addObserver(TaskObserver observer) {
            observers.add(observer);
        }

        public void notifyObservers(String message) {
            for (TaskObserver observer : observers) {
                observer.update(message);
            }
        }

        public List<Task> getCompletedTasks() {
            List<Task> completed = new ArrayList<>();
            for (Task task : tasks) {
                if (task.isCompleted()) {
                    completed.add(task);
                }
            }
            return completed;
        }
    }

    static class TaskController {
        private TaskManager manager;

        public TaskController() {
            this.manager = TaskManager.getInstance();
            this.manager.addObserver(new TaskLogger());
        }

        public void createNormalTask(String title) {
            Task task = TaskFactory.createTask(title);
            manager.addTask(task);
        }

        public void createImportantTask(String title) {
            Task task = PriorityTaskFactory.createTask(title);
            manager.addTask(task);
        }

        public void createCriticalTask(String title) {
            Task task = PriorityTaskFactory.createCriticalTask(title);
            manager.addTask(task);
        }

        public void listAllTasks() {
            System.out.println("\n--- All tasks ---");
            for (Task task : manager.getTasks()) {
                String priority = "⭐".repeat(task.getPriority());
                System.out.println(priority + " " + task.getTitle() + " - " + task.getStatus());
            }
        }

        public void markTaskCompleted(int index) {
            List<Task> tasks = manager.getTasks();
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markCompleted();
                manager.notifyObservers("Task completed: " + tasks.get(index).getTitle());
                System.out.println("✅ Task completed: " + tasks.get(index).getTitle());
            }
        }
    }

    public static void main(String[] args) {
        TaskController controller = new TaskController();

        System.out.println("🚀 Creating tasks...\n");

        controller.createNormalTask("Go to store");
        controller.createNormalTask("Read book");
        controller.createImportantTask("Prepare report");
        controller.createImportantTask("Attend meeting");
        controller.createCriticalTask("Fix critical bug");
        controller.createCriticalTask("Submit project");

        controller.listAllTasks();

        System.out.println("\n--- Marking tasks as completed ---");
        controller.markTaskCompleted(0);
        controller.markTaskCompleted(4);

        controller.listAllTasks();

        System.out.println("\n--- Completed tasks ---");
        List<Task> completed = TaskManager.getInstance().getCompletedTasks();
        for (Task task : completed) {
            System.out.println("✅ " + task.getTitle());
        }
    }
}