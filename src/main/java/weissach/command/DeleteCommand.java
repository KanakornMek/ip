package weissach.command;

import weissach.Storage;
import weissach.TaskList;
import weissach.Ui;
import weissach.exception.WeissachException;
import weissach.task.Task;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WeissachException {
        Task removedTask = tasks.deleteTask(index);
        storage.save(tasks);
        ui.showMessage("Noted. I've removed this task:\n   " + removedTask.toString()
                + "\nNow you have " + tasks.getSize() + " tasks in the list.");
    }
}