package weissach;

import weissach.command.Command;
import weissach.exception.WeissachException;

public class Weissach {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Weissach(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (WeissachException e) {
            ui.showError("Error loading file: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                if (fullCommand.trim().isEmpty()) {
                    continue;
                }
                ui.showLine();

                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);

                isExit = c.isExit();
            } catch (WeissachException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("That's not a number! Please enter a valid task ID.");
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Weissach("./data/weissach.txt").run();
    }
}
