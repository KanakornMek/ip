package weissach;

import java.util.ArrayList;
import weissach.task.Task;
import weissach.exception.WeissachException;

/**
 * Represents the list of tasks and handles task operations like adding, deleting, unmarking, and marking tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task that was deleted from the list.
     * Removes the task at the specified zero-based index.
     *
     * @param index The position of the task in the list.
     * @return The Task object that was removed.
     * @throws WeissachException If the index is outside the valid range of the list.
     */
    public Task deleteTask(int index) throws WeissachException {
        if (index < 0 || index >= tasks.size()) {
            throw new WeissachException("Task ID " + (index + 1) + " doesn't exist.");
        }
        return tasks.remove(index);
    }

    /**
     * Returns the task that was marked as completed.
     *
     * @param index The zero-based index of the task to mark.
     * @return The Task object that has been marked as done.
     * @throws WeissachException If the index provided is invalid.
     */
    public Task markTask(int index) throws WeissachException {
        if (index < 0 || index >= tasks.size()) {
            throw new WeissachException("Task ID " + (index + 1) + " doesn't exist.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Returns the task that was unmarked.
     *
     * @param index The zero-based index of the task to mark.
     * @return The Task object that has been unmarked.
     * @throws WeissachException If the index provided is invalid.
     */
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

    /**
     * Return tasks that contain the specified keyword in their description.
     *
     * @param keyword The word to search for.
     * @return An ArrayList of tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if(task.containsKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}