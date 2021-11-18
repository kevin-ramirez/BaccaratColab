import java.io.Serializable;

public class BaccaratInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    String message;
    String serverMessage;

    BaccaratInfo() {
        message = "";
        serverMessage = "";
    }
}
