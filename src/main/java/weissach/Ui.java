package weissach;

import java.util.ArrayList;
import java.util.Scanner;

import weissach.task.Task;

public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String INDENT = "   ";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        showMessage("Hello! I'm Weissach\nWhat can I do for you?");
        showLine();
    }

    public void showExit() {
        showMessage("Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println(INDENT + DIVIDER);
    }

    public void showMessage(String message) {
        System.out.println(INDENT + message.replace("\n", "\n" + INDENT));
    }

    public void showFoundTasks(ArrayList<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            showMessage("No matching tasks found.");
            return;
        }

        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < foundTasks.size(); i++) {
            result.append("   ").append(String.format("%d. %s\n", i + 1, foundTasks.get(i).toString()));
        }
        showMessage(result.toString().trim());
    }

    public void showError(String message) {
        showMessage("Uh-oh. " + message);
    }

    public String readCommand() {
        System.out.print("> ");
        return scanner.nextLine();
    }
}