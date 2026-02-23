package baymax.data;

/*
* This class handles storing the list of Tasks to the hard Disk
* We write the list of tasks to another text file
* */

import baymax.task.Task;

import java.io.FileWriter;
import java.io.IOException;

/*
* This class writes data in the text file
* Only have to access the TaskData class
* */
public class StoreData {

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

    }
}
