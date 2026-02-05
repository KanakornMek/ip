import java.util.Scanner;

public class Weissach {

    private static final String DIVIDER = "____________________________________________________________";
    private static final String INDENT = "   ";

    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    private static void printGreeting() {
        printMessage("Hello! I'm Weissach\n"
                + "What can I do for you?");
    }

    private static void printExitMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }

    private static void addTask(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String description = parts[1];
        Task newTask;

        switch (command) {
        case "todo":
            newTask = new Todo(description);
            break;
        case "deadline":
            String[] deadlineParts = description.split(" /by ");
            newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
            break;
        case "event":
            String[] eventParts = description.split(" /from | /to ");
            newTask = new Event(eventParts[0], eventParts[1], eventParts[2]);
            break;
        default:
            newTask = new Todo(input);
        }

        tasks[taskCount++] = newTask;
        printMessage("Got it. I've added this task:\n"
                + INDENT + newTask.toString()
                + "\nNow you have " + taskCount + " tasks in the list.");
    }

    private static void listTasks() {
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

    private static void markTask(int taskIdx) {
        if (taskIdx >= 0 && taskIdx < taskCount) {
            tasks[taskIdx].markAsDone();
            printMessage("Nice! I've marked this task as done:\n"
                    + INDENT + tasks[taskIdx].toString());
        }
    }

    private static void unmarkTask(int taskIdx) {
        if (taskIdx >= 0 && taskIdx < taskCount) {
            tasks[taskIdx].markAsNotDone();
            printMessage("OK, I've marked this task as not done yet:\n"
                    + INDENT + tasks[taskIdx].toString());
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
                    printMessage("Please specify the task number");
                    break;
                }
                markTask(Integer.parseInt(parts[1]) - 1);
                break;

            case "unmark":
                if (parts.length < 2) {
                    printMessage("Please specify the task number");
                    break;
                }
                unmarkTask(Integer.parseInt(parts[1]) - 1);
                break;

            case "todo":
            case "deadline":
            case "event":
                addTask(input);
                break;

            default:
                addTask(input);
            }
        }
    }
}
