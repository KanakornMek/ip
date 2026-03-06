package weissach.command;

import weissach.Storage;
import weissach.TaskList;
import weissach.Ui;
import weissach.exception.WeissachException;
import weissach.task.Task;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WeissachException {
        Task task = tasks.unmarkTask(index);
        storage.save(tasks);
        ui.showMessage("OK, I've marked this task as not done yet:\n   " + task.toString());
    }
}