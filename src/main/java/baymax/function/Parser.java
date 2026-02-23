/*Handles all the message entered, parsed and carries out the valid operation*/

package baymax.function;

import baymax.*;
import baymax.task.*;
import baymax.ui.Ui;

import java.util.Objects;
import java.util.Scanner;

public class Parser {

    public static boolean handleInput(String currInput) throws BaymaxException {

        String[] commandParts = currInput.split(" ", 2);

        //If the command is to leave chat
        if (Objects.equals(currInput, "bye")) {
            return true;
        }
        //If the command is to List all Tasks
        else if (Objects.equals(currInput, "list")) {
            //Lists all Elements in inputList
            Ui.listInput();
        }


        //If the command is mark
        else if (Objects.equals(commandParts[0], "mark")) {

            if (commandParts.length < 2) {
                throw new BaymaxException("You have not provided the Task Number that you want to mark. \n" +
                        "Please provide a task number to mark.");
            }
            Commands.markTask(Integer.parseInt(commandParts[1]));
        }

        //If the command is unmark
        else if (Objects.equals(commandParts[0], "unmark")) {

            if (commandParts.length < 2) {
                throw new BaymaxException("You have not provided the Task Number that you want to unmark. \n" +
                        "Please provide a task number to unmark.");
            }
            Commands.unmarkTask(Integer.parseInt(commandParts[1]));
        }

        //If the command is to delete
        else if (Objects.equals(commandParts[0], "delete")) {

            if (commandParts.length < 2) {
                throw new BaymaxException("You have not provided the Task Number that you want to delete. \n" +
                        "Please provide a task number to delete.");
            }
            int index = Integer.parseInt(commandParts[1]) - 1;
            Commands.deleteTask(index);
        }


        //Creating each task based on type of Task
        else {
            //ToDo Tasks
            if (Objects.equals(commandParts[0], "todo")) {

                if (commandParts.length < 2) {
                    throw new BaymaxException("The description of a todo cannot be empty.\n" +
                            "Please write a valid command.");
                }
                ToDo todoTask = new ToDo(commandParts[1]);
                Commands.addTask(todoTask);
            }

            //Deadline Tasks
            else if (Objects.equals(commandParts[0], "deadline")) {

                if (commandParts.length < 2) {
                    throw new BaymaxException("The description of deadline cannot be empty.");
                }
                String[] inputParts = commandParts[1].split("/by");
                if (inputParts.length < 2) {
                    throw new BaymaxException("You have not entered a deadline for the event (or)\n" +
                            "Did not format the message correctly. Please write a valid command");
                }
                Deadline deadlineTask = new Deadline(inputParts[0], inputParts[1]);

                Commands.addTask(deadlineTask);
            }
            //Event Tasks
            else if (Objects.equals(commandParts[0], "event")) {

                if (commandParts.length < 2) {
                    throw new BaymaxException("The description of event cannot be empty.");
                }

                String[] descSplit = commandParts[1].split("/from");
                //Throws exception if invalid /to
                if (descSplit.length < 2) {
                    throw new BaymaxException("You have not entered the start time of the event (or)\n" +
                            "Did not format the message correctly. Please write a valid command");
                }

                String[] times = descSplit[1].split("/to");
                //Throws and exception if "/to" does not exist
                if (times.length < 2) {
                    throw new BaymaxException("You have not entered the end time of the event (or)\n" +
                            "Did not format the message correctly. Please write a valid command");
                }
                Event eventTask = new Event(descSplit[0], times[0], times[1]);

                Commands.addTask(eventTask);
            }
            else {
                throw new BaymaxException("I do not know what that means! \n" +
                        "Try telling me something I understand!");
            }
        }

        return false;
    }
}

