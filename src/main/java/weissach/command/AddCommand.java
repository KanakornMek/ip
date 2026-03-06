package weissach.command;

import weissach.Storage;
import weissach.TaskList;
import weissach.Ui;
import weissach.exception.WeissachException;
import weissach.task.Task;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WeissachException {
        tasks.addTask(task);
        storage.save(tasks);
        ui.showMessage("Got it. I've added this task:\n   " + task.toString()
                + "\nNow you have " + tasks.getSize() + " tasks in the list.");
    }
}