package baymax.parser;

import baymax.BaymaxException;
import baymax.function.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    
    @Test
    public void handleInput_unknown_command() throws Exception {
        assertThrows(BaymaxException.class, () -> {
            Parser.handleInput("lol this is funny");
        });
        
    }
}
