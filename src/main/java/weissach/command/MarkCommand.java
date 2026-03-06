package weissach.command;

import weissach.Storage;
import weissach.TaskList;
import weissach.Ui;
import weissach.exception.WeissachException;
import weissach.task.Task;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WeissachException {
        Task task = tasks.markTask(index);
        storage.save(tasks);
        ui.showMessage("Nice! I've marked this task as done:\n   " + task.toString());
    }
}