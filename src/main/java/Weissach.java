import java.util.Scanner;

public class Weissach {

    private static final String DIVIDER = "____________________________________________________________";

    private static String[] items = new String[100];
    private static int itemCount = 0;

    private static void printGreeting() {
        printMessage("Hello! I'm Weissach\n"
                + "What can I do for you?");
    }

    private static void printExitMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }

    private static void addItem(String item) {
        items[itemCount++] = item;

        printMessage("added: " + item);
    }

    private static void listItems() {
        if (itemCount == 0) {
            printMessage("No item added yet");
            return;
        }

        String result = "";
        for (int i = 0; i < itemCount - 1; i++) {
            result += String.format("%d. %s\n", i+1, items[i]);
        }

        result += String.format("%d. %s", itemCount, items[itemCount-1]);
        printMessage(result);
    }

    private static void printMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
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
                listItems();
            } else {
                addItem(input);
            }
        }

        in.close();
    }
}
