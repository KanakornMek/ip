package weissach;

import weissach.command.AddCommand;
import weissach.command.Command;
import weissach.command.DeleteCommand;
import weissach.command.ExitCommand;
import weissach.command.ListCommand;
import weissach.command.MarkCommand;
import weissach.command.UnmarkCommand;
import weissach.exception.WeissachException;
import weissach.task.Deadline;
import weissach.task.Event;
import weissach.task.Todo;

public class Parser {

    public static Command parse(String fullCommand) throws WeissachException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(parseIndex(parts, "mark"));
        case "unmark":
            return new UnmarkCommand(parseIndex(parts, "unmark"));
        case "delete":
            return new DeleteCommand(parseIndex(parts, "delete"));
        case "todo":
            return new AddCommand(new Todo(parseDescription(parts, "todo")));
        case "deadline":
            String deadlineDesc = parseDescription(parts, "deadline");
            String[] deadlineParts = deadlineDesc.split(" /by ");
            if (deadlineParts.length < 2) {
                throw new WeissachException("Don't leave me hanging! When is this due?\n"
                        + "Please use '/by' to set the deadline.");
            }
            return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]));
        case "event":
            String eventDesc = parseDescription(parts, "event");
            String[] eventParts = eventDesc.split(" /from | /to ");
            if (eventParts.length < 3) {
                throw new WeissachException("When is the event?\n"
                        + "You need to tell me when this starts and ends using '/from' and '/to'.");
            }
            return new AddCommand(new Event(eventParts[0], eventParts[1], eventParts[2]));
        default:
            throw new WeissachException("Unknown command! I don't know what \"" + commandWord + "\" means.\n"
                    + "Try asking me for a 'todo', 'deadline', or 'event'.");
        }
    }

    private static int parseIndex(String[] parts, String commandWord) throws WeissachException {
        if (parts.length < 2) {
            throw new WeissachException("You forgot to say which task to " + commandWord + "!");
        }
        try {
            return Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new WeissachException("That's not a number! Please enter a valid task ID.");
        }
    }

    private static String parseDescription(String[] parts, String commandWord) throws WeissachException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new WeissachException("The description of a " + commandWord + " cannot be empty.");
        }
        return parts[1].trim();
    }
}