import java.io.Serializable;

public class BaccaratInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    String betValue;
    String message;
    String serverMessage;

    BaccaratInfo() {
        betValue = "";
        message = "";
        serverMessage = "";
    }
}
