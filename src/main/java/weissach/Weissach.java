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

public class Weissach {

    private static final String DIVIDER = "____________________________________________________________";
    private static final String INDENT = "   ";
    private static final int MAX_TASKS = 100;
    private static final String FILE_PATH = "./data/weissach.txt";

    private static ArrayList<Task> tasks = new ArrayList<>();

    private static void printGreeting() {
        printMessage("Hello! I'm Weissach\n"
                + "What can I do for you?");
    }

    private static void printExitMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }

    private static void loadData() throws WeissachException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
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
                    String by = parts[3];
                    newTask = new Deadline(description, by);
                    break;
                case "E":
                    String from = parts[3];
                    String to = parts[4];
                    newTask = new Event(description, from, to);
                    break;
                }

                if (newTask != null) {
                    if (isDone) {
                        newTask.markAsDone();
                    }
                    tasks.add(newTask);
                }
            }
        } catch (FileNotFoundException e) {
            throw new WeissachException("Error loading file: " + e.getMessage());
        } catch (Exception e) {
            throw new WeissachException("Error parsing file: " + e.getMessage());
        }
    }

    private static void saveData() throws WeissachException {
        try {
            File file = new File(FILE_PATH);

            File parentDir = file.getParentFile();

            if (parentDir != null && !parentDir.exists()) {
                boolean isDirCreated = parentDir.mkdirs();
                if (!isDirCreated) {
                    throw new WeissachException("Failed to create directory " + parentDir.getAbsolutePath());
                }
            }

            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new WeissachException("Error saving file: " + e.getMessage());
        }
    }

    private static void parseAndAddTask(String input) throws WeissachException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        Task newTask;

        if (!command.equals("todo") && !command.equals("deadline") &&
                !command.equals("event")) {
            throw new WeissachException("Unknown command! I don't know what \"" + command + "\" means.\n"
                    + "Try asking me for a 'todo', 'deadline', or 'event'.");
        }

        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new WeissachException("The description of a " + command + " cannot be empty.");
        }

        String description = parts[1];

        switch (command) {
        case "todo":
            newTask = new Todo(description);
            break;
        case "deadline":
            String[] deadlineParts = description.split(" /by ");
            if (deadlineParts.length < 2) {
                throw new WeissachException("Don't leave me hanging! When is this due?\n"
                        + "Please use '/by' to set the deadline.");
            }
            newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
            break;
        case "event":
            String[] eventParts = description.split(" /from | /to ");
            if (eventParts.length < 3) {
                throw new WeissachException("When is the event?\n"
                        + "You need to tell me when this starts and ends using '/from' and '/to'.");
            }
            newTask = new Event(eventParts[0], eventParts[1], eventParts[2]);
            break;
        default:
            throw new WeissachException("Unknown command!!");
        }

        tasks.add(newTask);
        saveData();
        printMessage("Got it. I've added this task:\n"
                + INDENT + newTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void listTasks() throws WeissachException {
        if (tasks.isEmpty()) {
            printMessage("No task added yet");
            return;
        }

        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            result += INDENT + String.format("%d. %s\n", i + 1, tasks.get(i).toString());
        }

        printMessage(result.trim());
    }

    private static void markTask(int taskIdx) throws WeissachException {
        if (taskIdx >= 0 && taskIdx < tasks.size()) {
            tasks.get(taskIdx).markAsDone();
            saveData();
            printMessage("Nice! I've marked this task as done:\n"
                    + INDENT + tasks.get(taskIdx).toString());
        } else {
            throw new WeissachException("Task ID " + (taskIdx + 1) + " doesn't exist.");
        }
    }

    private static void unmarkTask(int taskIdx) throws WeissachException {
        if (taskIdx >= 0 && taskIdx < tasks.size()) {
            tasks.get(taskIdx).markAsNotDone();
            saveData();
            printMessage("OK, I've marked this task as not done yet:\n"
                    + INDENT + tasks.get(taskIdx).toString());
        } else {
            throw new WeissachException("Task ID " + (taskIdx + 1) + " doesn't exist.");
        }
    }

    private static void deleteTask(int taskIdx) throws WeissachException {
        if (taskIdx >= 0 && taskIdx < tasks.size()) {
            Task removedTask = tasks.get(taskIdx);
            tasks.remove(taskIdx);
            printMessage("Noted. I've removed this task:\n"
                    + INDENT + removedTask.toString()
                    + "\nNow you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new WeissachException("Task ID " + (taskIdx + 1) + " doesn't exist.");
        }
    }


    private static void printMessage(String message) {
        System.out.println(INDENT + DIVIDER);
        System.out.println(INDENT + message.replace("\n", "\n" + INDENT));
        System.out.println(INDENT + DIVIDER);
    }

    public static void main(String[] args) {
        printGreeting();

        try {
            loadData();
        } catch (WeissachException e) {
            printMessage("Uh-oh. " + e.getMessage());
        }

        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = in.nextLine();

            if (input.isEmpty()) {
                continue;
            }

            try {
                String[] parts = input.split(" ");
                String command = parts[0];

                switch (command) {
                case "bye":
                    printExitMessage();
                    in.close();
                    return;

                case "list":
                    listTasks();
                    break;

                case "mark":
                    if (parts.length < 2) {
                        throw new WeissachException("You forgot to say which task to mark!");
                    }

                    // convert to zero-based index when marking task
                    try {
                        markTask(Integer.parseInt(parts[1]) - 1);
                    } catch (NumberFormatException e) {
                        throw new WeissachException("That's not a number! Please enter a valid task ID.");
                    }
                    break;

                case "unmark":
                    if (parts.length < 2) {
                        throw new WeissachException("You forgot to say which task to unmark!");
                    }

                    // convert to zero-based index when marking task
                    try {
                        unmarkTask(Integer.parseInt(parts[1]) - 1);
                    } catch (NumberFormatException e){
                        throw new WeissachException("That's not a number! Please enter a valid task ID.");
                    }
                    break;

                case "delete":
                    if (parts.length < 2) {
                        throw new WeissachException("You forgot to say which task to delete!");
                    }
                    try {
                        deleteTask(Integer.parseInt(parts[1]) - 1);
                    } catch (NumberFormatException e) {
                        throw new WeissachException("That's not a number! Please enter a valid task ID.");
                    }
                    break;

                default:
                    parseAndAddTask(input);
                }
            } catch (WeissachException e) {
                printMessage("Uh-oh. " + e.getMessage());
            }
        }
    }
}
