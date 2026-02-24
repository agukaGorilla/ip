package baymax.data;

/*
* This class handles storing the list of Tasks to the hard Disk
* We write the list of tasks to another text file
* */

import baymax.BaymaxException;
import baymax.task.*;
import com.sun.nio.sctp.AbstractNotificationHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;


public class StoreData {

    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
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
                TaskType taskType = currTask.getTaskType();
                int num = (currTask.getIsDone()? 1 : 0);

                String toWrite = "";

                switch (taskType) {
                    case TODO :
                        toWrite = String.format(" T | %d | %s \n", num, currTask.getDescription());
                        break;
                    case DEADLINE:
                        Deadline d = (Deadline) currTask;

                        LocalDateTime dateTime = d.getDateTime();
                        String dateString = dateTime.format(dateTimeFormat);

                        toWrite = String.format(" D | %d | %s | %s \n", num, currTask.getDescription(), dateString);
                        break;
                    case EVENT:
                        Event e = (Event) currTask;

                        LocalDateTime startTime = e.getStartTime();
                        String time1 = startTime.format(dateTimeFormat);

                        LocalDateTime endTime = e.getEndTime();
                        String time2 = startTime.format(dateTimeFormat);

                        toWrite = String.format(" E | %d | %s | %s | %s \n", num, currTask.getDescription(),
                                time1, time2);
                        break;
                    default:
                }
                fw.write(toWrite);
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
