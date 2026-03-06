package weissach.command;

import weissach.Storage;
import weissach.TaskList;
import weissach.Ui;
import weissach.exception.WeissachException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws WeissachException;

    public boolean isExit() {
        return false;
    }
}