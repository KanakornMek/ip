package weissach;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import weissach.exception.WeissachException;
import weissach.task.Deadline;
import weissach.task.Event;
import weissach.task.Task;
import weissach.task.Todo;

/**
 * Represents the file storage system. Used to read from and write tasks to
 * the local disk.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the list of tasks loaded from the storage file.
     * Parses each line of the text file into the corresponding Task object.
     *
     * @return An ArrayList of tasks retrieved from the file.
     * @throws WeissachException If the file cannot be read or parsing fails.
     */
    public ArrayList<Task> load() throws WeissachException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return loadedTasks;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task newTask = null;
                switch (type) {
                case "T":
                    newTask = new Todo(description);
                    break;
                case "D":
                    newTask = new Deadline(description, parts[3]);
                    break;
                case "E":
                    newTask = new Event(description, parts[3], parts[4]);
                    break;
                }

                if (newTask != null) {
                    if (isDone) {
                        newTask.markAsDone();
                    }
                    loadedTasks.add(newTask);
                }
            }
        } catch (FileNotFoundException e) {
            throw new WeissachException("Error loading file: " + e.getMessage());
        } catch (Exception e) {
            throw new WeissachException("Error parsing file: " + e.getMessage());
        }
        return loadedTasks;
    }

    /**
     * Saves the current list of tasks to the text file.
     *
     * @param tasks The TaskList containing the tasks to be saved.
     * @throws WeissachException If there is an error creating the directory or writing to the file.
     */
    public void save(TaskList tasks) throws WeissachException {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();

            if (parentDir != null && !parentDir.exists()) {
                if (!parentDir.mkdirs()) {
                    throw new WeissachException("Failed to create directory " + parentDir.getAbsolutePath());
                }
            }

            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < tasks.getSize(); i++) {
                writer.write(tasks.getTask(i).toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new WeissachException("Error saving file: " + e.getMessage());
        }
    }
}