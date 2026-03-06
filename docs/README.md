# Weissach User Guide

Weissach is a desktop app for managing your tasks, optimized for use via a Command Line Interface (CLI). If you can type fast, Weissach can get your task management tasks done faster than traditional GUI apps.

---

## Features

### Adding a Todo: `todo`
Adds a simple task without any date attached.
* **Format:** `todo DESCRIPTION`
* **Example:** `todo read book`

### Adding a Deadline: `deadline`
Adds a task that needs to be done before a specific date.
* **Format:** `deadline DESCRIPTION /by DATE`
* **Note:** `DATE` must be in `yyyy-MM-dd` format.
* **Example:** `deadline return book /by 2026-12-02`

### Adding an Event: `event`
Adds a task that starts and ends on specific dates.
* **Format:** `event DESCRIPTION /from START_DATE /to END_DATE`
* **Note:** Both dates must be in `yyyy-MM-dd` format.
* **Example:** `event project meeting /from 2026-10-05 /to 2026-10-06`

### Listing all tasks: `list`
Shows a list of all tasks currently in the task tracker.
* **Format:** `list`

### Finding tasks: `find`
Finds tasks whose descriptions contain the given keyword.
* **Format:** `find KEYWORD`
* **Example:** `find book`

### Marking a task as done: `mark`
Marks a specific task in the list as completed.
* **Format:** `mark INDEX`
* **Example:** `mark 1` (marks the 1st task as done)

### Unmarking a task: `unmark`
Marks a specific completed task as not done yet.
* **Format:** `unmark INDEX`
* **Example:** `unmark 1`

### Deleting a task: `delete`
Deletes the specified task from the list.
* **Format:** `delete INDEX`
* **Example:** `delete 2` (deletes the 2nd task in the list)

### Exiting the program: `bye`
Exits the program and automatically saves your data to the hard disk.
* **Format:** `bye`

---

## Command Summary

| Action | Format | Example |
| :--- | :--- | :--- |
| **Todo** | `todo DESCRIPTION` | `todo wash car` |
| **Deadline** | `deadline DESCRIPTION /by DATE` | `deadline submit report /by 2026-10-15` |
| **Event** | `event DESCRIPTION /from START_DATE /to END_DATE` | `event concert /from 2026-04-01 /to 2026-04-02` |
| **List** | `list` | `list` |
| **Find** | `find KEYWORD` | `find report` |
| **Mark** | `mark INDEX` | `mark 3` |
| **Unmark** | `unmark INDEX` | `unmark 3` |
| **Delete** | `delete INDEX` | `delete 2` |
| **Exit** | `bye` | `bye` |