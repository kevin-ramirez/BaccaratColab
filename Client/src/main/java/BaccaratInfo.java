import java.io.Serializable;

public class BaccaratInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    String betAmount;
    String serverMessage;
    String playerCards;
    String dealerCards;
    Boolean playAgain;

    BaccaratInfo() {
        betAmount = "";
        serverMessage = "";
        playAgain = false;
    }
}
