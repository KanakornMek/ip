package weissach.command;

import weissach.Storage;
import weissach.TaskList;
import weissach.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showMessage("No task added yet");
            return;
        }

        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            result.append("   ").append(String.format("%d. %s\n", i + 1, tasks.getTask(i).toString()));
        }
        ui.showMessage(result.toString().trim());
    }
}