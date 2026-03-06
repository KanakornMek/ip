package weissach.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import weissach.Weissach;
import weissach.exception.WeissachException;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) throws WeissachException {
        super(description);
        try {
            this.by = LocalDate.parse(by.trim());
        } catch (DateTimeException e) {
            throw new WeissachException("Invalid date format! " +
                    "Please use yyyy-MM-dd (e.g., 2026-10-15).");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + by;
    }
}
