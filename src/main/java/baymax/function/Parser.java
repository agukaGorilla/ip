/*Handles all the message entered, parsed and carries out the valid operation*/

package baymax.function;

import baymax.BaymaxException;
import baymax.task.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    /**
     * Parses user input into executable commands.
     * Extracts command and data types.
     * Calls the appropriate methods to trigger appropriate actions.
     *
     * @param currInput Input string types by user
     * @return True if user issued the exit command ("bye"), false otherwise.
     * @throws BaymaxException If input by user is invalid or in incorrect form.
     */
    public static boolean handleInput(String currInput) throws BaymaxException {
        
        String[] commandParts = currInput.trim().split(" ", 2);
        CommandType command;
        
        try {
            command = CommandType.valueOf(commandParts[0].toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            command = CommandType.UNKNOWN;
        }
        
        switch (command) {
        case BYE:
            return Commands.canCloseProgram();
        
        case LIST:
            Commands.listTasks();
            break;
        
        case MARK:
            if (commandParts.length < 2) {
                throw new BaymaxException("You have not provided the Task Number that you want to mark. \n" +
                        "Please provide a task number to mark.");
            }
            Commands.markTask(Integer.parseInt(commandParts[1]));
            break;
        
        case UNMARK:
            if (commandParts.length < 2) {
                throw new BaymaxException("You have not provided the Task Number that you want to unmark. \n" +
                        "Please provide a task number to unmark.");
            }
            Commands.unmarkTask(Integer.parseInt(commandParts[1]));
            break;
        
        case DELETE:
            if (commandParts.length < 2) {
                throw new BaymaxException("You have not provided the Task Number that you want to delete. \n" +
                        "Please provide a task number to delete.");
            }
            int index = Integer.parseInt(commandParts[1]) - 1;
            Commands.deleteTask(index);
            break;
        
        case SCHEDULE:
            if (commandParts.length < 2) {
                throw new BaymaxException("You have not provided the the date for the tasks to list. \n" +
                        "Please provide a valid date.");
            }
            
            LocalDate date;
            try {
                date = LocalDate.parse(commandParts[1].trim(), DATE_FORMAT);
            } catch (DateTimeException e) {
                throw new BaymaxException(
                        "Please enter the due date in this exact format:\nyyyy-MM-dd (eg., 2026-02-22)");
            }
            
            Commands.listTasksDate(date);
            break;
        
        case TODO:
            if (commandParts.length < 2) {
                throw new BaymaxException("The description of a todo cannot be empty.\n" +
                        "Please write a valid command.");
            }
            
            String replace = commandParts[1].replace("|", "-");
            ToDo todoTask = new ToDo(replace);
            Commands.addTask(todoTask);
            break;
        
        case FIND:
            if (commandParts.length < 2) {
                throw new BaymaxException("You have not provided the search word/phrase. \n" +
                        "Please provide a valid phrase (or) word to search the tasks.");
            }
            String searchWord = commandParts[1].trim();
            Commands.searchTasks(searchWord);
            break;
        
        case DEADLINE:
            if (commandParts.length < 2) {
                throw new BaymaxException("The description of deadline cannot be empty.");
            }
            String[] inputParts = commandParts[1].split("/by");
            if (inputParts.length < 2) {
                throw new BaymaxException("You have not entered a deadline for the event (or)\n" +
                        "Did not format the message correctly. Please write a valid command");
            }
            
            LocalDateTime dateTime;
            try {
                dateTime = LocalDateTime.parse(inputParts[1].trim(), DATE_TIME_FORMAT);
            } catch (DateTimeException e) {
                throw new BaymaxException(
                        "Please enter the due date in this exact format:\nyyyy-MM-dd HHmm (eg., 2026-02-22 0500)");
            }
            
            String replace2 = inputParts[0].replace("|", "-");
            Deadline deadlineTask = new Deadline(replace2, dateTime);
            
            Commands.addTask(deadlineTask);
            break;
        
        case EVENT:
            if (commandParts.length < 2) {
                throw new BaymaxException("The description of event cannot be empty.");
            }
            
            String[] descSplit = commandParts[1].split("/from");
            //Throws exception if invalid /to
            if (descSplit.length < 2) {
                throw new BaymaxException("You have not entered the time of the event (or)\n" +
                        "Did not format the message correctly. Please write a valid command");
            }
            
            String[] times = descSplit[1].split("/to");
            //Throws and exception if "/to" does not exist
            if (times.length < 2) {
                throw new BaymaxException("You have not entered the end time of the event (or)\n" +
                        "Did not format the message correctly. Please write a valid command");
            }
            
            LocalDateTime time1;
            LocalDateTime time2;
            
            try {
                time1 = LocalDateTime.parse(times[0].trim(), DATE_TIME_FORMAT);
                time2 = LocalDateTime.parse(times[1].trim(), DATE_TIME_FORMAT);
            } catch (DateTimeException e) {
                throw new BaymaxException(
                        "Please enter the due date in this exact format:\nyyyy-MM-dd HHmm (eg., 2026-02-22 0500)");
            }
            
            String replace3 = descSplit[0].replace("|", "-");
            Event eventTask = new Event(replace3, time1, time2);
            
            Commands.addTask(eventTask);
            break;
        
        case UNKNOWN:
            throw new BaymaxException("I do not know what that means! \n" +
                    "Try telling me something I understand!");
        }

        return false;
    }
}

