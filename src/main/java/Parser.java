import java.util.Objects;
import java.util.Scanner;

public class Parser {

    public static void handleInput() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            while (true) {

                String currInput = sc.nextLine();
                String[] currCommand = currInput.split(" ");

                if (Objects.equals(currInput, "bye")) {
                    break;
                }
                else if (Objects.equals(currInput, "list")) {
                    //Lists all Elements in inputList
                    Ui.listInput();
                }
                else if (Objects.equals(currCommand[0], "mark")) {
                    Commands.markTask(Integer.parseInt(currCommand[1]));
                }
                else if (Objects.equals(currCommand[0], "unmark")) {
                    Commands.unmarkTask(Integer.parseInt(currCommand[1]));
                }
                //Creating each task based on type of Task
                else {
                    String[] currDescription = currInput.split(" ", 2);

                    if (Objects.equals(currDescription[0], "todo")) {
                        ToDo todoTask = new ToDo(currDescription[1]);
                        Baymax.inputList.add(todoTask);
                    } else if (Objects.equals(currDescription[0], "deadline")) {
                        String[] descSplit = currDescription[1].split("/by");
                        Deadline deadlineTask = new Deadline(descSplit[0], descSplit[1]);

                        Baymax.inputList.add(deadlineTask);
                    } else if (Objects.equals(currDescription[0], "event")) {
                        String[] descSplit = currDescription[1].split("/from");
                        Event eventTask = new Event(descSplit[0], descSplit[1]);

                        Baymax.inputList.add(eventTask);
                    }
                }
            }
        }
    }
}
