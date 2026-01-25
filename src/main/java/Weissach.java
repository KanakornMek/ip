public class Weissach {

    private static void printGreeting() {
        System.out.println(
                "____________________________________________________________\n" +
                        " Hello! I'm Weissach\n" +
                        " What can I do for you?\n" +
                        "____________________________________________________________"
        );
    }

    private static void printExitMessage() {
        System.out.println(
                "Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________"
        );
    }

    public static void main(String[] args) {
        printGreeting();
        printExitMessage();
    }
}
