package baymax.data;

import baymax.task.Task;

import java.io.FileWriter;
import java.io.IOException;

/*
* This class writes data in the text file
* Only have to access the TaskData class
* */
public class WriteFileClass {

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
}
