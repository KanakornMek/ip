package weissach;

import java.util.ArrayList;
import weissach.task.Task;
import weissach.exception.WeissachException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws WeissachException {
        if (index < 0 || index >= tasks.size()) {
            throw new WeissachException("Task ID " + (index + 1) + " doesn't exist.");
        }
        return tasks.remove(index);
    }

    public Task markTask(int index) throws WeissachException {
        if (index < 0 || index >= tasks.size()) {
            throw new WeissachException("Task ID " + (index + 1) + " doesn't exist.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(int index) throws WeissachException {
        if (index < 0 || index >= tasks.size()) {
            throw new WeissachException("Task ID " + (index + 1) + " doesn't exist.");
        }
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}