package weissach;

import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String INDENT = "   ";
    private Scanner scanner;

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

    public void showError(String message) {
        showMessage("Uh-oh. " + message);
    }

    public String readCommand() {
        System.out.print("> ");
        return scanner.nextLine();
    }
}