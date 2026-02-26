package baymax.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    
    @Test
    public void getDateTime_validDate_Success() throws Exception {
        LocalDateTime testDate = LocalDateTime.of(2026, 12, 12, 18, 00);
        Deadline d = new Deadline("return book", testDate);
        
        assertEquals(testDate, d.getDateTime());
    }
    
}
