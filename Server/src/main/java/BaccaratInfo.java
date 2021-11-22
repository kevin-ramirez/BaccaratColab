import java.io.Serializable;
import java.util.ArrayList;

public class BaccaratInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    String betAmount;
    String betChoice;
    String serverMessage;
    Boolean playAgain;
    ArrayList<Card> playerCards;
    ArrayList<Card> dealerCards;
    Card playerThirdCard;
    Card bankerThirdCard;
    String whoWon;
    String playerHandValue;
    String bankerHandValue;

    BaccaratInfo() {
        betAmount = "";
        betChoice = "";
        serverMessage = "";
        playAgain = false;
        playerCards = null;
        dealerCards = null;
        playerThirdCard = null;
        bankerThirdCard = null;
        whoWon = "";
        bankerHandValue = "";
        playerHandValue = "";
    }
}
