package baymax.data;

import baymax.BaymaxException;
import baymax.task.Task;
import baymax.task.TaskType;
import baymax.task.ToDo;
import baymax.task.Deadline;
import baymax.task.Event;
import baymax.ui.UiBuffer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Handles reading and writing of tasks to local hard drive.
 */
public class StoreData {
    
    private static final String FILE_PATH = "./data/baymax.txt";
    private static final String SPLIT_DELIMITER = "\\|";
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    
    /**
     * Writes tasks from internal task list to text file.
     * Iterates through the task list and formats each task into a storable string format.
     */
    public static void writeToFile() {
        try {
            File f = new File(FILE_PATH);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < TaskData.getTotalTasks(); i++) {
                Task currTask = TaskData.getTask(i);
                String toWrite = formatTaskToString(currTask);
                fw.write(toWrite);
            }
            fw.close();
            
        } catch (IOException e) {
            UiBuffer.append("Something went wrong :( - " + e.getMessage());
        }
    }
    
    /**
     * Formats a single Task object into a delimited string for storage.
     * Extracts the specific details based on the subclass of the task.
     *
     * @param currTask The task to format.
     * @return A formatted string representing the task.
     */
    private static String formatTaskToString(Task currTask) {
        TaskType taskType = currTask.getTaskType();
        int num = (currTask.getIsDone() ? 1 : 0);
        String description = currTask.getDescription();
        
        switch (taskType) {
        case TODO:
            return String.format(" T | %d | %s \n", num, description);
        case DEADLINE:
            Deadline d = (Deadline) currTask;
            String dateString = d.getDateTime().format(DATE_TIME_FORMAT);
            return String.format(" D | %d | %s | %s \n", num, description, dateString);
        case EVENT:
            Event e = (Event) currTask;
            String time1 = e.getStartTime().format(DATE_TIME_FORMAT);
            String time2 = e.getEndTime().format(DATE_TIME_FORMAT); // Bug fixed here!
            return String.format(" E | %d | %s | %s | %s \n", num, description, time1, time2);
        default:
            return "";
        }
    }
    
    /**
     * Reads data from hard disk and stores it in the session's internal list.
     * Safely reads the file line by line and delegates the parsing to a helper method.
     */
    public static void readFromFile() {
        try {
            File f = new File(FILE_PATH);
            if (!f.exists()) {
                return; // Guard clause to exit early if there is no save file
            }
            
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                parseAndAddTask(sc.nextLine());
            }
        } catch (IOException | BaymaxException e) {
            UiBuffer.append("Something went wrong :(  : " + e.getMessage());
        }
    }
    
    /**
     * Parses a single formatted text line from the storage file and converts it back into a Task object.
     * Adds the newly instantiated task directly into the TaskData list.
     *
     * @param currLine The raw delimited string read from the text file.
     * @throws BaymaxException If the line is corrupted or missing necessary delimited components.
     */
    private static void parseAndAddTask(String currLine) throws BaymaxException {
        try {
            String[] lineWords = currLine.split(SPLIT_DELIMITER);
            
            String taskType = lineWords[0].trim();
            int num = Integer.parseInt(lineWords[1].trim());
            boolean isDone = (num == 1);
            String taskDescription = lineWords[2].trim();
            
            switch (taskType) {
            case "T":
                ToDo todoTask = new ToDo(taskDescription, isDone);
                TaskData.addTask(todoTask);
                break;
            case "D":
                String dTime = lineWords[3].trim();
                LocalDateTime time = LocalDateTime.parse(dTime, DATE_TIME_FORMAT);
                
                Deadline dTask = new Deadline(taskDescription, isDone, time);
                TaskData.addTask(dTask);
                break;
            case "E":
                String sTime = lineWords[3].trim();
                LocalDateTime time1 = LocalDateTime.parse(sTime, DATE_TIME_FORMAT);
                
                String eTime = lineWords[4].trim();
                LocalDateTime time2 = LocalDateTime.parse(eTime, DATE_TIME_FORMAT);
                
                Event eTask = new Event(taskDescription, isDone, time1, time2);
                TaskData.addTask(eTask);
                break;
            default:
                throw new BaymaxException("Unknown task type identifier found in file.");
            }
        } catch (Exception e) {
            throw new BaymaxException("File corrupted :(. Couldn't load some previous tasks");
        }
    }
}