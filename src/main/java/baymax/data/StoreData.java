package baymax.data;

/*
* This class handles storing the list of Tasks to the hard Disk
* We write the list of tasks to another text file
* */

import baymax.BaymaxException;
import baymax.task.Deadline;
import baymax.task.Event;
import baymax.task.Task;
import baymax.task.ToDo;
import com.sun.nio.sctp.AbstractNotificationHandler;

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

            File f = new File("./data/baymax.txt");
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }

            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < TaskData.getTotalTasks(); i++) {
                Task currTask = TaskData.getTask(i);

                TaskType currType = TaskType.valueOf(String.valueOf(currTask.getStatusIcon().charAt(1)).toUpperCase());
                int num = (currTask.getIsDone()? 1 : 0);
                String toWrite;

                switch (currType) {
                    case T :
                        toWrite = String.format("%s|%d|%s", currType, num, currTask.getDescription());
                        break;
                    case D:
                        Deadline d = (Deadline) currTask;
                        toWrite = String.format("%s|%d|%s|%s",
                                currType, num, currTask.getDescription(), d.getDateTime());
                        break;
                    case E:
                        toWrite = String.format("%s|%d|%s", currType, num, currTask.getDescription());
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + currType);
                }

                fw.write(toWrite + System.lineSeparator());
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

        /*
        try {
            File f = new File("./data/baymax.txt");
            if (!f.exists()) {
                return;
            }

            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String curr = sc.nextLine();

                try {
                    String statusIcon = curr.substring(0, 6);
                    String taskDescription = curr.substring(7);

                    TaskType type = TaskType.valueOf(String.valueOf(statusIcon.charAt(1)).toUpperCase());
                    boolean isDone = (Objects.equals(String.valueOf(statusIcon.charAt(4)).toUpperCase(), "X"));

                    switch (type) {

                        case T:
                            TaskData.addTask(new ToDo(taskDescription, isDone));
                            break;

                        case D:
                            TaskData.addTask(new Deadline(taskDescription, isDone));
                            break;

                        case E:
                            TaskData.addTask(new Event(taskDescription, isDone));
                    }
                } catch (Exception e) {
                    throw new BaymaxException("File corrupted :(. Couldn't load some previous tasks");
                }

            }
        } catch (IOException | BaymaxException e) {
            System.out.println("Something went wrong :(  : " + e.getMessage());
        }*/
    }

}
