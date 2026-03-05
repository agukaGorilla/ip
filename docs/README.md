# Baymax User Guide

Hello! I'm **Baymax**, your personal healthcare companion... but for your tasks! I help you keep track of your ToDos, Deadlines, and Events with a friendly interface.

---

## Features 

### 1. Adding Tasks
* **ToDo**: Adds a task without a date.
  * Format: `todo DESCRIPTION` or `t DESCRIPTION`
* **Deadline**: Adds a task with a specific "by" date/time.
  * Format: `deadline DESCRIPTION /by yyyy-MM-dd HHmm` or `d DESCRIPTION /by yyyy-MM-dd HHmm`
* **Event**: Adds a task that spans a time period.
  * Format: `event DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm` or `e DESCRIPTION /from ...`

### 2. Managing Tasks
* **List**: View all your current tasks.
  * Format: `list` or `l`
* **Mark/Unmark**: Toggle completion status.
  * Format: `mark INDEX` or `m INDEX` (e.g., `mark 1`)
  * Format: `unmark INDEX` or `um INDEX`
* **Delete**: Remove a task from your list.
  * Format: `delete INDEX` or `del INDEX`

### 3. Utility
* **Find**: Search for tasks by keyword.
  * Format: `find KEYWORD` or `f KEYWORD`
* **Schedule**: View tasks occurring on a specific date.
  * Format: `schedule yyyy-MM-dd` or `s yyyy-MM-dd`
* **Bye**: Saves your data and exits the app.
  * Format: `bye` or `q`

---

## Command Summary

| Action | Full Command | Alias |
|--------|--------------|-------|
| Add ToDo | `todo` | `t` |
| Add Deadline | `deadline` | `d` |
| Add Event | `event` | `e` |
| List Tasks | `list` | `l` |
| Mark Task | `mark` | `m` |
| Unmark Task | `unmark` | `um` |
| Delete Task | `delete` | `del` |
| Search | `find` | `f` |
| Quit | `bye` | `q` |