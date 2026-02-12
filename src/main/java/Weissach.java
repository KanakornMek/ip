import java.util.Scanner;

public class Weissach {

    private static final String DIVIDER = "____________________________________________________________";
    private static final String INDENT = "   ";
    private static final int MAX_TASKS = 100;

    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    private static void printGreeting() {
        printMessage("Hello! I'm Weissach\n"
                + "What can I do for you?");
    }

    private static void printExitMessage() {
        printMessage("Bye. Hope to see you again soon!");
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

        tasks[taskCount++] = newTask;
        printMessage("Got it. I've added this task:\n"
                + INDENT + newTask.toString()
                + "\nNow you have " + taskCount + " tasks in the list.");
    }

    private static void listTasks() throws WeissachException{
        if (taskCount == 0) {
            printMessage("No task added yet");
            return;
        }

        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskCount - 1; i++) {
            result += INDENT + String.format("%d. %s\n", i + 1, tasks[i].toString());
        }

        result += INDENT + String.format("%d. %s", taskCount, tasks[taskCount - 1].toString());
        printMessage(result);
    }

    private static void markTask(int taskIdx) throws WeissachException {
        if (taskIdx >= 0 && taskIdx < taskCount) {
            tasks[taskIdx].markAsDone();
            printMessage("Nice! I've marked this task as done:\n"
                    + INDENT + tasks[taskIdx].toString());
        } else {
            throw new WeissachException("Task ID " + (taskIdx + 1) + " doesn't exist.");
        }
    }

    private static void unmarkTask(int taskIdx) throws WeissachException {
        if (taskIdx >= 0 && taskIdx < taskCount) {
            tasks[taskIdx].markAsNotDone();
            printMessage("OK, I've marked this task as not done yet:\n"
                    + INDENT + tasks[taskIdx].toString());
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

                default:
                    parseAndAddTask(input);
                }
            } catch (WeissachException e) {
                printMessage("Uh-oh. " + e.getMessage());
            }
        }
    }
}
