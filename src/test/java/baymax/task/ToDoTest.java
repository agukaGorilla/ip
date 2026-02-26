package baymax.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    
    @Test
    public void getStatusIcon_notDone_returnsCorrectIcon() {
        ToDo task = new ToDo("read a book");
        assertEquals("[T][ ] ", task.getStatusIcon());
    }
    
    @Test
    public void markDone_statusChanges_returnsCorrectIcon() {
        ToDo task = new ToDo("read a book");
        task.markDone();
        assertEquals("[T][X] ", task.getStatusIcon());
    }
}