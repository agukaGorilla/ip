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

        }
        catch (IOException e) {
            System.out.println("Something went wrong :( - " + e.getMessage());
        }

    }

    /*
    * Reads data from the text file whenever we start the program
    * */
    public static void readFromFile() {


        try {
            File f = new File("./data/baymax.txt");
            if (!f.exists()) {
                return;
            }

            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();
                String[] lineWords = currLine.split("\\|");

                String taskType = lineWords[0].trim();

                int num = Integer.parseInt(lineWords[1].trim());
                boolean isDone = (num == 1 ? true : false);

                String taskDescription = lineWords[2].trim();

                try {
                    switch (taskType) {
                        case "T" :

                            ToDo todoTask = new ToDo(taskDescription, isDone);
                            TaskData.addTask(todoTask);

                            break;
                        case "D":

                            String dTime = lineWords[3].trim();
                            LocalDateTime time = LocalDateTime.parse(dTime, dateTimeFormat);

                            Deadline dTask = new Deadline(taskDescription, isDone, time);
                            TaskData.addTask(dTask);

                            break;
                        case "E":

                            String sTime = lineWords[3].trim();
                            LocalDateTime time1 = LocalDateTime.parse(sTime, dateTimeFormat);

                            String eTime = lineWords[4].trim();
                            LocalDateTime time2 = LocalDateTime.parse(eTime, dateTimeFormat);

                            Event eTask = new Event(taskDescription, isDone, time1, time2);
                            TaskData.addTask(eTask);

                            break;
                    }
                }
                catch (Exception e) {
                    throw new BaymaxException("File corrupted :(. Couldn't load some previous tasks");
                }

            }
        }
        catch (IOException | BaymaxException e) {
            System.out.println("Something went wrong :(  : " + e.getMessage());
        }
    }

}
