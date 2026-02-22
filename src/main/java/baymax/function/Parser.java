/*Handles all the message entered, parsed and carries out the valid operation*/

package baymax.function;

import baymax.*;
import baymax.task.*;
import baymax.ui.Ui;

import java.util.Objects;
import java.util.Scanner;

public class Parser {

    public static void handleInput() throws BaymaxException {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {

            String currInput = sc.nextLine();
            String[] currCommand = currInput.split(" ");

            //If the command is to leave chat
            if (Objects.equals(currInput, "bye")) {
                break;
            }
            //If the command is to List all Tasks
            else if (Objects.equals(currInput, "list")) {
                //Lists all Elements in inputList
                Ui.listInput();
            }


            //If the command is mark
            else if (Objects.equals(currCommand[0], "mark")) {

                if (currCommand.length < 2) {
                    throw new BaymaxException("You have not provided the Task Number that you want to mark. \n" +
                            "Please provide a task number to mark.");
                }
                Commands.markTask(Integer.parseInt(currCommand[1]));
            }
            //If the command is unmark
            else if (Objects.equals(currCommand[0], "unmark")) {

                if (currCommand.length < 2) {
                    throw new BaymaxException("You have not provided the Task Number that you want to unmark. \n" +
                            "Please provide a task number to unmark.");
                }
                Commands.unmarkTask(Integer.parseInt(currCommand[1]));
            }
            //If the command is to delete
            else if (Objects.equals(currCommand[0], "delete")) {

                if (currCommand.length < 2) {
                    throw new BaymaxException("You have not provided the Task Number that you want to delete. \n" +
                            "Please provide a task number to delete.");
                }
                int index = Integer.parseInt(currCommand[1]) - 1;
                Commands.deleteTask(index);
            }


            //Creating each task based on type of Task
            else {
                String[] currDescription = currInput.split(" ", 2);

                //ToDo
                if (Objects.equals(currDescription[0], "todo")) {
                    if (currDescription.length < 2) {
                        throw new BaymaxException("The description of a todo cannot be empty.\n" +
                                "Please write a valid command.");
                    }
                    ToDo todoTask = new ToDo(currDescription[1]);
                    Commands.addTask(todoTask);
                }
                //Deadline
                else if (Objects.equals(currDescription[0], "deadline")) {

                    if (currDescription.length < 2) {
                        throw new BaymaxException("The description of deadline cannot be empty.");
                    }
                    String[] descSplit = currDescription[1].split("/by");
                    if (descSplit.length < 2) {
                        throw new BaymaxException("You have not entered a deadline for the event (or)\n" +
                                "Did not format the message correctly. Please write a valid command");
                    }
                    Deadline deadlineTask = new Deadline(descSplit[0], descSplit[1]);

                    Commands.addTask(deadlineTask);
                }
                //Event
                else if (Objects.equals(currDescription[0], "event")) {

                    if (currDescription.length < 2) {
                        throw new BaymaxException("The description of event cannot be empty.");
                    }

                    String[] descSplit = currDescription[1].split("/from");
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
        }
    }
}

