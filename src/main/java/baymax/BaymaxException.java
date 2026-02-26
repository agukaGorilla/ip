package baymax;

/**
 * Represents customized exceptions specific to Baymax application.
 */
public class BaymaxException extends Exception{
    
    /**
     * Constructs a new Baymax exception with the specific detail message.
     *
     * @param message The error message.
     */
    public BaymaxException(String message) {
        super(message);
    }
}
