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

    private static void addTask(String task) {
        tasks[taskCount++] = new Task(task);

        printMessage("added: " + task);
    }

    private static void listTasks() {
        if (taskCount == 0) {
            printMessage("No task added yet");
            return;
        }

        String result = "Here are the tasks in your lists:\n";
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

            if (input.equals("bye")){
                printExitMessage();
                break;
            } else if (input.equals("list")) {
                listTasks();
            } else if (input.startsWith("mark")) {
                String[] split = input.split(" ");

                int idx = Integer.parseInt(split[1]) - 1;
                markTask(idx);
            } else if (input.startsWith("unmark")) {
                String[] split = input.split(" ");

                int idx = Integer.parseInt(split[1]) - 1;
                unmarkTask(idx);
            } else {
                addTask(input);
            }
        }

        in.close();
    }
}
