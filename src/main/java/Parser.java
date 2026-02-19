import java.util.Objects;
import java.util.Scanner;

public class Parser {

    public static void handleInput() throws BaymaxException {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {

            String currInput = sc.nextLine();
            String[] currCommand = currInput.split(" ");

            if (Objects.equals(currInput, "bye")) {
                break;
            } else if (Objects.equals(currInput, "list")) {
                //Lists all Elements in inputList
                Ui.listInput();
            } else if (Objects.equals(currCommand[0], "mark")) {

                if (currCommand.length < 2) {
                    throw new BaymaxException("Please provide a task number to mark.");
                }
                Commands.markTask(Integer.parseInt(currCommand[1]));
            } else if (Objects.equals(currCommand[0], "unmark")) {

                if (currCommand.length < 2) {
                    throw new BaymaxException("Please provide a task number to unmark.");
                }
                Commands.unmarkTask(Integer.parseInt(currCommand[1]));
            }
            //Creating each task based on type of Task
            else {
                String[] currDescription = currInput.split(" ", 2);

                if (Objects.equals(currDescription[0], "todo")) {
                    if (currCommand.length < 2) {
                        throw new BaymaxException("The description of a todo cannot be empty.");
                    }
                    ToDo todoTask = new ToDo(currDescription[1]);
                    Baymax.inputList.add(todoTask);
                } else if (Objects.equals(currDescription[0], "deadline")) {

                    if (currCommand.length < 2) {
                        throw new BaymaxException("The description of deadline cannot be empty.");
                    }
                    String[] descSplit = currDescription[1].split("/by");
                    Deadline deadlineTask = new Deadline(descSplit[0], descSplit[1]);

                    Baymax.inputList.add(deadlineTask);
                } else if (Objects.equals(currDescription[0], "event")) {

                    if (currCommand.length < 2) {
                        throw new BaymaxException("The description of event cannot be empty.");
                    }
                    String[] descSplit = currDescription[1].split("/from");
                    Event eventTask = new Event(descSplit[0], descSplit[1]);

                    Baymax.inputList.add(eventTask);
                }
                else {
                    throw new BaymaxException("OOPSIES!! I do not know what that means! \n" +
                            "Try telling me something I understand!");
                }
            }
        }
    }
}

