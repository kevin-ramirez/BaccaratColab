import java.io.Serializable;

public class BaccaratInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    String betAmount;
    String serverMessage;
    String playerCards;
    String dealerCards;

    BaccaratInfo() {
        betAmount = "";
        serverMessage = "";
    }
}
