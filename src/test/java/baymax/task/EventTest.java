package baymax.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    
    @Test
    public void getStartTime_validTime_success() throws Exception {
        
        LocalDateTime testDate1 = LocalDateTime.of(2026, 12, 31, 23, 59);
        LocalDateTime testDate = LocalDateTime.of(2026, 12, 31, 23, 58);
        
        Event e = new Event("meeting", testDate, testDate1);
        assertEquals(testDate, e.getStartTime());
    }
    
    @Test
    public void getEndTime_validTime_success() throws Exception {
        
        LocalDateTime testDate1 = LocalDateTime.of(2026, 12, 31, 23, 59);
        LocalDateTime testDate = LocalDateTime.of(2026, 12, 31, 23, 58);
        
        Event e = new Event("meeting", testDate, testDate1);
        assertEquals(testDate1, e.getEndTime());
    }
}

