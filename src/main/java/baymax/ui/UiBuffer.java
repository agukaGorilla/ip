package baymax.ui;

/**
 * Acts as a temporary bucket to hold text output before sending it to the GUI.
 */
public class UiBuffer {
    private static StringBuilder response = new StringBuilder();
    
    /**
     * Adds text to the bucket (and prints to console just in case).
     */
    public static void append(String text) {
        response.append(text).append("\n");
        UiBuffer.append(text + "\n"); //To still see in terminal
    }
    
    /**
     * Empties the bucket and returns all the text inside it.
     */
    public static String getAndClear() {
        String finalResponse = response.toString();
        response.setLength(0); // Empties the bucket for the next command
        return finalResponse;
    }
}