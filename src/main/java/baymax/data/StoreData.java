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
    
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    
    /**
     * Writes tasks from internal task list to text file.
     * Parses and formats in storable format (delimiter).
     */
    public static void writeToFile() {
        
        try {
            File f = new File("./data/baymax.txt");
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < TaskData.getTotalTasks(); i++) {
                
                Task currTask = TaskData.getTask(i);
                // ASSERTION: Task retrieved from list should not be null
                assert currTask != null : "Task retrieved for saving should not be null";
                
                TaskType taskType = currTask.getTaskType();
                int num = (currTask.getIsDone() ? 1 : 0);
                
                String toWrite = "";
                
                switch (taskType) {
                case TODO:
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
                    // BUG FIX: Changed startTime to endTime here!
                    String time2 = endTime.format(dateTimeFormat);
                    
                    toWrite = String.format(" E | %d | %s | %s | %s \n", num, currTask.getDescription(),
                            time1, time2);
                    break;
                default:
                    // ASSERTION (Control-Flow): We should never hit an unknown task type when saving
                    assert false : "Unknown task type encountered during save: " + taskType;
                }
                
                // ASSERTION (Postcondition): toWrite should have been populated by the switch statement
                assert !toWrite.isEmpty() : "String to write to file cannot be empty";
                
                fw.write(toWrite);
            }
            fw.close();
            
        } catch (IOException e) {
            UiBuffer.append("Something went wrong :( - " + e.getMessage());
        }
    }
    
    /**
     * Reads data from hard disk and stores it in sessions input list.
     * Parses data from text file into executable and storable format.
     */
    public static void readFromFile() {
        
        try {
            File f = new File("./data/baymax.txt");
            if (!f.exists()) {
                return;
            }
            
            Scanner sc = new Scanner(f);
            
            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();
                // ASSERTION: The line read from the file should not be null or entirely blank
                assert currLine != null && !currLine.trim().isEmpty() : "Read line from data file should not be empty";
                
                String[] lineWords = currLine.split("\\|");
                
                // ASSERTION: A properly formatted saved task must have at least Type, Status, and Description
                assert lineWords.length >= 3 : "Corrupted line in data file, missing basic task components: " + currLine;
                
                String taskType = lineWords[0].trim();
                
                int num = Integer.parseInt(lineWords[1].trim());
                // ASSERTION: The boolean conversion relies on this being exactly 0 or 1
                assert num == 0 || num == 1 : "Saved task completion status in file must be exactly 0 or 1";
                
                boolean isDone = (num == 1 ? true : false);
                
                String taskDescription = lineWords[2].trim();
                
                try {
                    switch (taskType) {
                    case "T":
                        ToDo todoTask = new ToDo(taskDescription, isDone);
                        TaskData.addTask(todoTask);
                        break;
                    case "D":
                        // ASSERTION: Deadlines must have a 4th component (the date)
                        assert lineWords.length >= 4 : "Deadline in data file is missing its date component";
                        String dTime = lineWords[3].trim();
                        LocalDateTime time = LocalDateTime.parse(dTime, dateTimeFormat);
                        
                        Deadline dTask = new Deadline(taskDescription, isDone, time);
                        TaskData.addTask(dTask);
                        break;
                    case "E":
                        // ASSERTION: Events must have 5 components (Start and End times)
                        assert lineWords.length >= 5 : "Event in data file is missing time components";
                        String sTime = lineWords[3].trim();
                        LocalDateTime time1 = LocalDateTime.parse(sTime, dateTimeFormat);
                        
                        String eTime = lineWords[4].trim();
                        LocalDateTime time2 = LocalDateTime.parse(eTime, dateTimeFormat);
                        
                        Event eTask = new Event(taskDescription, isDone, time1, time2);
                        TaskData.addTask(eTask);
                        break;
                    default:
                        // ASSERTION: The file shouldn't contain weird types like "Z" or "X"
                        assert false : "Unknown task type identifier in data file: " + taskType;
                    }
                } catch (Exception e) {
                    throw new BaymaxException("File corrupted :(. Couldn't load some previous tasks");
                }
                
            }
        } catch (IOException | BaymaxException e) {
            UiBuffer.append("Something went wrong :(  : " + e.getMessage());
        }
    }
}