import java.util.ArrayList;
import java.util.Objects;

public class BaccaratGame {
    ArrayList<Card> playerHand;
    ArrayList<Card> bankerHand;
    BaccaratDealer theDealer;
    double currentBet;
    double totalWinnings;
    String betOutcome;

    public double evaluateWinnings() {
        if (Objects.equals(betOutcome, "Win Draw")){
            return (currentBet * 7);
        }

        else if (Objects.equals(betOutcome, "Win")){
            return (currentBet);
        }

        else if (Objects.equals(betOutcome, "Loss")){
            return (0.0 - currentBet);
        }

        return 0.0;
    }

    public String outCome(String betChoice, String whoWon, int playerHandValue, int bankerHandValue){
        if ((playerHandValue == bankerHandValue && playerHandValue >= 8 && (!Objects.equals(betChoice, "Draw")))){
            betOutcome = "Loss";
        }

        else if (Objects.equals(betChoice, whoWon) && Objects.equals(whoWon, "Draw")){
            betOutcome = "Win Draw";
        }

        else if(Objects.equals(betChoice, whoWon)){
            betOutcome = "Win";
        }

        else if (!Objects.equals(betChoice, whoWon)){
            betOutcome = "Loss";
        }

        return betOutcome;
    }



}
