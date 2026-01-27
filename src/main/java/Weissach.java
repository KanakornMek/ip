import java.util.Scanner;

public class Weissach {

    private static final String DIVIDER = "____________________________________________________________";
    private static void printGreeting() {
        System.out.println(DIVIDER);
        System.out.println("Hello! I'm Weissach");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
    }

    private static void printExitMessage() {
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    private static void echoCommand(String message) {
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
            }

            echoCommand(input);
        }

        in.close();
    }
}
