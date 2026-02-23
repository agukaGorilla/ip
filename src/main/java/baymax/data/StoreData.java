package baymax.data;

/*
* This class handles storing the list of Tasks to the hard Disk
* We write the list of tasks to another text file
* */

import baymax.task.Deadline;
import baymax.task.Event;
import baymax.task.Task;
import baymax.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;


public class StoreData {

    /*
    * Nested Enum
    * */
    private enum TaskType {
        T, D, E
    }

    /*
    * Writes data from TaskData to a text file everytime any change is made to the list of Tasks
    * */
    public static void writeToFile() {

        try {
            FileWriter fw = new FileWriter("./data/baymax.txt");
            for (int i = 0; i < TaskData.getTotalTasks(); i++) {
                Task currTask = TaskData.getTask(i);
                fw.write(currTask.getStatusIcon() + currTask.getDescription() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong :( - " + e.getMessage());
        }

    }

    /*
    * Reads data from the text file whenever we start the program
    * */
    public static void readFromFile() {

        try {
            File f = new File("./data/baymax.txt");
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String curr = sc.nextLine();
                String[] lineParts = curr.split(" ", 2);

                TaskType type = TaskType.valueOf(String.valueOf(lineParts[0].charAt(1)).toUpperCase());
                String taskDescription = lineParts[1];
                boolean isDone = (Objects.equals(String.valueOf(lineParts[0].charAt(4)).toUpperCase(), "X"));

                switch (type) {

                    case T :
                        TaskData.addTask(new ToDo(taskDescription, isDone));
                        break;

                    case D:
                        TaskData.addTask(new Deadline(taskDescription, isDone));
                        break;

                    case E:
                        TaskData.addTask(new Event(taskDescription, isDone));
                }

            }
        } catch (IOException e) {
            System.out.println("Something went wrong :(  : " + e.getMessage());
        }
    }

}
